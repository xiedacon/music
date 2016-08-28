(function() {

	function Router() {
	}

	Router.prototype.start = function(config) {
		this.view = config.view;
		this.routerMap = config.routerMap;
		this.defaults = config.defaults;
		this.historyStack = new HistoryStack();
		this.pageCache = new PageCache();
		this.resourceLoader = new ResourceLoader();

		router.startRouter(this.defaults);
	}

	Router.prototype.back = function() {
		var back = router.historyStack.back();
		if (back) {
			var routerObj = parseUrl(back);
			routerAction(routerObj);
		}
	}

	Router.prototype.getPage = function(realUrl) {
		return router.pageCache.get(realUrl);
	}

	Router.prototype.getPageScope = function(realUrl) {
		return router.pageCache.get(realUrl).pageScope;
	}

	Router.prototype.startRouter = function(routerUrl, refresh) {
		if (!routerUrl) {
			throw "路径不存在";
		}
		var routerObj = parseUrl(routerUrl);
		if (!routerObj) {
			routerObj = {
				url : router.defaults
			};
		}

		routerObj.refresh = refresh;
		routerAction(routerObj);
		router.historyStack.push(routerObj.realUrl);
	}

	window.FUNCTION = {};
	window.AJAX = function(config) {
		if (!config) {
			console.error("ajax配置错误");
			return;
		}

		var url = config.url;
		var type = config.type;
		var dataType = config.dataType;
		var _success = config.success;
		var error = config.error;

		if (!url) {
			throw "url不能为空";
		}
		if (!type)
			type = "GET";
		if (!dataType)
			dataType = "json";
		if (!error) {
			error = function(e) {
				console.error(e);
			};
		}

		var success = function(data) {
			if (_success) {
				_success(data);
			}
			window.PageScope[url] = data;
		};

		if (window.PageScope[url]) {
			success(window.PageScope[url]);
			return;
		}

		$.ajax({
			url : url,
			type : type,
			dataType : dataType,
			success : success,
			error : error
		})
	}

	function HistoryStack() {
		var record;

		this.back = function() {
			if (record && record.previous) {
				record = record.previous;
				return record.url;
			}
		}

		this.push = function(url) {
			var _record = new Record(url, record);
			if (record) {
				record.next = _record;
			}
			record = _record;
		}

		this.forward = function() {
			if (record && record.previous) {
				return record.next.url;
			}
		}

		this.pop = function() {

		}

		function Record(url, previous, next) {
			this.next = next;
			this.previous = previous;
			this.url = url;
		}
	}

	function PageCache() {
		this.put = function(data) {
			pages[data.realUrl] = new Page({
				"templateUrl" : data.templateUrl,
				"controller" : data.controller,
				"html" : data.html,
				"realUrl" : data.realUrl
			});
		}

		this.get = function(realUrl) {
			return pages[realUrl];
		}

		this.remove = function(realUrl) {
			pages[realUrl] = undefined;
		}

		this.reflesh = function(realUrl) {
			pages[realUrl].reflesh();
		}

		this.removeAll = function() {
			pages = new Array();
		}

		var pages = new Array();

		function Page(data) {
			this.templateUrl = data.templateUrl;
			this.controller = data.controller;
			this.html = data.html;
			var pageScope = new PageScope(data.realUrl);
			this.pageScope = pageScope;
			this.reflesh = function() {
				this.pageScope = new PageScope(data.realUrl);
			}

			function PageScope(url) {
				this.url = url;
				var params = new Array();
				this.params = params;

				parseUrl(url);
				function parseUrl(url) {
					if (/\?/.test(url)) {
						url = url.replace(" ", "");
						var paramString = url.split("?")[1];
						var _params = paramString.split("&");
						for (var i = 0; i < _params.length; i++) {
							var key_value = _params[i].split("=");
							params[key_value[0]] = key_value[1];
						}
					}
				}
			}
			this.setAttribute = function(attributeName, attributeValue) {
				pageScope[attributeName] = attributeValue;
				pageScope.length += 1;
			}

			this.getAttribute = function(attributeName) {
				return pageScope[attributeName];
			}
		}
	}

	function routerAction(routerObj) {
		var temp = router.pageCache.get(routerObj.realUrl);
		if (temp) {
			if (routerObj.refresh) {
				router.pageCache.reflesh(routerObj.realUrl);
			}
			loadPage({
				html : temp.html.clone(),
				realUrl : routerObj.realUrl,
				controller : temp.controller,
				flag : true
			});
			return;
		}

		var routerItem = router.routerMap[routerObj.url];

		if (!routerItem || !routerItem.templateUrl) {
			throw "映射路径错误：         realUrl:" + routerObj.realUrl + "   rootUrl:" + routerObj.url;
		}

		$.ajax({
			url : routerItem.templateUrl,
			type : "get",
			dataType : "html",
			success : function(data) {
				var ele = $('<div></div>');
				ele.html(data);

				router.pageCache.put({
					"realUrl" : routerObj.realUrl,
					"templateUrl" : routerItem.templateUrl,
					"controller" : routerItem.controller,
					"html" : ele.clone()
				});

				loadPage({
					html : ele.clone(),
					realUrl : routerObj.realUrl,
					controller : routerItem.controller
				});
			},
			error : function(e) {
				// 404
			}
		})
	}

	function loadPage(data) {
		window.PageScope = router.getPageScope(data.realUrl);

		$("html .body")//
		.html("")//
		.css("min-height", window.innerHeight - $("html .head").height() - $("html .foot").height());
		$("html .extras").remove();

		var realUrlDiv = document.getElementById("realUrl");
		if (realUrlDiv) {
			realUrlDiv.innerHTML = data.realUrl;
		} else {
			realUrlDiv = document.createElement("div");
			realUrlDiv.setAttribute("id", "realUrl");
			realUrlDiv.innerHTML = data.realUrl;
			document.documentElement.appendChild(realUrlDiv);
		}

		loadCss({
			resource : data.html,
			callback : function() {
				loadHtml(data.html);
				$("html .body").children().css("visibility", "hidden");
				loadScript({
					resource : data.controller,
					callback : function() {
						$("html .body").children().css("visibility", "visible");
					}
				});
			}
		});
	}

	function loadCss(data) {
		var csses = data.resource.find("link[rel='stylesheet']");
		var cssResources = new Array();
		for (var i = 0; i < csses.length; i++) {
			var href = csses[i].getAttribute("href");

			cssResources[i] = new Resource({
				"href" : href
			}, function(data) {
				var link = document.createElement("link");
				link.setAttribute("rel", "stylesheet");
				link.setAttribute("type", "text/css");
				link.setAttribute("href", data.href);
				link.setAttribute("class", "extras");

				document.getElementsByTagName("head")[0].appendChild(link);
				return link;
			});
		}
		router.resourceLoader.load(cssResources, function() {
			data.callback();
		});
	}

	function loadHtml(html, realUrl) {
		$("title").replaceWith(html.find("title"));
		$("html head").append(html.find("style").attr("class", "extras"));
		$("html .body").html(html.find(".body").children());
		$("body").scrollTop(0);
	}

	function loadScript(data) {
		var resource, callback;
		if (typeof data === "object") {
			resource = data.resource;
			callback = data.callback;
		} else {
			resource = data;
			callback = function() {
			};
		}
		var script = document.createElement("script");
		script.setAttribute('src', resource);
		script.onload = function() {
			document.documentElement.removeChild(script);
			document.documentElement.removeChild(document.getElementById("realUrl"));
			script = null;
			callback();
		}
		document.documentElement.appendChild(script);
	}

	function parseUrl(routerUrl) {
		var url = routerUrl.split("?")[0];
		var realUrl = routerUrl;

		return {
			"url" : url,
			"realUrl" : realUrl
		};
	}

	function Resource(params, load) {
		var data = params;

		this.load = function(callback) {
			var resourceEle = load(data);
			resourceEle.onload = function() {
				callback();
			}
		}
	}

	// 还要容错机制
	function ResourceLoader() {
		var state = 0;
		var _callback;

		this.load = function(resources, callback) {
			if (!resources) {
				throw "资源错误";
			}

			_callback = callback;
			state = resources.length;

			for (var i = 0; i < resources.length; i++) {
				resources[i].load(function() {
					state -= 1;
					check();
				});
			}
		}

		function check() {
			if (state === 0) {
				_callback();
			}
		}
	}

	window.router = new Router();
})();