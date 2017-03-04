(function() {
	$.ajax({
		url : "songList/" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="entityMessage_left">
			<img src="{{songList.icon}}">
		</div>
		<div class="entityMessage_right">
			<h2>{{songList.name}}</h2>
			<p class="createTime">
				<i class='icomoon'></i>
				{{songList.refreshDate | dateFormatter:'最近更新：MM月dd日 '}}
				<span class='frequency'>{{songList.refreshRate}}</span>
			</p>
			<div class="buttons">
				<div class="play_addToPlayList">
					<span class="play" onclick="MMR.get('music').batchAddThenPlay('songListId','{{songList.id}}')">
						<i class="icomoon"></i>
						<span>播放</span>
					</span>
					<span class="addToPlaylist icomoon" onclick="MMR.get('music').batchAdd('songListId','{{songList.id}}"></span>
				</div>
				<span class="collection button">
					<i class="icomoon"></i>{{songList.collectionNum}}
				</span>
				<span class="share button">
					<i class="icomoon"></i>{{songList.shareNum}}
				</span>
				<span class="download button">
					<i class="icomoon"></i>下载
				</span>
				<span class="comment button">
					<i class="icomoon"></i>{{songList.commentNum}}
				</span>
			</div>
		</div>
		`, //
		data = {
			songList : process(source)
		};

		document.querySelector("div#songList").innerHTML = Template.compile(template)(data);

		document.querySelector("span#songNum").innerHTML = data.songList.songNum + "首歌";
		document.querySelector("i#playNum").innerHTML = data.songList.playNum;
		document.querySelector("span#commentNum").innerHTML = "共" + data.songList.commentNum + "条评论";
		var user = UserManager.getUser();
		if (user) {
			document.querySelector("img#userIcon").src = user.icon;
		}
		document.querySelector("span#addComment").addEventListener("click",function(){
			MMR.addComment('songList', data.songList.id);
		})
	});

	$.ajax({
		url : "songList",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<ul id="specialList" class="select">
			<h3>云音乐特色榜</h3>
			{{each specialList as songList}}
			<a class="option {{if songList.id == `+ PageScope.params.id +`}}now{{/if}}" {{if songList.id != ` + PageScope.params.id + `}}href="#songList?id={{songList.id}}"{{/if}}>
				<img title="{{songList.name}}" src="{{songList.icon}}">
				<div class="option_right">
					<span title="{{songList.name}}" class="name">{{songList.name}}</span>
					<span class="time">{{songList.refreshRate}}</span>
				</div>
			</a>
			{{/each}}
		</ul>
		<div id="globeList" class="select">
			<h3>全球媒体榜</h3>
			{{each globeList as songList}}
			<a class="option {{if songList.id == `+ PageScope.params.id +`}}now{{/if}}" {{if songList.id != ` + PageScope.params.id + `}}href="#songList?id={{songList.id}}"{{/if}}>
				<img title="{{songList.name}}" src="{{songList.icon}}">
				<div class="option_right">
					<span title="{{songList.name}}" class="name">{{songList.name}}</span>
					<span class="time">{{songList.refreshRate}}</span>
				</div>
			</a>
			{{/each}}
		</div>
		`, //
		data = {
			specialList : [],
			globeList : []
		};

		process(source).forEach(function(songList){
			if(songList.globe){
				data.globeList.push(songList);
			}else{
				data.specialList.push(songList);
			}
		});

		document.querySelector("div#songLists").innerHTML = Template.compile(template)(data);
	})

	$.ajax({
		url : "song/songListId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		{{each songs as song index}}
		<tr id="{{song.id}}" class="{{if index%2==0}}{{else}}singular{{/if}}{{if index<3}}top3{{/if}}">
			<td>
				<span class="num">{{index + 1}}</span>
				{{if song.rankChange < 0}}
				<div class="riseOrDecline decline">
					<i class='icomoon'></i> <span>{{song.rankChange.toString().substring(1)}}</span>
				</div>
				{{else if song.rankChange == 0}}
				<div class="riseOrDecline none">
					<i class='icomoon'></i> <span>{{song.rankChange}}</span>
				</div>
				{{else if song.rankChange > 0}}
				<div class="riseOrDecline rise">
					<i class='icomoon'></i> <span>{{song.rankChange}}</span>
				</div>
				{{else}}
				<div class="riseOrDecline new">
					<i class='icomoon'>NEW</i>
				</div>
				{{/if}}
			</td>
			<td>
				{{if index<3}}
				<a href="#song?id={{song.id}}">
					<img src="{{song.icon}}">
				</a>
				{{/if}}
				<i class="play icomoon" onclick="MMR.get('music').addThenPlay('aaa');"></i>
				<a class="songName" href="#song?id={{song.id}}" title="{{song.name}}">
					{{song.name | lengthLimit:'20'}}
				</a>
				<span class="remark"></span>
			</td>
			<td>
				<span class="time">{{song.time}}</span>
				<p class="hidden">
					<span class="addToPlaylist icomoon" onclick="MMR.get('music').add('{{song.id}}');"></span>
					<span class="collection icomoon" onclick="MMR.get('collection').collect('{{song.id}}');"></span>
					<span class="share icomoon"></span>
					<span class="download icomoon"></span>
				</p>
			</td>
			<td>
				<a class="songer" href="#singer?id={{song.singerId}}" title="{{song.singerName}}">
					{{song.singerName | lengthLimit:'5'}}
				</a>
			</td>
		</tr>
		{{/each}}
		`, //
		data = {
			songs : process(source)
		};

		document.querySelector("tbody#songs").innerHTML = Template.compile(template)(data);
	});

	$.ajax({
		url : "comment/songListId_" + PageScope.params.id + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		Excutor({
			source : source,
			data : {
				urlPrefix : "comment/songListId_" + PageScope.params.id + "/"
			},
			before : function(source, data, excutor){
				source = process(source);
				data.hotList = source.hotList;
				data.pageBean = source.pageBean;
			},
			excute : function(source, data, excutor){
				FUNCTION.loadComments("ul#commentList", data);
				FUNCTION.loadPages("ul#pages", data, excutor);
				FUNCTION.loadEmojis();
			}
		}).excute();
	});
}())
