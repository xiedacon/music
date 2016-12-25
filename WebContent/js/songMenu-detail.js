(function() {
	var id = PageScope.params.id, //
	page = "songMenu", //
	loadForFirst = "comment/songMenuId_" + id, //
	loadPageBean = "comment/songMenuId_" + id + "/";

	$.ajax({
		url : "songMenu/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data){
		process(data);

		var template = `<div class="songMenuMessage_left entityMessage_left">
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
		</div>`;

		document.querySelector("div#songMenu").innerHTML = Template.compile(template)(data.data);
		document.querySelector("span#songNum").innerHTML = data.data.songNum + "首歌";
		document.querySelector("i#playNum").innerHTML = data.data.playNum;
		document.querySelector("span#commentNum").innerHTML = "共" + data.data.commentNum + "条评论";
		var user = UserManager.getUser();
		if (user) {
			document.querySelector("img#userIcon").src = user.icon;
		}
		document.querySelector("span#addComment").addEventListener("onclick",function(){
			MMR.addComment('songMenu',data.data.id);
		})
	});
	$.ajax({
		url : "song/songMenuId_" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data){
		process(data);

		var template = `{{each data as song index}}
			<tr id="{{song.id}}" class="{{if index%2==0}}{{else}}singular{{/if}}">
				<td>
					<span class="num">{{index + 1}}</span>
					<i class="play icomoon" onclick="MMR.get('music').addThenPlay('aaa');"></i>
				</td>
				<td>
					<a class="songName" href="#song?id={{song.id}}" title="{{song.name}}">
						{{song.name | lengthLimit:'20'}}
					</a>
				</td>
				<td>
					<span class="time">{{song.time}}</span>
					<p class="hidden">
						<span class="addToPlaylist icomoon" onclick="MMR.get('music').add('{{song.id}}');"></span>
						<span class="collection icomoon" onclick="MMR.get('collection').collect('{{song.id}}');"></span>
						<span class="share icomoon"></span>
						<span class="download icomoon"></span>
					</p></td>
				<td>
					<a class="songer" href="#singer?id={{song.singerId}}" title="{{song.singerName}}">
						{{song.singerName | lengthLimit:'5'}}
					</a>
				</td>
				<td>
					<a class="special" href="#album?id={{song.albumId}}" title="{{song.albumName}}">
						{{song.albumName | lengthLimit:'7'}}
					</a>
				</td>
			</tr>
		{{/each}}`;
		document.querySelector("tbody#songList").innerHTML = Template.compile(template)(data);
	});
	$.ajax({
		url : "comment/songMenuId_" + id,
		type : "GET",
		dataType : "json"
	}).done(function(data){
		process(data);

		var template = `
			{{if hotList.length > 0}}
			<h3>最热评论<span>{{hotList.length}}</span></h3>
			{{each hotList as comment}}
			<li class="comment">
				<a href="#home?id={{comment.creatorId}}">
					<img src="{{comment.creatorIcon}}">
				</a>
				<div class="comment_left">
					<p class="comment_material">
						<a class="name" href="#home?id={{comment.creatorId}}" title="{{comment.creatorName}}">
							{{comment.creatorName}}
						</a>
						<span class="content">
							:{{#comment.content}}
						</span>
					</p>
					<p class="comment_reply" style="display: none">
						<i></i> <a class="name" href="javascript:void(0);" data-href="" onclick="jump(this);"></a> <span class="content"></span>
					</p>
					<div class="comment_bottom">
						<span class="createTime">
							{{comment.createTime | dateFormatter:'MM月dd号 HH:mm'}}
						</span>
						<div class="right">
							<p>
								<i class="icomoon"></i>
								<span class="num" onclick="MMR.agreeComment(this,'{{comment.id}}');">
									{{comment.agreeNum}}
								</span>
							</p>
							<span class="interval">|</span> <span class="reply">回复</span>
						</div>
					</div>
				</div>
			</li>
			{{/each}}
			{{/if}}
			<h3>最新评论<span>{{pageBean.count}}</span></h3>
			{{each pageBean.beans as comment}}
			<li class="comment">
				<a href="#home?id={{comment.creatorId}}">
					<img src="{{comment.creatorIcon}}">
				</a>
				<div class="comment_left">
					<p class="comment_material">
						<a class="name" href="#home?id={{comment.creatorId}}" title="{{comment.creatorName}}">
							{{comment.creatorName}}
						</a>
						<span class="content">
							:{{#comment.content}}
						</span>
					</p>
					<p class="comment_reply" style="display: none">
						<i></i> <a class="name" href="javascript:void(0);" data-href="" onclick="jump(this);"></a> <span class="content"></span>
					</p>
					<div class="comment_bottom">
						<span class="createTime">
							{{comment.createTime | dateFormatter:'MM月dd号 HH:mm'}}
						</span>
						<div class="right">
							<p>
								<i class="icomoon"></i>
								<span class="num" onclick="MMR.agreeComment(this,'{{comment.id}}');">
									{{comment.agreeNum}}
								</span>
							</p>
							<span class="interval">|</span> <span class="reply">回复</span>
						</div>
					</div>
				</div>
			</li>
			{{/each}}
		`;

		document.querySelector("ul#commentList").innerHTML = Template.compile(template)(data.data);
	});
}())
