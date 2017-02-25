(function() {
	var orderBy = "hot", //
	supplement = PageScope.params.secondTagId ? ("/secondTagId_" + PageScope.params.secondTagId) : "", //
	songMenusExcutor;

	$.ajax({
		url : "songMenu/" + orderBy + "_1" + supplement,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		songMenusExcutor = Excutor(source, {
			urlPrefix : "songMenu/" + orderBy + "_",
			urlSuffix : supplement
		}, function(source, data, excutor){
			data.pageBean = process(source);
		}, function(source, data, excutor){
			FUNCTION.loadSongMenus("ul#songMenuList", data);
			FUNCTION.loadPages("ul#pages", data, excutor);
		}).excute();
	});

	$.ajax({
		url : "songMenuTag",
		type : "GET",
		dataType : "json"
	}).done(function(source){
			process(source);

			var id = PageScope.params.secondTagId, //
			template = `
			<div class="nav_top">
				<i class="triangle"></i>
				<a class="nav_top_button" title="全部分类" href="#songMenus?secondTagName=全部分类">全部分类</span>
			</div>
			{{each data as tag}}
			<li class="subnav">
				<h3>
					<i class="icomoon"></i>
					<span>{{tag.name}}</span>
				</h3>
				<div class="types">
					{{each tag.secondTagList as secondTag index}}
					<a href="#songMenus?secondTagId={{secondTag.id}}&secondTagName={{secondTag.name}}" title="{{secondTag.name}}" class="type {{if secondTag.id == `+ id +`}}select{{/if}}">{{secondTag.name}}</a>
					{{if index < tag.secondTagList.length - 1}}<span>|</span>{{/if}}
					{{/each}}
				</div>
			</li>
			{{/each}}
			`;
			document.querySelector("ul#secondTagList").innerHTML = Template.compile(template)(source);
	});

	//init();
	document.querySelector("h2#tagName").innerHTML = PageScope.params.secondTagName;
	var select = document.querySelector("span#select"), //
	secondTagList = document.querySelector("ul#secondTagList");

	select["hidden"] = true;
	select.addEventListener("click",function(){
		if(select["hidden"]){
			select["hidden"] = false;
			secondTagList.style.display = "block";
		}else{
			select["hidden"] = true;
			secondTagList.style.display = "none";
		}
	})

	document.querySelector("div#orderBy").addEventListener("click", function(e){
		var tag = e.target;
		if(tag.innerHTML === "热门"){
			tag.className = "orderbyHot orderBy now";
			tag.nextSibling.nextSibling.className = "orderbyNew orderBy";
			orderBy = "hot";
		} else {
			tag.className = "orderbyNew orderBy now";
			tag.previousSibling.previousSibling.className = "orderbyHot orderBy";
			orderBy = "new";
		}

		$.ajax({
			url : "songMenu/" + orderBy + "_1" + supplement,
			type : "GET",
			dataType : "json"
		}).done(function(source){
			songMenusExcutor.source = source;
			songMenusExcutor.data = {
				urlPrefix : "songMenu/" + orderBy + "_",
				urlSuffix : supplement
			};
			songMenusExcutor.excute();
		});
	})
}())
