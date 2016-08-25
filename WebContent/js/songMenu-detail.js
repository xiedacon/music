(function() {
	var songMenuId = PageScope.params.songMenuId;
	PageScope.loadForFirst = "comment/s/songMenuId_" + songMenuId;
	PageScope.loadPageBean = "comment/s/songMenuId_" + songMenuId + "/";
	AJAX({
		url : "songMenu/" + songMenuId,
		success : loadSongMenu
	});
	AJAX({
		url : "song/s/songMenuId_" + songMenuId,
		success : FUNCTION.loadSongs
	});
	AJAX({
		url : "comment/s/songMenuId_" + songMenuId,
		success : FUNCTION.loadForFirst
	});

	function loadSongMenu(songMenu) {
		var $songMenuEle = $("#songMenu");
		$songMenuEle.find(".songMenuMessage_left img").attr({
			"src" : songMenu.icon
		});
		$songMenuEle.find(".songMenuMessage_right h2").text(songMenu.name);
		$songMenuEle.find(".creator_time .creator img").attr({
			"src" : songMenu.creatorIcon,
			"title" : songMenu.creatorName,
			"data-href" : "home?userId=" + songMenu.creatorId
		});
		$songMenuEle.find(".creator .creatorName").attr({
			"title" : songMenu.creatorName,
			"data-href" : "home?userId=" + songMenu.creatorId
		}).text(songMenu.creatorName);
		$songMenuEle.find(".createTime").text(new DateFormatter("yyyy-MM-dd").format(songMenu.createTime) + " 创建");
		$songMenuEle.find(".play_addToPlayList .play").attr({
			"onclick" : "MMR.get('music').batchAddThenPlay('songMenuId','" + songMenu.id + "')"
		});
		$songMenuEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "MMR.get('music').batchAdd('songMenuId','" + songMenu.id + "')"
		});
		$songMenuEle.find(".buttons .share").html("<i class='icomoon'></i>" + songMenu.collectionNum);
		$songMenuEle.find(".buttons .collection").html("<i class='icomoon'></i>" + songMenu.shareNum);
		$songMenuEle.find(".buttons .comment").html("<i class='icomoon'></i>" + songMenu.commentNum);
		var $tagsEle = $songMenuEle.find(".details .tags");
		var $tagEle;
		for (var i = 0; i < songMenu.songMenuSecondTagList.length; i++) {
			$tagEle = $(" <a class='tag' href='javascript:void(0);' onclick='jump(this);'></a>");
			$tagEle.attr(
					{
						"data-href" : "songMenus?secondTagId=" + songMenu.songMenuSecondTagList[i].id + "&secondTagName="
								+ songMenu.songMenuSecondTagList[i].name
					}).text(songMenu.songMenuSecondTagList[i].name);
			$tagsEle.append($tagEle);
		}
		if (songMenu.introduction) {
			$songMenuEle.find(".details .introduction").text("介绍：" + songMenu.introduction);
		}
		$(".songList .songList_detail").text(songMenu.songNum + " 首歌");
		$(".songList .songList_top .num").text(songMenu.playNum);
		$(".commentList .commentList_detail").text("共" + songMenu.commentNum + "条评论");
	}
}())
