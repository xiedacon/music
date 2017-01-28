window.Router = (function(){

  function HashManager(){
    window.onhashchange = function(e){
		Router.goto(hashManager.hash);
    }

    var hashManager = {};
    Object.defineProperty(hashManager , "hash", {
      get : function(){
        return location.hash;
      },
      set : function(hash){
        location.hash = hash;
      }
    });
    return hashManager;
  }


  function PageCache(){
    var pages = {};

    this.put = function(data){
      pages[data.url] = new Page({
        url:data.url,
        source:data.source
      });
    }

    this.get = function(url){
      return pages[url];
    }

    this.remove = function(url){
      var page = pages[url];
      delete pages[url];
      return page;
    }

    function Page(data){
      this.url = data.url;
      this.source = data.source;
    }
  }

  function preLoadPage(){
    view.main.innerHTML = view.loading.innerHTML;

    var extras = document.getElementsByClassName("extra");
    if(extras){
      Array.from(extras).forEach(function(element){
        element.parentElement.removeChild(element);
      });
    }
  }

  function loadPage(routerObj){
    view.title.innerHTML = routerObj.title;
    //這是workGroup1
    WorkGroup([
        loadCsses(routerObj.csses),
        loadHtml(routerObj.frame)
    ]).done(function(){
      loadJs(routerObj.controller);
    });
  }

  function loadCsses(hrefArr){
    if(!hrefArr){
      return function(){this.workGroup.pop();};
    }
    return function(){//workGroup1的任務
      var loadFun = hrefArr.map(loadCss);
      //這是workGroup2
      WorkGroup(loadFun).done(function(){
        this.workGroup.pop();//workGroup1的任務完成
      }.bind(this));
    }
  }

  function loadCss(href){
    return function(){//workGroup2的任務
      var link = document.createElement("link");
      link.setAttribute("rel","stylesheet");
      link.setAttribute("type", "text/css");
      link.setAttribute("class", "extra");
      link.setAttribute("href", href);
      link.onload = function(){
        this.workGroup.pop();//workGroup2的任務完成
      }.bind(this);
      document.documentElement.appendChild(link);
    }
  }

  function loadJs(src){
    if(!src){
      return function(){this.workGroup.pop();};
    }
	  var script = document.createElement("script");
	  script.setAttribute("type","text/javascript");
	  script.setAttribute("src", src);
	  script.onload = function(){
	    document.documentElement.removeChild(script);
	    script = null;
	  };
	  document.documentElement.appendChild(script);
  }

  function loadHtml(url){
    return function(){//workGroup1的任務
      var cache = pageCache.get(url);
      if(cache){
        view.main.innerHTML = cache.source;
        this.workGroup.pop();//workGroup1的任務完成
      }else{
        $.ajax({
          url:url,
          type:"GET",
          dataType:"text",
          context:this
        }).done(function(source){
          view.main.innerHTML = source;
          pageCache.put({
            url:url,
            source:source
          });
          this.workGroup.pop();//workGroup1的任務完成
        });
      }
    }
  }

  function parseHash(hash){
	var url, //
	params = {};

    if(/^(#(?:\w+\/)?\w+)(?:\?((?:\w+=(?:\w|[\u4e00-\u9fa5])*&)*\w+=(?:\w|[\u4e00-\u9fa5])*))?$/g.test(hash)){
      var arr = /^(#(?:\w+\/)?\w+)(?:\?((?:\w+=(?:\w|[\u4e00-\u9fa5])*&)*\w+=(?:\w|[\u4e00-\u9fa5])*))?$/g.exec(hash);
      url = arr[1];
      paramStr = arr[2];

      if(paramStr){
        var paramStrs = paramStr.split("&");
        paramStrs.forEach(function(paramStr){
          var key_value = paramStr.split("=");
          params[key_value[0]] = key_value[1];
        })
      }
    } else {
      url = view.index;
    }

    var routerObj = {
      url:url,
      params:params
    };
    Object.setPrototypeOf(routerObj,routerMap[url]);
    return routerObj;
  }

  function routerAction(hash){
    var routerObj = parseHash(hash);
    window.PageScope = {
      url:routerObj.url,
      params:routerObj.params
    }

    preLoadPage();
    loadPage(routerObj);
  }

  var pageCache = new PageCache(), //
  hashManager = HashManager(), //
  view, //
  routerMap, //
  Router = {
    start : function(config){
      if(config.view && config.routerMap){
        view = config.view;
        routerMap = config.routerMap;
        view.loading = view.loading ? view.loading : document.createElement("div");
        view.index = view.index ? view.index : "#index";
        if(!view.main){
          throw "view.main不能为空";
        }
        if(!view.title){
          throw "view.title不能为空";
        }

        Router.goto(hashManager.hash);
      } else {
        throw "配置文件出错" ;
      }
    },
    goto : function (hash) {
      routerAction(hash);
    }
  };

  return Object.create(Router);

})()
