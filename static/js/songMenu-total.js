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
			name = PageScope.params.secondTagName, //
			href = "#songMenus?" + (id?("secondTagId="+id):"") + (name?("&secondTagName="+name):""), //
			template = `
			<div class="nav_top">
				<i class="triangle"></i>
				<a class="nav_top_button" title="`+ name +`" href="` + href + `">`+ name +`</span>
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

	function init() {
		$("#tagName").text(PageScope.params["secondTagName"]);
		$("#select").click(function() {
			var flag = $(this).attr("data-flag");
			if (flag === "hidden") {
				$(this).siblings().removeAttr("style");
				$(this).attr("data-flag", "show");
			} else {
				$(this).siblings().attr("style", "display: none");
				$(this).attr("data-flag", "hidden");
			}
		})
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
