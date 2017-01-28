(function() {
	var songId = PageScope.params.songId;
	PageScope.loadForFirst = "comment/songId_" + songId;
	PageScope.loadPageBean = "comment/songId_" + songId + "/";
	PageScope.page = "song";
	
	AJAX({
		url : "song/" + songId,
		success : loadSong
	});
	AJAX({
		url : PageScope.loadForFirst,
		success : FUNCTION.loadForFirst
	});

	function loadSong(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		var song = data.data //
		, $songEle = $("#song");

		$songEle.find(".entityMessage_left img").attr({
			"src" : song.icon
		});
		$songEle.find(".entityMessage_right h2 span").not(".remark").text(song.name);
		if (song.remark) {
			$songEle.find(".entityMessage_right h2 .remark").text(song.remark);
		}
		$songEle.find(".creator_time_company .creator .creatorName").attr({
			"title" : song.singerName,
			"data-href" : "singer?singerId=" + song.singerId
		}).text(song.singerName);
		$songEle.find(".creator_time_company .album .creatorName").attr({
			"title" : song.albumName,
			"data-href" : "album?albumId=" + song.albumId
		}).text(song.albumName);
		$songEle.find(".buttons .comment").html("<i class='icomoon'></i>(" + song.commentNum + ")");

		// 获取歌词
		AJAX({
			url : "song/" + PageScope.params.songId + "/lrc",
			success : function(data) {
				if (data.code == 200 && data.data) {
					var $lrcEle = $songEle.find(".details");
					$lrcEle.children("p").remove();
					$lrcEle.children(".show_hidden").removeAttr("style").click(function() {
						if ($(this).attr("data-flag") === "close") {
							$(this).html("收起<i style='transform: translate(-125%, -25%) rotate(225deg);'></i>").attr("data-flag", "open");
							$(this).parent().css({
								"height" : "inherit"
							})
						} else {
							$(this).html("展开<i></i>").attr("data-flag", "close");
							$(this).parent().css({
								"height" : "300px"
							})
						}
					});
					var lrc = data.data;
					var ar = lrc.match(/(?:\[ar:)(.*)(?:\])/);
					var ti = lrc.match(/(?:\[ti:)(.*)(?:\])/);

					if (ar) {
						$lrcEle.append("<p>" + ar[1] + "</p>");
					}
					if (ti) {
						$lrcEle.append("<p>" + ti[1] + "</p>");
						$lrcEle.append("<p>&nbsp;</p>");
					}

					var ps = lrc.match(/\[\d{2}:\d{2}\.\d{2}\].*[\n\r]/g);
					var p;
					for (var i = 0; i < ps.length; i++) {
						p = ps[i];
						var strs = p.match(/(\[\d{2}:\d{2}\.\d{2}\])(.*)[\n\r]/);
						$lrcEle.append("<p data-time=" + strs[1] + ">" + strs[2] + "</p>");
					}
				}
			}
		})

		$(".commentList .commentList_detail").text("共" + song.commentNum + "条评论");
		var user = UserManager.getUser();
		if (user) {
			$(".commentList .newComment_left").attr("src", user.icon);
		}
		$(".commentList .button").attr("onclick", "MMR.addComment('song','" + song.id + "')");
	}
}())
