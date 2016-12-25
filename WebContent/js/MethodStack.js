(function() {
	function loadComment(data) {
		var comment = data.comment;
		var $commentListEle = data.$commentListEle;
		var $prototype = $commentListEle.find(".prototype").clone().removeClass("prototype");
		var $commentEle = $prototype.clone();

		$commentEle.find("img").attr({
			"src" : comment.creatorIcon,
			"data-href" : "home?userId=" + comment.creatorId
		});
		$commentEle.find(".comment_material .name").attr({
			"data-href" : "home?userId=" + comment.creatorId,
			"title" : comment.creatorName
		}).text(comment.creatorName);
		$commentEle.find(".comment_material .content").html(":" + comment.content);
		$commentEle.find(".comment_bottom .createTime").text(new DateFormatter("MM月dd号 HH:mm").format(comment.createTime));
		$commentEle.find(".comment_bottom .right .num").text(comment.agreeNum).attr("onclick", "MMR.agreeComment(this,'" + comment.id + "');");
		$commentEle.find(".comment_bottom .right .num").siblings().on("click", {
			id : comment.id
		}, function(e) {
			MMR.agreeComment($(this).siblings().eq(0)[0], e.data.id);
		})
		if (sessionStorage["flag_agreed_" + comment.id]) {
			$commentEle.find(".comment_bottom .right .num").siblings().css("color", "#ff0000");
		} else {
			$commentEle.find(".comment_bottom .right .num").siblings().removeAttr("style");
		}

		$commentListEle.append($commentEle);
	}
	FUNCTION.loadPageBean = function _loadPageBean(data) {
		var pageBean = data.pageBean;
		var $pagesEle = data.$pagesEle;
		$pagesEle.empty();

		data.loadBeans();

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
				data.click($(this).attr("data-page"));
			})
		}

		FUNCTION.align($(".material_right"), $(".material_left"));
	}
	function loadPageBean_comment(pageBean, flag) {
		var $commentListEle = $("#commentList");

		if (!flag) {
			$commentListEle.children().not(".prototype").remove();
		}

		FUNCTION.loadPageBean({
			pageBean : pageBean,
			$pagesEle : $("#pages"),
			loadBeans : function() {
				var newList = pageBean.beans;

				if (newList.length > 0) {
					if (pageBean.page === 1) {
						$commentListEle.append("<h3>最新评论<span>(" + pageBean.count + ")</span></h3>");
					}
					for (var i = 0; i < newList.length; i++) {
						loadComment({
							comment : newList[i],
							$commentListEle : $commentListEle
						})
					}
				}
			},
			click : function(page) {
				var url = PageScope.loadPageBean + page;
				var success = function(data){
					if(data.code != 200){
						MMR.get("simpleMsg").showError(data.error.value);
						return;
					}

					loadPageBean_comment(data.data);
				}
				if (page == 1) {
					url = PageScope.loadForFirst;
					success = FUNCTION.loadForFirst;
				}
				AJAX({
					url : url,
					success : success
				})
			}
		});
	}
	FUNCTION.loadForFirst = function loadForFirst(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}

		var comments = data.data //
		, hotList = comments.hotList;
		var $commentListEle = $("#commentList");
		$commentListEle.children().not(".prototype").remove();

		var $prototype = $commentListEle.find(".prototype").clone().removeClass("prototype");
		var $commentEle, comment;
		if (hotList.length > 0) {
			$commentListEle.append("<h3>最热评论<span>(" + hotList.length + ")</span></h3>")
			for (var i = 0; i < hotList.length; i++) {
				loadComment({
					comment : hotList[i],
					$commentListEle : $commentListEle
				});
			}
		}

		FUNCTION.loadEmojis();
		loadPageBean_comment(comments.pageBean, true);
	}
	FUNCTION.loadSongs = function loadSongList(data, special) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}

		var songList = data.data //
		, $songListEle = $("#songList");
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

			if (special) {
				special($songEle, song, i);
			}

			$songEle.find(".num").text(i + 1);
			var songSerialized = MMR.get('music').serialize(song);
			$songEle.find(".play").attr({
				"onclick" : "MMR.get('music').addThenPlay('" + songSerialized + "');"
			});
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "MMR.get('music').add('" + song.id + "');"
			});
			$songEle.find(".hidden .collection").attr({
				"onclick" : "MMR.get('collection').collect('" + song.id + "');"
			})
			$songEle.find(".songName").attr({
				"data-href" : "song?songId=" + song.id,
				"title" : song.name
			}).text(limitStringLength(song.name, 20));
			$songEle.find(".time").text(song.time);
			$songEle.find(".songer").attr({
				"data-href" : "singer?singerId=" + song.singerId,
				"title" : song.singerName
			}).text(limitStringLength(song.singerName, 5));
			$songEle.find(".special").attr({
				"data-href" : "album?albumId=" + song.albumId,
				"title" : song.albumName
			}).text(limitStringLength(song.albumName, 7));

			$songListEle.append($songEle);
		}
	}
	FUNCTION.loadSongMenu = function loadSongMenu(songMenu, $songMenuEle, i) {
		$songMenuEle.find("img").attr({
			"src" : songMenu.icon,
			"alt" : songMenu.name,
			"title" : songMenu.name,
			"data-href" : "songMenu?songMenuId=" + songMenu.id
		});
		$songMenuEle.find(".num").text(songMenu.playNum);
		$songMenuEle.find(".playthis").attr({
			"onclick" : "MMR.get('music').batchAddThenPlay('songMenuId','" + songMenu.id + "')"
		});
		$songMenuEle.find(".name").attr({
			"title" : songMenu.name,
			"data-href" : "songMenu?songMenuId=" + songMenu.id
		}).text(songMenu.name);
		$songMenuEle.find(".songer").attr({
			"title" : songMenu.creatorName,
			"data-href" : "home?userId=" + songMenu.creatorId
		}).text(songMenu.creatorName);
		if ((i + 1) % 5 === 0) {
			$songMenuEle.css("padding-right", 0);
		}
	}
	FUNCTION.loadAlbum = function loadAlbum(album, $albumEle) {
		$albumEle.find("img").attr({
			"src" : album.icon,
			"title" : album.name,
			"data-href" : "album?albumId=" + album.id
		});
		$albumEle.find(".play").attr({
			"onclick" : "MMR.get('music').batchAddThenPlay('albumId','" + album.id + "')"
		});
		$albumEle.find(".name").attr({
			"title" : album.name,
			"data-href" : "album?albumId=" + album.id
		}).text(album.name);
		$albumEle.find(".songer").attr({
			"title" : album.singerName,
			"data-href" : "singer?singerId=" + album.singerId
		}).text(album.singerName);
	}

	// 保持高度相等
	FUNCTION.align = function align($ele1, $ele2) {
		$ele1.css("min-height", "inherit");
		$ele2.css("min-height", "inherit");
		var height1 = $ele1.outerHeight();
		var height2 = $ele2.outerHeight();
		var max = height1 > height2 ? height1 : height2;
		$ele1.css("min-height", max);
		$ele2.css("min-height", max);
	}

	FUNCTION.loadEmojis = function() {
		var $emojis = $("#emojis");
		var $newComment = $("#newComment");
		var emojis_flag;
		FUNCTION.showEmojis = function() {
			if (emojis_flag) {
				$emojis.css("display", "none");
				emojis_flag = false;
			} else {
				$emojis.css("display", "block");
				emojis_flag = true;
			}
		}
		$newComment.on("focusin", function() {
			$newComment.on("keydown", function() {
				$(this).parent().find(".num").text(200 - $(this).val().length);
			});
		});
		$newComment.on("focusout", function() {
			$newComment.off("keydown");
		});

		AJAX({
			url : "json/emoji",
			success : function(emojis) {
				PageScope.emojis = emojis;
				var shortnames = new Array();
				emojis.forEach(function(emoji) {
					$emojis.append('<img class="emoji" data-name="' + emoji.shortname + '" alt="" src="svg/' + emoji.unicode + '.svg">');
					shortnames.push(emoji.shortname);
				});
				var regShortNames = new RegExp(shortnames.join('|'), "gi");
				FUNCTION.shortnameToImage = function(str) {
					var emoji;
					str = str.replace(regShortNames, function(shortname) {
						if ((typeof shortname === 'undefined') || (shortname === '')) {
							return shortname;
						}
						emoji = emojis.find(function(emoji) {
							if (emoji.shortname === shortname) {
								return true;
							} else {
								return false;
							}
						})
						return '<img class="emoji" data-name="' + emoji.shortname + '" alt="" src="svg/' + emoji.unicode + '.svg">';
					});
					return str;
				};
				$emojis.find("img").on("click", function() {
					var name = $(this).attr("data-name");
					var comment = $("#newComment").val();
					$newComment.val(comment + name);
					FUNCTION.showEmojis();
				})
			}
		})
	}
}())
