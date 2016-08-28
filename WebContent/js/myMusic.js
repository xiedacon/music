(function() {
	AJAX({
		url : "songMenu/s/creatorId_" + PageScope.params.userId,
		success : loadCreatedSongMenus
	});
	AJAX({
		url : "songMenu/s/collectorId_" + PageScope.params.userId,
		success : loadCollectedSongMenus
	});

	function loadSongMenu(songMenuId) {
		var $songMenus = $(".material_right li").not(".prototype");
		$songMenus.filter("li[data-id='" + songMenuId + "']").addClass("now") //
		$songMenus.filter("li[data-id!='" + songMenuId + "']").removeClass("now");

		PageScope.loadForFirst = "comment/s/songMenuId_" + songMenuId;
		PageScope.loadPageBean = "comment/s/songMenuId_" + songMenuId + "/";

		AJAX({
			url : "songMenu/" + songMenuId,
			success : _loadSongMenu
		});
		AJAX({
			url : "song/s/songMenuId_" + songMenuId,
			success : FUNCTION.loadSongs
		});
		AJAX({
			url : PageScope.loadForFirst,
			success : FUNCTION.loadForFirst
		});
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

			var userId = UserManager.getUserId();

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
				loadSongMenu(songMenu.id);
				continue;
			}

			$songMenuEle.find(".option_rightF").removeAttr("style");

			if (songMenu.creatorId === userId) {
				$songMenuEle.find(".edit").removeAttr("style").on("click", function() {
					var songMenuId = $(this).parents(".option").attr("data-id");
					showEditPage(songMenuId);
					return false;
				});
			}

			$songMenuEle.find(".delete").on("click", function() {
				MMR.deleteSongMenu(this);
				return false;
			});

			$songMenusEle.append($songMenuEle);
		}
	}

	function showEditPage(songMenuId) {
		$("#createdSongMenus .option,#collectedSongMenus .option").removeClass("now");
		var $editPage = $("#editPage");
		$.ajax({
			url : "songMenu/" + songMenuId,
			dataType : "json",
			type : "GET",
			success : function(songMenu) {
				$editPage.attr("data-id", songMenu.id);
				$editPage.find(".name").attr({
					"data-href" : "songMenu?songMenuId=" + songMenu.id
				}).text(songMenu.name);
				$editPage.find("input").val(songMenu.name);
				if (songMenu.introduction) {
					$editPage.find(".introduction").text(songMenu.introduction);
				}
				if (songMenu.songMenuSecondTagList) {
					var $tags = $editPage.find(".tags");
					var tagList = songMenu.songMenuSecondTagList;
					var tag;
					for (var i = 0; i < tagList.length; i++) {
						tag = tagList[i];
						$tags.prepend("<span data-id='" + tag.id + "' class='tag'>" + tag.name + "<i class='icomoon'></i></span>");
					}
					$tags.find("i").on("onclick", function() {
						$(this).parent().remove();
					})
				}
				$editPage.find("img").attr("src", songMenu.icon);
				$editPage.css("display", "block");
				$("#showPage").css("display", "none");
			}
		})
	}

	function _loadSongMenu(songMenu) {
		var $songMenuEle = $("#songMenu");
		$songMenuEle.find(".songMenuMessage_left img").attr({
			"src" : songMenu.icon
		});

		var userId = UserManager.getUserId();

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

		var user = UserManager.getUser();

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
}())
