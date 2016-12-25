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
