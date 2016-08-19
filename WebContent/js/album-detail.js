(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);

	ajaxConfigs.setAjaxConfigs({
		url : realUrl,
		success : loadAlbum
	}, {
		url : realUrl + "/songList",
		success : loadSongList
	}, {
		url : realUrl + "/comments",
		success : loadForFirst
	});

	ajaxConfigs.startAjaxs();

	function loadAlbum(album) {
		var $albumEle = $("#album");
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
			"data-href" : "singer/{" + album.singerId + "}"
		}).text(album.singerName);
		$albumEle.find(".creator_time_company .createTime").text(new DateFormatter("发行时间：yyyy-MM-dd 创建").format(album.createTime));
		$albumEle.find(".creator_time_company .createCompany").text("发行公司：" + album.createCompany);
		$albumEle.find(".play_addToPlayList .play").attr({
			"onclick" : "hiddenDiv.playFromAlbum('" + album.id + "')"
		});
		$albumEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "hiddenDiv.addFromAlbum('" + album.id + "')"
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
	}
	function loadSongList(songList) {
		var $songListEle = $("#songList");
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
			$songEle.find(".songName").attr({
				"data-href" : "song/{" + song.id + "}",
				"title" : song.name
			}).text(limitStringLength(song.name, 20));
			$songEle.find(".time").text(song.time);
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "hiddenDiv.addToPlayList('" + song.id + "')"
			});
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
