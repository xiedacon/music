(function() {
	var url = "singer", //
	index = Number.MAX_VALUE;

	if(PageScope.params.classifyId !== "all"){
		url = "singer/classifyId_" + PageScope.params.classifyId;
	}
	if(PageScope.params.classifyId === "hot"){
		url = "singer/hot";
		index = 10;
	}

	$.ajax({
		url : url,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="material_top">
			<h2>{{h2}}</h2>
		</div>
		<ul class="right_buttons">
			<li class="button double now">热门</li>
			<li class="button">A</li>
			<li class="button">B</li>
			<li class="button">C</li>
			<li class="button">D</li>
			<li class="button">E</li>
			<li class="button">F</li>
			<li class="button">G</li>
			<li class="button">H</li>
			<li class="button">I</li>
			<li class="button">J</li>
			<li class="button">K</li>
			<li class="button">L</li>
			<li class="button">M</li>
			<li class="button">N</li>
			<li class="button">O</li>
			<li class="button">P</li>
			<li class="button">Q</li>
			<li class="button">R</li>
			<li class="button">S</li>
			<li class="button">T</li>
			<li class="button">U</li>
			<li class="button">V</li>
			<li class="button">W</li>
			<li class="button">C</li>
			<li class="button">Y</li>
			<li class="button">Z</li>
			<li class="button double">其他</li>
		</ul>
		<ul class="songerList entitys">
			{{each singers as singer index}}
			<li class="songer entity" style="{{if (index+1)%5 === 0}}padding:25px 0 0 0{{/if}}">
				<div class="imageDiv">
					<a href="#singer?id={{singer.id}}">
						<img title="{{singer.name}}的音乐" src="{{singer.icon}}">
					</a>
				</div>
				<a title="{{singer.name}}的音乐" class="name" href="#singer?id={{singer.id}}">{{singer.name}}</a>
				{{if singer.userId}}
				<a title="{{singer.name}}的个人主页" class="icon" href="#home?id={{singer.userId}}">
					<i class="icomoon"></i>
				</a>
				{{/if}}
			</li>
			{{/each}}
		</ul>
		{{if remainSinger && remainSinger.length != 0}}
		<ul class="songerList_simple">
			{{each remainSinger as singer index}}
			<li class="singer_simple" style="{{if (index+1)%5 === 0}}padding:0 0 0 0{{/if}}">
				<a title="{{singer.name}}的音乐" class="name" href="#singer?id={{singer.id}}">{{singer.name}}</a>
				{{if singer.userId}}
				<a title="{{singer.name}}的个人主页" class="icon" href="#home?id={{singer.userId}}">
					<i class="icomoon"></i>
				</a>
				{{/if}}
			</li>
			{{/each}}
		</ul>
		{{/if}}
		`, //
		singers = process(source), //
		data = {
			h2 : PageScope.params.classifyName,
			singers : singers.filter(function(singer, i){
				return i < index;
			}),
			remainSinger : singers.filter(function(singer, i){
				return i >= index;
			})
		}

		document.querySelector("div#singers").innerHTML = Template.compile(template)(data);
	});

	$.ajax({
		url : "json/userClassify",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<ul class="select">
			<h3>推荐</h3>
			<a class="option {{if 'hot' === id}}now{{/if}}" href="#singers?classifyId=hot&classifyName=推荐歌手" style="display:block">
				<i class="point"></i>
				<span title="推荐歌手" class="songerList">推荐歌手</span>
			</a>
			<a class="option {{if 'all' === id}}now{{/if}}" href="#singers?classifyId=all&classifyName=入驻歌手" style="display:block">
				<i class="point"></i>
				<span title="入驻歌手" class="songerList">入驻歌手</span>
			</a>
		</ul>
		{{each classifys as classify}}
		<ul class="select" style="border: 0">
			<h3>{{classify.name}}</h3>
			{{each classify.childs as child}}
			<li class="option {{if child.id === id}}now{{/if}}">
				<i class="point"></i>
				<a href="#singers?classifyId={{child.id}}&classifyName={{child.name}}" title="{{child.name}}" class="songerList">{{child.name}}</a>
			</li>
			{{/each}}
		</ul>
		{{/each}}
		`, //
		data = {
			id : PageScope.params.classifyId,
			classifys : source
		};

		document.querySelector("div#classifys").innerHTML = Template.compile(template)(data);
	});

}())
