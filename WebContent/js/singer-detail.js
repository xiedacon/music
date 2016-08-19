(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);

	pageScope.setAttribute("singerId", realUrl.split("/")[1]);

	ajaxConfigs.setAjaxConfigs({
		url : realUrl,
		success : loadSinger
	}, {
		url : realUrl + "/songs",
		success : loadSongs
	});

	ajaxConfigs.startAjaxs();

	function loadSinger(singer) {
		var $singerEle = $("#singer");

		$singerEle.find("img").attr({
			"src" : singer.icon
		});
		$singerEle.find("h2 span").not(".remark").text(singer.name);
		if (singer.remark) {
			$singerEle.find("h2 .remark").text(singer.remark);
		}
		if (singer.userId) {
			$singerEle.find(".homePage").attr({
				"title" : singer.name + "的个人主页",
				"data-href" : "home/{" + singer.userId + "}"
			}).removeAttr("style");
		}
		var $songList = $(".songList .play_addToPlayList");
		$songList.find(".play").attr({
			"onclick" : "hiddenDiv.playFromSinger('" + singer.id + "')"
		});
		$songList.find(".addToPlaylist").attr({
			"onclick" : "hiddenDiv.addFromSinger('" + singer.id + "')"
		});
	}

	function loadSongs(songs) {
		$("#songListHead").addClass("now") //
		.siblings().removeClass("now");

		var $songListEle = $("#songList");
		$songListEle.parents(".songList").removeAttr("style") //
		.siblings().css("display", "none");
		$songListEle.children().not(".prototype").remove();

		var $prototype = $songListEle.find(".prototype").clone().removeClass("prototype");
		var $songEle, song;

		for (var i = 0; i < songs.length; i++) {
			$songEle = $prototype.clone();
			song = songs[i];

			if (i % 2 != 0) {
				$songEle.addClass("singular");
			}

			$songEle.find(".num").text(i + 1);
			$songEle.find(".play").attr({
				"onclick" : "hiddenDiv.addToPlayListThenPlay('" + song.id + "')"
			});
			$songEle.find(".songName").attr({
				"title" : song.name,
				"data-href" : "song/{" + song.id + "}"
			}).text(song.name);
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "hiddenDiv.addToPlayList('" + song.id + "')"
			});
			$songEle.find(".time").text(song.time);
			if (song.albumName) {
				$songEle.find(".special").attr({
					"title" : song.albumName,
					"data-href" : song.albumId
				}).text(song.albumName);
			}
			var ratio = parseFloat(song.collectionNum) / 1000;
			if (ratio > 0 && ratio < 0.075) {
				ratio = 0.075;
			} else if (ratio > 1) {
				ratio = 1;
			}
			$songEle.find(".hot").css("width", ratio * 100 + "%");

			$songListEle.append($songEle);
		}
	}

	function loadAlbums(pageBean) {
		$("#albumListHead").addClass("now") //
		.siblings().removeClass("now");

		var $albumListEle = $("#albumList");
		$albumListEle.parent().removeAttr("style") //
		.siblings().css("display", "none");
		$albumListEle.children().not(".prototype").remove();

		var $prototype = $albumListEle.find(".prototype").clone().removeClass("prototype");
		var albumList = pageBean.beans;
		var album, $albumEle;

		for (var i = 0; i < albumList.length; i++) {
			album = albumList[i];
			$albumEle = $prototype.clone();

			$albumEle.find("img").attr({
				"src" : album.icon,
				"title" : album.name,
				"data-href" : "album/{" + album.id + "}"
			});
			$albumEle.find(".name").attr({
				"title" : album.name,
				"data-href" : "album/{" + album.id + "}"
			}).text(limitStringLength(album.name, 9));
			$albumEle.find(".time").text(new DateFormatter("yyyy.MM.dd").format(album.createTime));

			$albumListEle.append($albumEle);
		}

		loadPage($albumListEle.siblings("#pages"), pageBean);
	}

	function loadPage($pagesEle, pageBean) {
		$pagesEle.empty();

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

				if (!ajaxConfigs.hasAjaxConfig(realUrl + "/albums/" + page)) {
					ajaxConfigs.setAjaxConfig({
						url : realUrl + "/albums/" + page,
						success : function(pageBean) {
							loadAlbumList(pageBean);
						}
					});
				}

				ajaxConfigs.startAjax(realUrl + "/albums/" + page);
			})
		}
	}

	function loadIntroduction(data) {
		$("#introductionHead").addClass("now") //
		.siblings().removeClass("now");

		var $introduction = $("#introduction");
		$introduction.removeAttr("style") //
		.siblings().css("display", "none");

		if (data.code == 200) {
			$introduction.find("p").text(data.introduction) //
			.parent().removeAttr("style") //
			.siblings().css("display", "none");
		}
	}

	$("#songListHead").click(function() {
		ajaxConfigs.setAjaxConfig({
			url : realUrl + "/songs",
			success : loadSongs
		});
		ajaxConfigs.startAjax(realUrl + "/songs");
	});
	$("#albumListHead").click(function() {
		ajaxConfigs.setAjaxConfig({
			url : realUrl + "/albums/1",
			success : loadAlbums
		});
		ajaxConfigs.startAjax(realUrl + "/albums/1");
	});
	$("#introductionHead").click(function() {
		ajaxConfigs.setAjaxConfig({
			url : realUrl + "/introduction",
			success : loadIntroduction
		});
		ajaxConfigs.startAjax(realUrl + "/introduction");
	});
}())
