(function() {
	var id = PageScope.params.id;

	$.ajax({
		url : "songMenu/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		process(source);

		var template = `
		<div class="songMenuMessage_left entityMessage_left">
			<img src="{{icon}}">
		</div>
		<div class="songMenuMessage_right entityMessage_right">
			<div class="label">
				<span class="content">歌单</span>
				<span class="triangle"><i></i></span>
			</div>
			<h2>{{name}}</h2>
			<div class="creator_time">
				<div class="creator">
					<a title="{{creatorName}}" href="#home?id={{creatorId}}">
						<img src="{{creatorIcon}}" />
					</a>
					<a class="creatorName" href="#home?id={{creatorId}}" title="{{creatorName}}">
						{{creatorName}}
					</a>
				</div>
				<span class="createTime">
					{{createTime | dateFormatter:'yyyy-MM-dd'}}创建
				</span>
			</div>
			<div class="buttons">
				<div class="play_addToPlayList">
					<span class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('songMenuId','{{id}}')">
						<i class="icomoon"></i>
						<span>播放</span>
					</span>
					<span class="addToPlaylist icomoon" title="添加到播放列表" onclick="MMR.get('music').batchAdd('songMenuId','{{id}}')"></span>
				</div>
				<span class="collection button" onclick="MMR.collectSongMenu({{id}})">
					<i class='icomoon'></i>{{collectionNum}}
				</span>
				<span class="share button">
					<i class='icomoon'></i>{{shareNum}}
				</span>
				<span class="download button"><i class="icomoon"></i>下载</span>
				<span class="comment button">
					<i class='icomoon'></i>{{commentNum}}
				</span>
			</div>
			<div class="details">
				<p class="tags">
					标签：
					{{each songMenuSecondTagList as secondTag}}
					<a class='tag' href='#songMenus?secondTagId={{secondTag.id}}&secondTagName={{secondTag.name}}'>
						{{secondTag.name}}
					</a>
					{{/each}}
				</p>
				<p class="introduction">
					{{if introduction}}
					介绍：{{introduction}}
					{{else}}
					介绍：无
					{{/if}}
				</p>
			</div>
		</div>
		`;

		document.querySelector("div#songMenu").innerHTML = Template.compile(template)(source.data);
		document.querySelector("span#songNum").innerHTML = source.data.songNum + "首歌";
		document.querySelector("i#playNum").innerHTML = source.data.playNum;
		document.querySelector("span#commentNum").innerHTML = "共" + source.data.commentNum + "条评论";
		var user = UserManager.getUser();
		if (user) {
			document.querySelector("img#userIcon").src = user.icon;
		}
		document.querySelector("span#addComment").addEventListener("click",function(){
			MMR.addComment('songMenu',source.data.id);
		})
	});
	$.ajax({
		url : "song/songMenuId_" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		process(source);

		FUNCTION.loadSongs("tbody#songList", source);
	});
	$.ajax({
		url : "comment/songMenuId_" + id + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		process(source);

		source.data.urlPrefix = "comment/songMenuId_" + id + "/";
		FUNCTION.loadCommentsAndPages(source);
	});
}())
