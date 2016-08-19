(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;

	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);
	ajaxConfigs.setAjaxConfigs({
		url : realUrl + "/createdSongMenus",
		success : loadCreatedSongMenus
	}, {
		url : realUrl + "/collectedSongMenus",
		success : loadCollectedSongMenus
	});

	ajaxConfigs.startAjaxs();

	function loadSongMenu(songMenuId) {
		var $songMenus = $(".material_right li").not(".prototype");
		$songMenus.filter("li[data-id='" + songMenuId + "']").addClass("now") //
		$songMenus.filter("li[data-id!='" + songMenuId + "']").removeClass("now");

		var realUrl = "songMenu/" + songMenuId;
		pageScope.setAttribute("sub_realUrl", realUrl);

		ajaxConfigs.setAjaxConfigs({
			url : realUrl,
			success : _loadSongMenu
		}, {
			url : realUrl + "/songList",
			success : loadSongList
		}, {
			url : realUrl + "/comments",
			success : loadForFirst
		});

		ajaxConfigs.startAjaxs(new Array("songMenu/" + songMenuId //
		, "songMenu/" + songMenuId + "/songList" //
		, "songMenu/" + songMenuId + "/comments"));
	}

	function loadCreatedSongMenus(data) {
		var $ele = $("#createdSongMenus");
		if (data.length > 0) {
			$ele.siblings("#createdSongMenusHead").find(".content").text("创建的歌单(" + data.length + ")");
			loadSongMenus($ele, data);
		} else {
			$ele.css("display", "none");
			$ele.siblings("#createdSongMenusHead").css("display", "none");
		}
	}

	function loadCollectedSongMenus(data) {
		var $ele = $("#collectedSongMenus");
		if (data.length > 0) {
			$ele.siblings("#collectedSongMenusHead").find(".content").text("收藏的歌单(" + data.length + ")");
			loadSongMenus($ele, data);
		} else {
			$ele.css("display", "none");
			$ele.siblings("#collectedSongMenusHead").css("display", "none");
		}
	}

	function loadSongMenus($ele, songMenus) {
		var $songMenusEle = $ele;
		var $prototype = $songMenusEle.find(".prototype").clone().removeClass("prototype");
		var $songMenuEle, songMenu;

		for (var i = 0; i < songMenus.length; i++) {
			$songMenuEle = $prototype.clone();
			songMenu = songMenus[i];

			$songMenuEle.find("img").attr({
				"src" : songMenu.icon
			});

			var userId = userDiv.getUserId();

			if (songMenu.userId && userId === songMenu.userId) {
				$songMenuEle.find(".name").text("我" + songMenu.name);
			} else if (songMenu.userId) {
				$songMenuEle.find(".name").text(songMenu.creatorName + songMenu.name);
			} else {
				$songMenuEle.find(".name").text(songMenu.name);
			}
			$songMenuEle.find(".num").text(songMenu.songNum + "首");

			$songMenuEle.attr("data-id", songMenu.id).click(function() {
				loadSongMenu($(this).attr("data-id"));
			});

			if (songMenu.userId === userId) {// 我喜欢的音乐，无法删除或修改
				$songMenusEle.prepend($songMenuEle);
				$songMenuEle.find("img").wrap("<div style='position:relative;'></div>").parent().append(
						"<img src='image/default_songMenu_love.jpg' style='position: absolute;opacity: 0.7;top:0;left:0;'>");
				loadSongMenu(songMenu.id);
				continue;
			}

			$songMenuEle.find(".option_rightF").removeAttr("style");

			if (songMenu.creatorId === userId) {
				$songMenuEle.find(".edit").removeAttr("style").click(function() {
					return false;
				});
			}

			$songMenuEle.find(".delete").click(function() {
				return false;
			});

			$songMenusEle.append($songMenuEle);
		}
	}

	function _loadSongMenu(songMenu) {
		var $songMenuEle = $("#songMenu");
		$songMenuEle.find(".songMenuMessage_left img").attr({
			"src" : songMenu.icon
		});

		var userId = userDiv.getUserId();

		if (songMenu.userId && userId === songMenu.userId) {
			$songMenuEle.find(".songMenuMessage_right h2").text("我" + songMenu.name);
		} else if (songMenu.userId) {
			$songMenuEle.find(".songMenuMessage_right h2").text(songMenu.creatorName + songMenu.name);
		} else {
			$songMenuEle.find(".songMenuMessage_right h2").text(songMenu.name);
		}

		$songMenuEle.find(".creator_time .creator img").attr({
			"src" : songMenu.creatorIcon,
			"title" : songMenu.creatorName,
			"data-href" : "home/{" + songMenu.creatorId + "}"
		});
		$songMenuEle.find(".creator .creatorName").attr({
			"title" : songMenu.creatorName,
			"data-href" : "home/{" + songMenu.creatorId + "}"
		}).text(songMenu.creatorName);
		$songMenuEle.find(".createTime").text(new DateFormatter("yyyy-MM-dd").format(songMenu.createTime) + " 创建");
		$songMenuEle.find(".play_addToPlayList .play").attr({
			"onclick" : "hiddenDiv.playFromSongMenu('" + songMenu.id + "')"
		});
		$songMenuEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "hiddenDiv.addFromSongMenu('" + songMenu.id + "')"
		});
		$songMenuEle.find(".buttons .share").html("<i class='icomoon'></i>" + songMenu.shareNum);

		var user = userDiv.getUser();

		if (user && (user.id === songMenu.creatorId)) {// ||
			// user.collectedSongMenus[songMenu.id]
			$songMenuEle.find(".buttons .collection").addClass("disable");
		} else {
			$songMenuEle.find(".buttons .collection").removeClass("disable")
		}
		$songMenuEle.find(".buttons .collection").html("<i class='icomoon'></i>" + songMenu.collectionNum);

		$songMenuEle.find(".buttons .comment").html("<i class='icomoon'></i>" + songMenu.commentNum);
		var $tagsEle = $songMenuEle.find(".details .tags").empty();
		var $tagEle;
		for (var i = 0; i < songMenu.songMenuSecondTagList.length; i++) {
			$tagEle = $(" <a class='tag' href='javascript:void(0);' onclick='jump(this);'></a>");
			$tagEle.attr({
				"data-href" : "songMenus/{" + songMenu.songMenuSecondTagList[i].id + "}?" + songMenu.songMenuSecondTagList[i].name
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
	function loadSongList(songList) {
		var $songListEle = $("#songList");
		$songListEle.children().not(".prototype").remove();
		var $prototype = $songListEle.find(".prototype").clone().removeClass("prototype");
		var $songEle, song;

		for (var i = 0; i < songList.length; i++) {
			song = songList[i];
			$songEle = $prototype.clone();
			$songEle.attr({
				"id" : song.id
			});
			if (i % 2 != 0) {
				$songEle.addClass("singular");
			}

			$songEle.find(".num").text(song.rank);
			$songEle.find(".play").attr({
				"onclick" : "hiddenDiv.addToPlayListThenPlay('" + song.id + "')"
			});
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "hiddenDiv.addToPlayList('" + song.id + "')"
			});
			$songEle.find(".songName").attr({
				"data-href" : "song/{" + song.id + "}",
				"title" : song.name
			}).text(limitStringLength(song.name, 20));
			$songEle.find(".time").text(song.time);
			$songEle.find(".songer").attr({
				"data-href" : "singer/{" + song.singerId + "}",
				"title" : song.singerName
			}).text(limitStringLength(song.singerName, 5));
			$songEle.find(".special").attr({
				"data-href" : "album/{" + song.albumId + "}",
				"title" : song.albumName
			}).text(limitStringLength(song.albumName, 7));

			$songListEle.append($songEle);
		}
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

			var realUrl = pageScope.getAttribute("sub_realUrl");

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
}())
