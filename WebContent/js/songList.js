(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);

	var rootUrl = realUrl.split("/")[0];
	pageScope.setAttribute("songListId", realUrl.split("/")[1]);

	ajaxConfigs.setAjaxConfigs({
		url : rootUrl + "/" + pageScope.getAttribute("songListId"),
		success : loadSongList
	}, {
		url : rootUrl,
		success : loadSongLists
	}, {
		url : rootUrl + "/" + pageScope.getAttribute("songListId") + "/songs",
		success : loadSongs
	}, {
		url : rootUrl + "/" + pageScope.getAttribute("songListId") + "/comments",
		success : loadForFirst
	});

	ajaxConfigs.startAjaxs();

	function loadSongLists(songLists) {
		var $specialList = $("#specialList");
		var $globeList = $("#globeList");
		var $prototype = $specialList.find(".prototype").clone().removeClass("prototype");

		var songList, $songListEle;

		for (var i = 0; i < songLists.length; i++) {
			songList = songLists[i];
			$songListEle = $prototype.clone();

			$songListEle.attr({
				"data-href" : "songList/{" + songList.id + "}"
			});
			if (songList.id === pageScope.getAttribute("songListId")) {
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

	function loadSongList(songList) {
		var $songListEle = $("#songList");

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

		chiping();
	}

	function loadSongs(songs) {
		var $songsEle = $("#songs");
		var $prototype = $songsEle.find(".prototype").clone().removeClass("prototype");
		var $songEle, song;

		for (var i = 0; i < songs.length; i++) {
			song = songs[i];
			$songEle = $prototype.clone();
			$songEle.attr({
				"id" : song.id
			});
			if (i % 2 == 0) {
				$songEle.addClass("singular");
			}

			if (i < 3) {
				$songEle.addClass("top3");
				$songEle.find("td").eq(1) //
				.prepend("<img src='" + song.icon + "' data-href='song/{" + song.id + "}' onclick='jump(this);'>")
			}

			$songEle.find(".num").text(song.rank);
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

			$songEle.find(".songName").attr({
				"data-href" : "song/{" + song.id + "}",
				"title" : song.name
			}).text(song.name);
			if (song.remark) {
				$songEle.find(".remark").text("-(" + song.remark + ")");
			}
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "hiddenDiv.addToPlayList('" + song.id + "')"
			});
			$songEle.find(".time").text(song.time);
			$songEle.find(".songer").attr({
				"data-href" : "singer/{" + song.singerId + "}",
				"title" : song.singerName
			}).text(limitStringLength(song.singerName, 5));

			$songsEle.append($songEle);
		}

		chiping();
	}

	function loadForFirst(comments) {
		var hotList = comments.hotList;
		var $commentListEle = $("#commentList");
		$commentListEle.children().not(".prototype").remove();

		var $prototype = $commentListEle.find(".prototype").clone().removeClass("prototype");
		var $commentEle, comment;
		if (hotList.length > 0) {
			$commentListEle.append("<h3>最热评论<span>(" + hotList.length + ")</span></h3>")
			for (var i = 0; i < hotList.length; i++) {
				$commentEle = $prototype.clone();
				comment = hotList[i];
				$commentEle.find("img").attr({
					"src" : comment.creator.icon,
					"data-href" : "home/{" + comment.creator.id + "}"
				});
				$commentEle.find(".comment_material .name").attr({
					"data-href" : "home/{" + comment.creator.id + "}",
					"title" : comment.creator.name
				}).text(comment.creator.name);
				$commentEle.find(".comment_material .content").text(":" + comment.content);
				$commentEle.find(".comment_bottom .createTime").text(new DateFormatter("MM月dd号 HH:mm").format(comment.createTime));
				$commentEle.find(".comment_bottom .right .num").text(comment.agreeNum);

				$commentListEle.append($commentEle);
			}
		}

		loadPageBean(comments.pageBean);

	}

	function loadPageBean(pageBean) {
		var newList = pageBean.beans;
		var $pagesEle = $("#pages");
		$pagesEle.empty();
		var $commentListEle = $("#commentList");
		$commentListEle.children().not(".prototype").remove();
		var $prototype = $commentListEle.find(".prototype").clone().removeClass("prototype");
		var $commentEle, comment;

		if (newList.length > 0) {
			if (pageBean.page === 1) {
				$commentListEle.append("<h3>最新评论<span>(" + pageBean.count + ")</span></h3>");
			}

			for (var i = 0; i < newList.length; i++) {
				$commentEle = $prototype.clone();
				comment = newList[i];
				$commentEle.find("img").attr({
					"src" : comment.creator.icon,
					"data-href" : "home/{" + comment.creator.id + "}"
				});
				$commentEle.find(".comment_material .name").attr({
					"data-href" : "home/{" + comment.creator.id + "}",
					"title" : comment.creator.name
				}).text(comment.creator.name);
				$commentEle.find(".comment_material .content").text(":" + comment.content);
				$commentEle.find(".comment_bottom .createTime").text(new DateFormatter("MM月dd号 HH:mm").format(comment.createTime));
				$commentEle.find(".comment_bottom .right .num").text("(" + comment.agreeNum + ")");

				$commentListEle.append($commentEle);
			}
		}

		if (pageBean.totalPage !== 0 && pageBean.totalPage !== 1) {
			var begin = pageBean.page - 2;
			var end = pageBean.page + 2;
			if (pageBean.page < 3) {
				begin = 1;
				end = 5;
			}
			if (pageBean.totalPage < 5) {
				end = pageBean.totalPage;
			} else if (end > pageBean.totalPage) {
				end = pageBean.totalPage;
			}

			var $previousPage = $("<li class='button' data-page='" + (pageBean.page - 1) + "'>< 上一页</li>");
			if (pageBean.page === 1) {
				$previousPage.addClass("unable");
			} else {
				$previousPage.addClass("enable");
			}
			$pagesEle.append($previousPage);

			var $pageEle;
			for (var i = begin; i < end + 1; i++) {
				$pageEle = $("<li class='button num' data-page='" + i + "'>" + i + "</li>");
				if (i === pageBean.page) {
					$pageEle.removeClass("num").addClass("now");
				}
				$pagesEle.append($pageEle);
			}

			var $nextPage = $("<li class='button' data-page='" + (pageBean.page + 1) + "'>下一页 ></li>");
			if (pageBean.page === pageBean.totalPage) {
				$nextPage.addClass("unable");
			} else {
				$nextPage.addClass("enable");
			}
			$pagesEle.append($nextPage);

			chiping();

			$pagesEle.find(".button").not(".unable").not(".now").click(function() {
				var page = $(this).attr("data-page");
				if (page == 1) {
					ajaxConfigs.startAjax(realUrl + "/comments");
					return;
				}

				if (!ajaxConfigs.hasAjaxConfig(realUrl + "/comments/" + page)) {
					ajaxConfigs.setAjaxConfig({
						url : realUrl + "/comments/" + page,
						success : function(pageBean) {
							loadPageBean(pageBean);
						}
					});
				}

				ajaxConfigs.startAjax(realUrl + "/comments/" + page);
			})
		}
	}

	// 保持高度相等
	function chiping() {
		$(".material_right").css("height", $(".material_left")[0].clientHeight);
	}
}())
