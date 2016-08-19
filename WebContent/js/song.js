(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);

	pageScope.setAttribute("id", realUrl.split("/")[1]);
	ajaxConfigs.setAjaxConfigs({
		url : realUrl,
		success : loadSong
	}, {
		url : realUrl + "/comments",
		success : loadForFirst
	});

	ajaxConfigs.startAjaxs();

	function loadSong(song) {
		var $songEle = $("#song");

		$songEle.find(".entityMessage_left img").attr({
			"src" : song.icon
		});
		$songEle.find(".entityMessage_right h2 span").not(".remark").text(song.name);
		if (song.remark) {
			$songEle.find(".entityMessage_right h2 .remark").text(song.remark);
		}
		$songEle.find(".creator_time_company .creator .creatorName").attr({
			"title" : song.singerName,
			"data-href" : "singer/{" + song.singerId + "}"
		}).text(song.singerName);
		$songEle.find(".creator_time_company .album .creatorName").attr({
			"title" : song.albumName,
			"data-href" : "album/{" + song.albumId + "}"
		}).text(song.albumName);
		$songEle.find(".buttons .comment").html("<i class='icomoon'></i>(" + song.commentNum + ")");

		// 获取歌词
		ajaxConfigs.setAjaxConfig({
			url : "lrc/" + pageScope.getAttribute("id"),
			success : function(data) {
				if (data.code == 200) {
					var $lrcEle = $songEle.find(".details");
					$lrcEle.children("p").remove();
					$lrcEle.children(".show_hidden").removeAttr("style").click(function() {
						if ($(this).attr("data-flag") === "close") {
							$(this).html("收起<i style='transform: translate(-125%, -25%) rotate(225deg);'></i>").attr("data-flag","open");
							$(this).parent().css({
								"height" : "inherit"
							})
						} else {
							$(this).html("展开<i></i>").attr("data-flag","close");
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
		ajaxConfigs.startAjax("lrc/" + pageScope.getAttribute("id"));

		$(".commentList .commentList_detail").text("共" + song.commentNum + "条评论");
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
