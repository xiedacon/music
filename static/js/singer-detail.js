(function() {
	AJAX({
		url : "singer/" + PageScope.params.singerId,
		success : loadSinger
	});
	AJAX({
		url : "song/singerId_" + PageScope.params.singerId,
		success : loadSongs
	});

	function loadSinger(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var singer = data.data //
		, $singerEle = $("#singer");

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
				"data-href" : "home?userId=" + singer.userId
			}).removeAttr("style");
		}
		var $songList = $(".songList .play_addToPlayList");
		$songList.find(".play").attr({
			"onclick" : "MMR.get('music').batchAddThenPlay('singerId','" + singer.id + "')"
		});
		$songList.find(".addToPlaylist").attr({
			"onclick" : "MMR.get('music').batchAdd('singerId','" + singer.id + "')"
		});
	}

	function loadSongs(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		$("#songListHead").addClass("now") //
		.siblings().removeClass("now");

		var songs = data.data //
		, $songListEle = $("#songList");
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
			var songSerialized = MMR.get('music').serialize(song);
			$songEle.find(".play").attr({
				"onclick" : "MMR.get('music').addThenPlay('" + songSerialized + "')"
			});
			$songEle.find(".songName").attr({
				"title" : song.name,
				"data-href" : "song?songId=" + song.id
			}).text(song.name);
			$songEle.find(".addToPlaylist").attr({
				"onclick" : "MMR.get('music').add('','" + songSerialized + "')"
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

	function loadAlbums(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		$("#albumListHead").addClass("now") //
		.siblings().removeClass("now");

		var pageBean = data.data // 
		, $albumListEle = $("#albumList");
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
				"data-href" : "album?albumId=" + album.id
			});
			$albumEle.find(".name").attr({
				"title" : album.name,
				"data-href" : "album?albumId=" + album.id
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

				AJAX({
					url : "album/singerId_" + PageScope.params.singerId + "/" + page,
					success : function(pageBean) {
						loadAlbumList(pageBean);
					}
				});
			})
		}
	}

	function loadIntroduction(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		introduction = data.data;
		$("#introductionHead").addClass("now") //
		.siblings().removeClass("now");

		var $introduction = $("#introduction");
		$introduction.removeAttr("style") //
		.siblings().css("display", "none");

		if (data.code == 200) {
			$introduction.find("p").text(introduction) //
			.parent().removeAttr("style") //
			.siblings().css("display", "none");
		}
	}

	$("#songListHead").click(function() {
		AJAX({
			url : "song/singerId_" + PageScope.params.singerId,
			success : loadSongs
		});
	});
	$("#albumListHead").click(function() {
		AJAX({
			url : "album/singerId_" + PageScope.params.singerId + "/1",
			success : loadAlbums
		});
	});
	$("#introductionHead").click(function() {
		AJAX({
			url : "singer/" + PageScope.params.singerId + "/introduction",
			success : loadIntroduction
		});
	});
}())
