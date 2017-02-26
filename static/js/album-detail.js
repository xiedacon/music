(function() {
	var id = PageScope.params.id;

	$.ajax({
		url : "album/" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class="entityMessage_left">
			<img src="{{album.icon}}">
			<div class="image_left">
				<span class="circle"></span>
				<span class="rectangle"></span>
				<span class="triangle"></span>
			</div>
		</div>
		<div class="entityMessage_right">
			<div class="label">
				<span class="content">专辑</span>
				<span class="triangle"><i></i></span>
			</div>
			<h2>
				{{album.name}}
				{{if album.remark}}<span class="remark">{{album.remark}}</span>{{/if}}
			</h2>
			<div class="creator_time_company">
				<div class="creator">
					歌手：
					<a class="creatorName" title="{{album.singerName}}" href="#singer?id={{album.singerId}}">{{album.singerName}}</a>
				</div>
				<span class="createTime">发行时间：{{album.createTime | dateFormatter:'yyyy-MM-dd'}} 创建</span>
				<span class="createCompany">发行公司：{{album.createCompany}}</span>
			</div>
			<div class="buttons">
				<div class="play_addToPlayList">
					<span class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('albumId','{{album.id}}')">
						<i class="icomoon"></i>
						<span>播放</span>
					</span>
					<span class="addToPlaylist icomoon" onclick="MMR.get('music').batchAdd('albumId','{{album.id}}')"></span>
				</div>
				<span class="collection button">
					<i class="icomoon"></i>收藏
				</span>
				<span class="share button">
					<i class="icomoon"></i>{{album.shareNum}}
				</span>
				<span class="download button">
					<i class="icomoon"></i>下载
				</span>
				<span class="comment button">
					<i class="icomoon"></i>{{album.commentNum}}
				</span>
			</div>
		</div>
		<div class="details">
			<span class="title">专辑介绍：</span>
			<p class="introduction">
				介绍：
				{{if album.introduction}}
				{{album.introduction | lengthLimit:'195'}}
				{{else}}
				无
				{{/if}}
			</p>
			<span class="show_hidden" style="display: none">展开<i></i></span>
		</div>
		`, //
		data = {
			album : process(source)
		};
		document.querySelector("div#album").innerHTML = Template.compile(template)(data);
	});
	/*
	function loadAlbum(data) {

		if (album.introduction) {
			var introduction_limit = limitStringLength(album.introduction, 195);
			$albumEle.find(".details .introduction").attr({
				"data-content" : album.introduction
			}).text("介绍：" + introduction_limit);
			if (introduction_limit !== album.introduction) {
				$albumEle.find(".details .show_hidden").removeAttr("style").click(function() {
					var $introduction = $(this).siblings(".introduction");
					var data_content = $introduction.attr("data-content");
					var content = $introduction.text();
					$introduction.attr("data-content", content);
					$introduction.text(data_content);

					if ($(this).text() === "展开") {
						$(this).text("收起");
					} else {
						$(this).text("展开");
					}
				});
			}
		}
		$(".songList .songList_detail").text(album.songNum + " 首歌");
		$(".commentList .commentList_detail").text("共" + album.commentNum + "条评论");
		var user = UserManager.getUser();
		if (user) {
			$(".commentList .newComment_left").attr("src", user.icon);
		}
		$(".commentList .button").attr("onclick", "MMR.addComment('album','" + album.id + "')");
	}
	*/
	$.ajax({
		url : "song/albumId_" + id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var data = {
			songs : process(source)
		}
		FUNCTION.loadSongs("tbody#songList", data);
	});

	$.ajax({
		url : "comment/albumId_" + id + "/1",
		type : "GET",
		dataType : "json"
	}).done(function(source){
		Excutor({
			source : source,
			data : {
				urlPrefix : "comment/albumId_" + id + "/"
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
