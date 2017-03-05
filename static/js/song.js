(function() {
	var id = PageScope.params.id;

	$.ajax({
		url : "song/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="entityMessage_left">
			<img src="{{song.icon}}">
		</div>
		<div class="entityMessage_right">
			<div class="label">
				<span class="content">单曲</span>
				<span class="triangle"><i></i></span>
			</div>
			<h2>
				<span>{{song.name}}</span>
				{{if song.remark}}
				<span class="remark">{{song.remark}}</span>
				{{/if}}
			</h2>
			<div class="creator_time_company">
				<div class="creator">
					歌手：
					<a title="{{song.singerName}}" href="#singer?id={{song.singerId}}" class="creatorName">{{song.singerName}}</a>
				</div>
				<div class="creator album">
					所属专辑：
					<a href="#album?id={{song.albumId}}" title="{{song.albumName}}" class="creatorName">{{song.albumName}}</a>
				</div>
			</div>
			<div class="buttons">
				<div class="play_addToPlayList">
					<span class="play">
						<i class="icomoon"></i>
						<span>播放</span>
					</span>
					<span class="addToPlaylist icomoon"></span>
				</div>
				<span class="collection button">
					<i class="icomoon"></i>收藏
				</span>
				<span class="share button">
					<i class="icomoon"></i>分享
				</span>
				<span class="download button">
					<i class="icomoon"></i>下载
				</span>
				<span class="comment button">{{song.commentNum}}</span>
			</div>
			<div id="lrc" class="details lrc">
				<p>暂无歌词</p>
			</div>
		</div>
		`, //
		data = {
			song : process(source)
		};

		document.querySelector("div#song").innerHTML = Template.compile(template)(data);
		document.querySelector("span#commentNum").innerHTML = "共" + data.song.commentNum + "条评论";
		var user = UserManager.getUser();
		if (user) {
			document.querySelector("img#userIcon").src = user.icon;
		}
		document.querySelector("span#addComment").addEventListener("click",function(){
			MMR.addComment('song', data.song.id);
		});

		$.ajax({
			url : "song/" + id + "/lrc",
			type : "GET",
			dataType : "json"
		}).done(function(source){
			var lrc = process(source);
			if(!lrc){
				return;
			}

			var template = `
			{{if lrc.ar}}
			<p>{{lrc.ar}}</p>
			{{/if}}
			{{if lrc.ti}}
			<p>{{lrc.ti}}</p>
			{{/if}}
			<p>&nbsp;</p>
			{{each lrc.lyrics as lyric}}
			<p data-time="{{lyric.time}}">{{lyric.content}}</p>
			{{/each}}
			`, //
			data = {
				lrc : {
					get ar (){
						var ar = lrc.match(/\[ar:(.*)\]/);
						if(ar){
							return ar[1];
						}
					},
					get ti (){
						var ti = lrc.match(/\[ti:(.*)\]/);
						if(ti){
							return ti[1];
						}
					},
					get lyrics (){
						var lyrics = lrc.match(/\[\d{2}:\d{2}\.\d{2}\].*[\n\r]/g);
						if(lyrics){
							return lyrics.map(function(lyric){
								var temp = lyric.match(/^\[(\d{2}:\d{2}\.\d{2})\](.*)[\n\r]$/);
								return {
									time : temp[1],
									content : temp[2]
								};
							})
						}
					}
				}
			};

			document.querySelector("div#lrc").innerHTML = Template.compile(template)(data);
		})
	})

	$.ajax({
		url : "comment/songId_" + id + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		Excutor({
			source : source,
			data : {
				urlPrefix : "comment/songId_" + id + "/"
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
