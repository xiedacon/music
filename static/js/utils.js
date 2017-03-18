//只有逻辑，保证每个work执行完成后才会执行done
//works：[Function,Function]
//WorkGroup(works).done(function(){});
function WorkGroup(works) {
  if (!Array.isArray(works)) {
    throw TypeError("works必须为Array");
  }

  var num = works.length | 0 //
  , callback = function(){} //
  , excuteFlag = true //
  //定义暴露出去的接口
  ,group = {
    done : function(_callback) {	//模拟延迟接口
      callback = _callback;
      callback.workGroup = group;
      if(num === 0){
    	  callback.call(callback);
      }
      return this;
    },
    pop : function(work){		//被work调用接口
      num--;
      if(num<=0){
        callback.call(callback);
      }
    },/*
    push : function(work){		//动态添加work接口
      //num++;
    },*/
    fail : function(){			//有工作失败时
      callback = function(){};
      excuteFlag = false;
    }
  };

  //work与workGroup绑定
  works.forEach(function(work){
    if(work instanceof Function){
      if(excuteFlag){
        work.workGroup = group;
        work.call(work);
      }
    }else{
      throw TypeError("work必须为Function");
    }
  });

  return group;
}

//只有执行逻辑
//只能保证执行顺序正确，不能保证结果正确
//addEle：根据给出的页数，执行添加方法，前一页、后一页不在其中
//addPrevious/addNext：执行前一页或后一页的添加方法
//totalPage：总页数
//page：当前页数
//limit：显示的页面个数
function Page(config) {//分页的计算模块
  var defaultFunction = function() {
  }, //
  addEle = config.addEle || defaultFunction, //
  addPrevious = config.addPrevious || defaultFunction, //
  addNext = config.addNext || defaultFunction, //
  totalPage = config.totalPage || 0, //
  page = config.page || 1, //
  limit = config.limit || 10;

  if (totalPage <= 0) {
    //传的什么玩意儿啊
    return;
  } else if (totalPage == 1) {
    //上一页   1  下一页
    addPrevious();
    addEle(1);
    addNext();
  } else {
    //一般情况
    var middle = Math.ceil(limit / 2), //中数
    begin = page - (middle - 1), //开始位默认值
    end = page + (middle - 1); //结束位默认值

    if (page < middle) { //中数以下
      begin = 1;
      end = limit;
    }
    if (totalPage < end) {//结束位修正
      end = totalPage;
    }

    addPrevious();
    for (var i = begin; i < end + 1; i++) {
      addEle(i);
    }
    addNext();
  }
}

//
function Excutor(arguments){
  var defaultFunction = function(){}, //
  source = arguments.source, //
  data = arguments.data, //
  before = arguments.before ? arguments.before : defaultFunction, //
  excute = arguments.excute ? arguments.excute : defaultFunction, //
  after = arguments.after ? arguments.after : defaultFunction;

  return {
    source : source,
    data : data,
    before : before,
    do : excute,
    after : after,
    excute : function(){
      before(this.source, this.data, this);
      excute(this.source, this.data, this);
      after(this.source, this.data, this);
      return this;
    }
  }
}

//
function dom(selector){
  if(selector instanceof Element){
    var ele = selector;
  }else{
    var ele = document.querySelector(selector);
  }
  if(!ele) throw "该dom元素不存在";

  var allListeners = {};

  return {
    e : ele,
    //dom
    siblings : function(){
      return this.parent().childs().filter(function(e){
        return e.e != ele;
      });
    },
    parent : function(tagName){
      var tag = ele.parentElement;
      if(tagName){
        while(tag.tagName != tagName.toLocaleUpperCase()){
          tag = tag.parentElement;
        }
      }
      return dom(tag);
    },
    childs : function(){
      return Array.from(ele.children).map(function(e){
        return dom(e);
      });
    },
    addClass : function(className){
      ele.className += " " + className;
      return this;
    },
    removeClass : function(className){
      if(ele.className){
        var newClassName = "";
        ele.className.split(/\s/).filter(function(_className){
          return _className != className;
        }).forEach(function(_className){
          newClassName += _className;
        });
        ele.className = newClassName;
        return this;
      }
    },
    attr : function(attres){
      if(attres === "class") attres = "className";
      return ele[attres];
    },
    removeAttr : function(attrName){
      if(attrName == "class") attrName = "className";
      ele[attrName] = "";
      return this;
    },
    css : function(csses){
      for(var cssName in csses){
        ele.style[cssName] = csses[cssName];
      }
      return this;
    },
    //事件
    on : function(type,listener,once){
      var listeners = allListeners[type];
      (listeners = listeners ? listeners : []).push(listener);
      allListeners[type] = listeners;

      ele.addEventListener(type,listener,{
        once : once
      });

      return this;
    },
    off : function(type,listener){
      var listeners = allListeners[type];
      if(!listeners){
        ele.removeEventListener(type,listener);
        return this;
      }
      if(listener){
        listeners.filter(function(_listener){
          return _listener == listener;
        }).forEach(function(_listener){
          ele.removeEventListener(type,_listener);
        });
        allListeners[type] = listeners.filter(function(_listener){
          return _listener != listener;
        });
        return this;
      }

      listeners.forEach(function(_listener){
        ele.removeEventListener(type,_listener);
      });
      allListeners[type] = undefined;

      return this;
    },
    once : function(type,listener){
      return this.on(type,listener,true);
    },
    trigger : function(type){
      ele[type]();
      return this;
    }
  };
}
