(function() {

	$.ajax({
		url : "json/index",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		FUNCTION.loadAlbums("ul#hotList",source);
	});

	$.ajax({
		url : "album/tagId_" + PageScope.params.tagId + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		Excutor({
			source : source,
			data : {
				urlPrefix : "album/tagId_" + PageScope.params.tagId + "/"
			},
			before : function(source,data){
				data.pageBean = process(source);
				data.albums = data.pageBean.beans;
			},
			excute : function(source, data, excutor){
				FUNCTION.loadAlbums("ul#albumList", data);
				FUNCTION.loadPages("ul#pages", data, excutor);
			}
		}).excute();
	});

	$.ajax({
		url : "albumTag",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<h2>`+ PageScope.params.tagName +`</h2>
		<p class="types">
			<a class="type" href="#albums">全部</a>
			<span>|</span>
			{{each tags as tag index}}
			<a class="type" href="#albums?tagId={{tag.id}}&tagName={{tag.name}}">{{tag.name}}</a>
			{{if index < tags.length - 1}}
			<span>|</span>
			{{/if}}
			{{/each}}
		</p>
		`, //
		data = {
			tags : process(source)
		};

		document.querySelector("div#tags").innerHTML = Template.compile(template)(data);
	});

}())
