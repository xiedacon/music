(function (){

  //callback函数生成函数
  function Callback(callback){
    callback = callback ? callback : function(){};
    return function(element){
      (callback.bind(element))();
      this.childs.forEach(function(node){
    	  if(node.state > 0)
              node.callback(element.querySelector(node.selector));
      })
    }
  }

  function Node(selector, parentNode, domType, id, options){
    return {
      selector : selector,
      parentNode : parentNode,
      htmls : [],
      childs : [],
      callback : undefined,
      //-1:未完成
      //1:已完成
      state : -1,
      //拼接模板
      replenish : function(html){
        if(this.state > 0){
          var domStr = getDomStr(domType, id, this.htmls.join(""));
          html = html.replace(getDomStr(domType, id), domStr);
          this.childs.forEach(function(node){
            html = node.replenish(html);
          });
        }

        return html;
      },
      //生成dom，执行callback
      //生成模板，并将自己的状态修改为已完成
      //父节点已完成：合并已完成子节点，生成dom，执行callback
      //父节点未完成：不执行任何操作
      render : function(source, _callback, _options){
        var html = (Template.compile(this.htmls.join(""), options))(source, _options);
        this.callback = Callback(_callback);
        this.state = 1;

        if(parentNode && parentNode.state < 0){//存在父节点且未完成
          this.htmls = [html];
          return undefined;
        } else {
          this.childs.forEach(function(node){
            html = node.replenish(html);
          });

          if(selector === "body"){
            bodyElement.innerHTML = html;
            this.callback(bodyElement);
          } else {
            var element = document.createElement(domType);
            element.id = id;
            element.innerHTML = html;
            this.callback(element);
            bodyElement.querySelector(selector).replaceWith(element);
          }
        }
      }
    }
  }

  function pushCode(index, node, codes){
    if(index > codes.length - 1){
      return node;
    }

    node = node.parentNode;
    node.htmls.push(codes[index]);

    return pushCode(index + 1, node, codes);
  }

  function getDomStr(domType, id, content){
	  content = content ? content : "";
	  return "<"+ domType +" id="+ id +">" + content + "</"+ domType +">";
  }

  var bodyElement;

  window.MyTemplate = {
    compile : function(_bodyElement,source,options){
      var prefix = /{{dom /g, //
      suffix = /{{\/dom}}/g, //
      codes = source.split(prefix), //
      node = Node("body"), //
      nodes = {};
      bodyElement = _bodyElement;
      nodes["body"] = node;

      codes.forEach(function(code){
        if(/^\w+#\w+}}.*/g.test(code)){
          var arr = /^((\w+)#(\w+))}}.*/g.exec(code), //
          selector = arr[1], //
          domType = arr[2], //
          id = arr[3], //
          _codes = code.split(selector+"}}")[1].split(suffix), //
          _node = Node(selector, node, domType, id, options) //
          ;

          nodes[selector] = _node;
          node.childs.push(_node);
          node.htmls.push(getDomStr(domType, id));
          _node.htmls.push(_codes[0]);
          node = _node;

          if(_codes.length > 1){
            node = pushCode(1, node, _codes);
          }
        }else{
          node.htmls.push(code);
        }
      })

      return nodes;
    },
    helper : function(name,callback){
      Template.helper(name,callback);
    },
    config : function(name,value){
      Template.config(name,value);
    }
  };
}())
