//只有逻辑，保证每个work执行完成后才会执行done
//works：[Function,Function]
//WorkGroup(works).done(function(){});
function WorkGroup(works) {
  if (!Array.isArray(works)) {
    throw TypeError("works必须为Array");
  }

  var num = works.length || 0 //
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
function dom(param){//selector | Element
  var emptyElement = document.createElement("div"),//
  ele = ((param instanceof Element) && param)
        || ((typeof param === "string") && document.querySelector(param))
        || emptyElement, //
  allListeners = {}, //
  temp;

  return {
    e : ele,
    //dom元素操作
    remove : function(){ele.remove.apply(ele,arguments);},
    clone : function(deep){return ele.cloneNode(typeof deep === "boolean" ? deep : true);},
    replaceWith : function(){ele.replaceWith.apply(ele,arguments);},
    contains : function(param){//selector | dom | Element
      if(typeof param === "string") return this.querySelector(param) ? true : false;
      if(!param instanceof Element) param = ((temp = param.e) && temp instanceof Element) ? temp : emptyElement;
      return ele.contains(param);
      // return ele.contains(((typeof param === "string") && this.querySelector(param))
      //                     || (param instanceof Element && param)
      //                     || ((param.e && param.e instanceof Element) ? params.e : emptyElement));
    },
    text : function(content){
      if(!content) return ele.textContent;
      ele.textContent = content;
      return this;
    },
    innerHTML : function(content){
      if(!content) return ele.innerHTML;
      ele.innerHTML = content;
      return this;
    },
    querySelector : function(){return dom((temp = ele.querySelector.apply(ele,arguments)) ? temp : undefined);},
    querySelectorAll : function(){return Array.from(ele.querySelectorAll.apply(ele,arguments)).map(function(e){return dom(e);});},
    find : function(){return this.querySelectorAll.apply(this,arguments);},
    parent : function(selector){
      var tag = ele.parentElement;
      if(selector)
        while(tag && !tag.matches(selector))
          tag = tag.parentElement;
      return dom(tag);
    },
    childs : function(selector){
      return Array //
              .from(ele.children)//
              .filter(function(e){return selector ? e.matches(selector) : true;}) //
              .map(function(e){return dom(e);});
    },
    removeChilds : function(selector){this.childs(selector).forEach(function(e){e.remove();});return this;},
    append : function(){ele.append.apply(ele,arguments);return this;},
    prepend : function(){ele.prepend.apply(ele,arguments);return this;},
    siblings : function(selector){return this.parent().childs(selector).filter(function(e){return e.e != ele;});},
    after : function(){ele.after.apply(ele,arguments);return this;},
    before : function(){ele.before.apply(ele,arguments);return this;},
    //dom属性操作
    addClass : function(className){className && ele.classList.add(className);return this;},
    removeClass : function(className){ele.classList.remove(className);return this;},
    hasClass : function(className){return ele.classList.contains(className);},
    attr : function(attres){
      if(typeof attres === "string") return ele.getAttribute(attres);
      for(var name in attres)
        ele.setAttribute(name,attres[name]);
      return this;
    },
    removeAttr : function(attrName){ele.removeAttribute(attrName);return this;},
    hasAttr : function(attrName){return ele.hasAttribute(attrName);},
    css : function(param){
      if(typeof param === "string") return ele.style[param];
      for(var cssName in param)
        ele.style[cssName] = param[cssName];
      return this;
    },
    //事件
    on : function(type,listener,once){
      var listeners = allListeners[type] || [];
      ele.addEventListener(type,listener,{once : once});
      listeners.push(listener);
      allListeners[type] = listeners;
      return this;
    },
    off : function(type,listener){
      ele.removeEventListener(type,listener);
      allListeners[type] = (allListeners[type] || []).filter(function(_listener){
        return (_listener !== ((listener || ele.removeEventListener(type,_listener)) || _listener));
      });
      return this;
    },
    once : function(type,listener){
      var fn = (function(){listener.apply(listener,arguments);this.off(type,fn);}).bind(this);
      return this.on(type,fn,true);
    },
    trigger : function(type){
      typeof type === "string" && ele.dispatchEvent(new Event(type,{bubbles:true,cancelable:true,composed:true}));
      return this;
    }
  };
}
