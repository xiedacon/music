(function() {
	var orderBy = "hot", //
	supplement = PageScope.params.secondTagId ? ("/secondTagId_" + PageScope.params.secondTagId) : "" //
	;

	$.ajax({
		url : "songMenu/" + orderBy + "_1" + supplement,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		process(source);

		source.data.urlPrefix = "songMenu/" + orderBy + "_";
		source.data.urlSuffix = supplement;
		FUNCTION.loadSongMenusAndPages(source);
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

	function init() {
		$(".orderBy").click(function() {
			$(this).addClass("now");
			$(this).siblings().removeClass("now");

			var orderBy = $(this).attr("data-orderBy");
			PageScope["orderBy"] = orderBy;
			AJAX({
				url : "songMenu/" + orderBy + "_1" + PageScope["supplement"],
				success : loadSongMenuList
			});
		})
	}
}())
