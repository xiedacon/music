(function() {

	var modules = new Array();
	function ModuleManager() {
	}
	ModuleManager.prototype.get = function(name) {
		return modules[name];
	}
	ModuleManager.prototype.addModule = function(key, value) {
		modules[key] = value;
	}
	var moduleManager = new ModuleManager();

	var $userDiv = $("#userDiv");
	function UserManager() {
	}
	UserManager.prototype.setUser_regist = function(user) {
		sessionStorage.setItem("regist_user", JSON.stringify(user));
		return this;
	}
	UserManager.prototype.getUser_regist = function() {
		return JSON.parse(sessionStorage.getItem("regist_user"));
	}
	UserManager.prototype.removeUser_regist = function() {
		sessionStorage.removeItem("regist_user");
	}
	UserManager.prototype.setUser = function(user, flag) {
		sessionStorage.setItem("user", JSON.stringify(user));

		if (flag) {// 自动登录选项相关
			localStorage.setItem("login_auto", true);
			localStorage.setItem("user", JSON.stringify(user));
		} else {
			localStorage.setItem("login_auto", false);
			localStorage.removeItem("user");
		}
		return this;
	}
	UserManager.prototype.getUser = function() {
		return JSON.parse(sessionStorage.getItem("user"));
	}
	UserManager.prototype.getUserId = function() {
		var user = window.UserManager.getUser();
		if (user) {
			return user.id;
		} else {
			return undefined;
		}
	}
	UserManager.prototype.init = function() {
		if (localStorage.getItem("login_auto")) {
			sessionStorage.setItem("user", localStorage.getItem("user"));
		}
		var user = window.UserManager.getUser();
		if (user) {
			window.UserManager.login();
		} else {
			removeUser();
			// 第三方登录，暂时先这样
			if (location.search && location.search !== "") {
				var params = location.search.substring(1).split("&");
				var id = params[0];
				var name = params[1];
				$.ajax({
					url : "user/" + id,
					type : "GET",
					dataType : "json",
					success : function(data) {
						window.UserManager.setUser_regist(data);

						if (!name || name === "") {
							MMR.get("username").show();
						} else {
							window.UserManager.login();
						}
					},
					error : function(e) {
						console.error(e);
					}
				});
				history.replaceState(null, null, location.href.split("?")[0]);
			}
		}
	}
	UserManager.prototype.logout = function() {
		removeUser();
		router.startRouter("index");
		MMR.get("simpleMsg").setData({
			"status" : "info",
			"content" : "退出成功"
		}).show();
	}
	var firstFlag = true;
	UserManager.prototype.login = function() {
		var user = window.UserManager.getUser();
		$userDiv.find(".username").empty().append("<img src='" + user.icon + "'>" + user.name + "<i></i>");
		unbindEvents();
		bindEvents('nav_login');

		$("#myMusic").attr({
			"data-href" : "myMusic?userId=" + user.id,
			"onclick" : "jump(this);"
		})

		if (firstFlag) {
			firstFlag = false;
			return;
		}

		router.startRouter("index");
	}
	function removeUser() {
		sessionStorage.removeItem("user");
		localStorage.removeItem("user");
		localStorage.removeItem("login_auto");

		$userDiv.find(".username").empty().text("登录");
		unbindEvents();
		bindEvents('nav_logout');
	}
	function unbindEvents() {
		$userDiv.unbind() //
		.find(".user_nav").css("display", "none");
	}
	function bindEvents(eleName) {
		$userDiv.hover(function() {
			$(this).children('.' + eleName).stop(true).slideDown(300);
		}, function() {
			$(this).children('.' + eleName).stop(true).slideUp(300);
		});
	}

	window.limitStringLength = function limitStringLength(content, length) {
		if (!content) {
			return;
		}

		var num = content.split(/\w|\s/).length - 1;
		var realLength = (content.length - num) * 2 + num;
		if (realLength > length * 2) {
			var _length = length * 2;
			var i = 0;
			var _content = "";
			while (_length > 0) {
				if (content.charAt(i).match(/\w|\s/)) {
					_length -= 1;
				} else {
					_length -= 2;
				}
				_content += content.charAt(i);
				i++;
			}
			return _content + "..";
		} else {
			return content;
		}
	}
	window.UserManager = new UserManager();

	// $yyyy-$MM-$dd-$SS $HH:$mm:$ss:$SS
	window.DateFormatter = function DateFormatter(regExpString) {
		var array = new Array();
		var str = regExpString;
		str = parse(str);

		function parse(regExpString) {
			var possibleValues = new Array();
			var str = regExpString;

			possibleValues.splice(0, possibleValues.length, 2, 4);
			str = parse_base("y", "year", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("M", "month", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("d", "day", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("H", "hour", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("m", "minute", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("s", "seconds", str, possibleValues);
			
			return str;
		}

		function parse_base(part, total, regExpString, possibleValues) {
			var num = regExpString.split(part).length - 1;
			var str = regExpString;
			array[total] = new DateItem();

			for (var i = 0; i < possibleValues.length; i++) {
				if (num === possibleValues[i] && str.split(increaseStr(part, num)).length === 2) {
					var _str = increaseStr(part, num);
					str = str.replace(_str, "$" + _str);
					array[total].regExp = "$" + _str;
					array.length += 1;
					return str;
				}
				if (num === 0) {
					return str;
				}
			}
			alert("格式错误：" + regExpString);
			throw "格式错误：" + regExpString;
		}

		function increaseStr(str, num) {
			var result = "";
			if (num === 0) {
				return result;
			}
			for (var i = 0; i < num; i++) {
				result += str;
			}
			return result;
		}

		function DateItem(regExp, num) {
			this.regExp = regExp;
			this.num = num;
		}

		this.format = function(time) {
			var date = new Date();
			date.setTime(time);
			array["year"].num = date.getFullYear();
			array["month"].num = date.getMonth() + 1;
			array["day"].num = date.getDate();
			array["hour"].num = date.getHours();
			array["minute"].num = date.getMinutes();
			array["seconds"].num = date.getSeconds();
			return replace(array, str);
		}

		function replace(array, str) {
			var _str = str;
			for ( var i = 0 in array) {
				var num = "";
				var regExp = array[i].regExp;
				if (regExp) {
					if (array[i].num || array[i].num === 0) {
						num = array[i].num.toString();
						if (num.length < regExp.length - 1) {// 时分秒为0
							num = increaseStr("0", regExp.length - 1 - num.length) + num;
						} else {// 年只有两位
							num = num.substring(num.length - regExp.length + 1);
						}
					}
					_str = _str.replace(regExp, num);
				}
			}
			return _str;
		}
	}

	window.ModuleManager = moduleManager;
	window.MMR = moduleManager;

	var configs = new Array();
	configs.push("js/music.js");
	configs.push("js/modules.js");
	configs.push("js/MethodStack.js");

	for (var i = 0; i < configs.length; i++) {
		loadScript(configs[i]);
	}

	function loadScript(src) {
		var script = document.createElement("script");
		script.src = src;
		script.onload = function() {
			document.documentElement.removeChild(script);
			script = null;
		}
		document.documentElement.appendChild(script);
	}

}())
