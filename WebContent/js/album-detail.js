(function() {
	var albumId = PageScope.params.albumId;
	PageScope.loadForFirst = "comment/albumId_" + albumId;
	PageScope.loadPageBean = "comment/albumId_" + albumId + "/";
	PageScope.page = "album";
	AJAX({
		url : "album/" + albumId,
		success : loadAlbum
	});
	AJAX({
		url : "song/albumId_" + albumId,
		success : FUNCTION.loadSongs
	});
	AJAX({
		url : PageScope.loadForFirst,
		success : FUNCTION.loadForFirst
	});

	function loadAlbum(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var album = data.data //
		, $albumEle = $("#album");
		$albumEle.find(".entityMessage_left img").attr({
			"src" : album.icon
		});
		var albumName = album.name;
		if (album.remark) {
			albumName += "<span class='remark'>" + album.remark + "</span>";
		}
		$albumEle.find(".entityMessage_right h2").html(albumName);
		$albumEle.find(".creator_time_company .creator .creatorName").attr({
			"title" : album.singerName,
			"data-href" : "singer?singerId=" + album.singerId
		}).text(album.singerName);
		$albumEle.find(".creator_time_company .createTime").text(new DateFormatter("发行时间：yyyy-MM-dd 创建").format(album.createTime));
		$albumEle.find(".creator_time_company .createCompany").text("发行公司：" + album.createCompany);
		$albumEle.find(".play_addToPlayList .play").attr({
			"onclick" : "MMR.get('music').batchAddThenPlay('albumId','" + album.id + "')"
		});
		$albumEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "MMR.get('music').batchAdd('albumId','" + album.id + "')"
		});
		$albumEle.find(".buttons .share").html("<i class='icomoon'></i>" + album.shareNum);
		$albumEle.find(".buttons .comment").html("<i class='icomoon'></i>" + album.commentNum);

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

}())
