(function() {
	var id = PageScope.params.id;

	$.ajax({
		url : "songMenu/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="songMenuMessage_left entityMessage_left">
			<img src="{{songMenu.icon}}">
		</div>
		<div class="songMenuMessage_right entityMessage_right">
			<div class="label">
				<span class="content">歌单</span>
				<span class="triangle"><i></i></span>
			</div>
			<h2>{{songMenu.name}}</h2>
			<div class="creator_time">
				<div class="creator">
					<a title="{{songMenu.creatorName}}" href="#home?id={{songMenu.creatorId}}">
						<img src="{{songMenu.creatorIcon}}" />
					</a>
					<a class="creatorName" href="#home?id={{songMenu.creatorId}}" title="{{songMenu.creatorName}}">
						{{songMenu.creatorName}}
					</a>
				</div>
				<span class="createTime">
					{{songMenu.createTime | dateFormatter:'yyyy-MM-dd'}}创建
				</span>
			</div>
			<div class="buttons">
				<div class="play_addToPlayList">
					<span class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('songMenuId','{{songMenu.id}}')">
						<i class="icomoon"></i>
						<span>播放</span>
					</span>
					<span class="addToPlaylist icomoon" title="添加到播放列表" onclick="MMR.get('music').batchAdd('songMenuId','{{songMenu.id}}')"></span>
				</div>
				<span class="collection button" onclick="MMR.collectSongMenu({{songMenu.id}})">
					<i class='icomoon'></i>{{songMenu.collectionNum}}
				</span>
				<span class="share button">
					<i class='icomoon'></i>{{songMenu.shareNum}}
				</span>
				<span class="download button"><i class="icomoon"></i>下载</span>
				<span class="comment button">
					<i class='icomoon'></i>{{songMenu.commentNum}}
				</span>
			</div>
			<div class="details">
				<p class="tags">
					标签：
					{{each songMenu.songMenuSecondTagList as secondTag}}
					<a class='tag' href='#songMenus?secondTagId={{secondTag.id}}&secondTagName={{secondTag.name}}'>
						{{secondTag.name}}
					</a>
					{{/each}}
				</p>
				<p class="introduction">
					介绍：
					{{if songMenu.introduction}}
					{{songMenu.introduction}}
					{{else}}
					无
					{{/if}}
				</p>
			</div>
		</div>
		`, //
		data = {
			songMenu : process(source)
		};

		document.querySelector("div#songMenu").innerHTML = Template.compile(template)(data);
		document.querySelector("span#songNum").innerHTML = data.songMenu.songNum + "首歌";
		document.querySelector("i#playNum").innerHTML = data.songMenu.playNum;
		document.querySelector("span#commentNum").innerHTML = "共" + data.songMenu.commentNum + "条评论";
		var user = UserManager.getUser();
		if (user) {
			document.querySelector("img#userIcon").src = user.icon;
		}
		document.querySelector("span#addComment").addEventListener("click",function(){
			MMR.addComment('songMenu', data.songMenu.id);
		})
	});

	$.ajax({
		url : "song/songMenuId_" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var data = {
			songs : process(source)
		}
		FUNCTION.loadSongs("tbody#songList", data);
	});

	$.ajax({
		url : "comment/songMenuId_" + id + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		Excutor({
			source : source,
			data : {
				urlPrefix : "comment/songMenuId_" + id + "/"
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
