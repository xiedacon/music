(function() {

	$.ajax({
		url : "singer/" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<img src="{{singer.icon}}">
		<div class="entityMessage_bottom">
			<h2>
				<span>{{singer.name}}</span>
				{{if singer.remark}}
				<span class="remark">{{singer.remark}}</span>
				{{/if}}
			</h2>
			{{if singer.userId}}
			<a href="#home?id={{singer.userId}}" class="homePage" title="{{singer.name}}的个人主页">
				<span class="icon">
					<i class="icomoon"></i>
				</span>
				<span>Ta的个人主页</span>
			</a>
			{{/if}}
			<span class="collectionButton">
				<i class="icomoon"></i>收藏
			</span>
		</div>
		`, //
		data = {
			singer : process(source)
		};

		document.querySelector("div#singer").innerHTML = Template.compile(template)(data);
	});

	$.ajax({
		url : "song/singerId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="buttons">
			<div class="play_addToPlayList">
				<span class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('singerId','`+ PageScope.params.id +`')">
					<i class="icomoon"></i>
					<span>播放</span>
				</span>
				<span class="addToPlaylist icomoon" onclick="MMR.get('music').batchAdd('singerId','`+ PageScope.params.id +`')"></span>
			</div>
			<span class="collection button"> <i class="icomoon"></i>收藏热门50
			</span>
		</div>
		<table style="border: 0;">
			<tbody>
				{{each songs as song index}}
				<tr class="{{if index%2 != 0}}singular{{/if}}">
					<td style="width: 14%;">
						<span class="num">{{index + 1}}</span>
						<i class="play icomoon" onclick="MMR.get('music').addThenPlay('{{song.id}}')"></i>
					</td>
					<td style="width: 31%;">
						<a class="songName" title="{{song.name}}" href="#song?id={{song.id}}">{{song.name}}</a>
					</td>
					<td style="width: 14%;">
						<span class="time">{{song.time}}</span>
						<p class="hidden">
							<span class="addToPlaylist icomoon"></span>
							<span class="collection icomoon"></span>
							<span class="share icomoon"></span>
							<span class="download icomoon"></span>
						</p></td>
					<td style="width: 19%;">
						{{if song.albumName}}
						<a class="special" title="{{song.albumName}}" href="#album?id={{song.albumId}}">{{song.albumName}}</a>
						{{/if}}
					</td>
					<td class="maxtd" style="width: 22%;">
						<p class="max">
							<span class="hot" style="width:{{if song.collectionNum >= 1000}}100{{else if song.collectionNum < 75}}0{{else}}{{song.collectionNum / 10}}{{/if}}%"></span>
						</p>
					</td>
				</tr>
				{{/each}}
			</tbody>
		</table>
		`, //
		data = {
			songs : process(source)
		};

		document.querySelector("div#songs").innerHTML = Template.compile(template)(data);

		//pre-load
		$.ajax({
			url : "album/singerId_" + PageScope.params.id + "/1",
			type : "GET",
			dataType : "json"
		}).done(function(source){
			Excutor({
				source : source,
				data : {
					urlPrefix : "album/singerId_" + PageScope.params.id + "/"
				},
				before : function(source, data, excutor){
					source = process(source);
					data.pageBean = source;
					data.albums = data.pageBean.beans;
				},
				excute : function(source, data, excutor){
					var template = `
					<ul class="albums">
						{{each albums as album}}
						<li class="album">
							<div class="image">
								<a href="#album?id={{album.id}}">
									<img src="{{album.icon}}" title="{{album.name}}">
								</a>
								<div class="image_left">
									<span class="circle"></span>
									<span class="rectangle"></span>
									<span class="triangle"></span>
								</div>
								<i title="播放"></i>
							</div>
							<a title="{{album.name}}" class="name" href="#album?id={{album.id}}">{{album.name | lengthLimit:9}}</a>
							<span class="time">{{album.time | dateFormatter:'yyyy.MM.dd'}}</span>
						</li>
						{{/each}}
					</ul>
					<ul id="pages" class="buttons">
					</ul>
					`;
					document.querySelector("div#albums").innerHTML = Template.compile(template)(data);
					FUNCTION.loadPages("ul#pages", data, excutor);
				}
			}).excute();
		});
		$.ajax({
			url : "singer/" + PageScope.params.id + "/introduction",
			type : "GET",
			dataType : "json"
		}).done(function(source){
			document.querySelector("p#a").innerHTML = process(source);
		});

		var songs = document.querySelector("div#songs"), //
		albums = document.querySelector("div#albums"), //
		introduction = document.querySelector("div#introduction");
		document.querySelector("ul#head").addEventListener("click",function(e){
			if(e.target.id == ""){
				alert("暂无");
				return;
			}
			songs.style.display = "none";
			albums.style.display = "none";
			introduction.style.display = "none";
			switch(e.target.id){
				case  "songs" :
					songs.style.display = "block";
					break;
				case "albums" :
					albums.style.display = "block";
					break;
				case "introduction" :
					introduction.style.display = "block";
					break;
				default :
			}
			var tag = e.target;
			while(tag.nextSibling){
				tag.nextSibling.className = "";
				tag = tag.nextSibling;
			}
			while(tag.previousSibling){
				tag.previousSibling.className = "";
				tag = tag.previousSibling;
			}
			e.target.className = "now";
		})
	});

	function loadIntroduction(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}

		introduction = data.data;
		$("#introductionHead").addClass("now") //
		.siblings().removeClass("now");

		var $introduction = $("#introduction");
		$introduction.removeAttr("style") //
		.siblings().css("display", "none");

		if (data.code == 200) {
			$introduction.find("p").text(introduction) //
			.parent().removeAttr("style") //
			.siblings().css("display", "none");
		}
	}

}())
