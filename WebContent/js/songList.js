(function() {
	var songListId = PageScope.params.songListId;
	PageScope.loadForFirst = "comment/songListId_" + songListId;
	PageScope.loadPageBean = "comment/songListId_" + songListId + "/";
	PageScope.page = "songList";
	AJAX({
		url : "songList/" + PageScope.params.songListId,
		success : loadSongList
	});
	AJAX({
		url : "songList",
		success : loadSongLists
	});
	AJAX({
		url : "song/songListId_" + PageScope.params.songListId,
		success : loadSongs
	});
	AJAX({
		url : PageScope.loadForFirst,
		success : FUNCTION.loadForFirst
	});

	function loadSongLists(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var songLists = data.data //
		, $specialList = $("#specialList");
		var $globeList = $("#globeList");
		var $prototype = $specialList.find(".prototype").clone().removeClass("prototype");

		var songList, $songListEle;

		for (var i = 0; i < songLists.length; i++) {
			songList = songLists[i];
			$songListEle = $prototype.clone();

			$songListEle.attr({
				"data-href" : "songList?songListId=" + songList.id
			});
			if (songList.id === PageScope.params.songListId) {
				$songListEle.addClass("now").removeAttr("onclick");
			}

			$songListEle.find("img").attr({
				"title" : songList.name,
				"src" : songList.icon
			})
			$songListEle.find(".name").attr({
				"title" : songList.name
			}).text(songList.name)
			$songListEle.find(".time").text(songList.refreshRate);

			if (songList.globe) {
				$globeList.append($songListEle);
			} else {
				$specialList.append($songListEle);
			}
		}
	}

	function loadSongList(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var songList = data.data //
		, $songListEle = $("#_songList");

		$songListEle.find(".entityMessage_left img").attr({
			"src" : songList.icon
		});

		$songListEle.find(".entityMessage_right h2").html(songList.name);
		$songListEle.find(".createTime")//
		.html(
				"<i class='icomoon'></i>" + new DateFormatter("最近更新：MM月dd日 ").format(songList.refreshDate) + "<span class='frequency'>("
						+ songList.refreshRate + ")</span>");

		$songListEle.find(".play_addToPlayList .play").attr({
			"onclick" : "hiddenDiv.playFromSongList('" + songList.id + "')"
		});
		$songListEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "hiddenDiv.addFromSongList('" + songList.id + "')"
		});
		$songListEle.find(".buttons .collection").html("<i class='icomoon'></i>" + songList.collectionNum);
		$songListEle.find(".buttons .share").html("<i class='icomoon'></i>" + songList.shareNum);
		$songListEle.find(".buttons .comment").html("<i class='icomoon'></i>" + songList.commentNum);

		$(".songList .songList_detail").text(songList.songNum + " 首歌");
		$(".songList .top_right .num").text(songList.playNum);
		$(".commentList .commentList_detail").text("共" + songList.commentNum + "条评论");
		var user = UserManager.getUser();
		if (user) {
			$(".commentList .newComment_left").attr("src", user.icon);
		}
		$(".commentList .button").attr("onclick", "MMR.addComment('songList','" + songList.id + "')");
	}

	function loadSongs(songs) {
		FUNCTION.loadSongs(songs, function($songEle, song, i) {
			if (i < 3) {
				$songEle.addClass("top3");
				$songEle.find("td").eq(1) //
				.prepend("<img src='" + song.icon + "' data-href='song/{" + song.id + "}' onclick='jump(this);'>")
			}

			var $rankChangeEle = $songEle.find(".riseOrDecline");

			if (song.rankChange < 0) {
				$rankChangeEle //
				.addClass("decline") //
				.html("<i class='icomoon'></i> <span>" + song.rankChange.toString().substring(1) + "</span>");
			} else if (song.rankChange === 0) {
				$rankChangeEle //
				.addClass("none") //
				.html("<i class='icomoon'></i> <span>" + song.rankChange + "</span>");
			} else if (song.rankChange > 0) {
				$rankChangeEle //
				.addClass("rise") //
				.html("<i class='icomoon'></i> <span>" + song.rankChange + "</span>");
			} else {
				$rankChangeEle //
				.addClass("new") //
				.html("<i class='icomoon'>NEW</i>");
			}
		})
	}
}())
