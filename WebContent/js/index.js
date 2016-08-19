(function() {
	var realUrl = document.getElementById("realUrl").innerHTML;

	var ajaxConfigs = new router.AjaxConfigs(realUrl);
	ajaxConfigs.setAjaxConfigs({
		url : realUrl + "/songMenu",
		success : loadSongMenus
	}, {
		url : realUrl + "/songMenuSecondTag",
		success : loadSongMenuSecondTags
	}, {
		url : realUrl + "/ablum",
		success : loadAlbums
	}, {
		url : realUrl + "/user",
		success : loadUsers
	}, {
		url : realUrl + "/songList",
		success : loadSongLists
	});

	ajaxConfigs.startAjaxs();

	function loadSongMenus(data) {
		var $songMenuListEle = $("#songMenuList");
		var $prototype = $songMenuListEle.find(".prototype").clone().removeClass("prototype");
		var $songMenuEle, songMenu;

		for (var i = 0; i < data.length; i++) {
			$songMenuEle = $prototype.clone();
			songMenu = data[i];
			$songMenuEle.find("img").attr({
				"alt" : songMenu.name,
				"src" : songMenu.icon,
				"title" : songMenu.name
			});
			$songMenuEle.find("img").parent().attr({
				"data-href" : "songMenu/{" + songMenu.id + "}"
			});
			$songMenuEle.find(".num").text(songMenu.playNum);
			$songMenuEle.find(".playthis").attr({
				"onclick" : "hiddenDiv.playFromSongMenu('" + songMenu.id + "')"
			});
			$songMenuEle.find(".name").attr({
				"title" : songMenu.name,
				"data-href" : "songMenu/{" + songMenu.id + "}"
			}).text(limitStringLength(songMenu.name, 20));

			$songMenuEle.attr("id", songMenu.id);

			$songMenuEle.appendTo($songMenuListEle);
		}
	}

	function loadSongMenuSecondTags(data) {
		var $songMenuSecondTagListEle = $("#songMenuSecondTagList");
		var $prototype = $songMenuSecondTagListEle.find(".prototype").clone().removeClass("prototype");
		var songMenuSecondTag, $songMenuSecondTagEle;
		for (var i = 0; i < data.length; i++) {
			songMenuSecondTag = data[i];
			$songMenuSecondTagEle = $prototype.clone().attr({
				"id" : songMenuSecondTag.id,
				"data-href" : "songMenus/{" + songMenuSecondTag.id + "}?" + songMenuSecondTag.name,
				"data-url" : "songMenus",
			}).text(songMenuSecondTag.name);

			$songMenuSecondTagListEle.append($songMenuSecondTagEle);
		}
	}

	function loadAlbums(data) {
		var $albumListEle = $("#albumList1");
		var $prototype = $albumListEle.find(".prototype").clone().removeClass("prototype");
		var album, $albumEle;

		for (var i = 0; i < data.length; i++) {
			album = data[i];
			$albumEle = $prototype.clone();
			$albumEle.find("img").attr({
				"src" : album.icon,
				"title" : album.name,
				"alt" : album.name
			});
			$albumEle.find("img").parent().attr({
				"data-href" : "album/{" + album.id + "}"
			});
			$albumEle.find(".play").attr({
				"onclick" : "hiddenDiv.playFromAlbum('" + album.id + "')"
			});

			$albumEle.find(".name").attr({
				"title" : album.name,
				"data-href" : "album/{" + album.id + "}"
			}).text(limitStringLength(album.name, 6));
			$albumEle.find(".songer").attr({
				"title" : album.singerName,
				"data-href" : "singer/{" + album.singerId + "}"
			}).text(limitStringLength(album.singerName, 6));

			if (i > 4) {
				$albumListEle = $("#albumList2");
			}
			$albumListEle.append($albumEle);
		}
	}

	function loadUsers(data) {
		var $userListEle = $("#userList");
		var $prototype = $userListEle.find(".prototype").clone().removeClass("prototype");
		var user, $userEle;

		for (var i = 0; i < data.length; i++) {
			user = data[i];
			$userEle = $prototype.clone();
			$userEle.find("img").attr({
				"src" : user.icon,
				"alt" : user.name
			});

			$userEle.find(".name").attr({
				"title" : user.name
			}).text(limitStringLength(user.name, 9));
			$userEle.find(".desc").attr({
				"title" : user.singerName
			}).text(limitStringLength(user.introduction, 9));

			$userEle.attr({
				"data-href" : "home/{" + user.id + "}"
			})

			$userListEle.append($userEle);
		}
	}

	function loadSongLists(data) {
		var $songListsEle = $("#songLists");
		var $prototype = $songListsEle.children(".prototype").clone().removeClass("prototype");
		var songList, $songListEle, $head, $_prototype, songs, $songEle, song, flag;

		$songListsEle.parents(".lists").find(".more").attr({
			"data-href" : "songList/{" + data[0].id + "}"
		})

		for (var i = 0; i < data.length; i++) {
			songList = data[i];
			$songListEle = $prototype.clone();
			$head = $songListEle.find("li").eq(0);
			$head.find("img").attr({
				"src" : songList.icon,
				"alt" : songList.name,
				"title" : songList.name,
				"data-href" : "songList/{" + songList.id + "}"
			});
			$head.find("h3").attr({
				"title" : songList.name,
				"data-href" : "songList/{" + songList.id + "}"
			}).text(songList.name);
			$head.find(".play").attr({
				"onclick" : "hiddenDiv.playFromSongList('" + songList.id + "')"
			});
			$head.find(".collection").attr({
			// "onclick" : "hiddenDiv.playFromSongList('" + songList.id + "')"
			});

			$_prototype = $prototype.find(".prototype").clone().removeClass("prototype");
			songs = songList.songs;
			flag = false;

			for (var j = 0; j < songs.length; j++) {
				song = songs[j];
				$songEle = $_prototype.clone();
				$songEle.find(".num").text(song.rank);
				$songEle.find(".name").attr({
					"title" : song.name,
					"data-name" : limitStringLength(song.name, 13),
					"data-href" : "song/{" + song.id + "}"
				}).text(limitStringLength(song.name, 13));

				if (flag) {
					$songEle.addClass("even");
					flag = false;
				} else {
					$songEle.addClass("odd");
					flag = true;
				}
				hiddenDiv.setSong(song.id, song);
				$songEle.find(".hidden .add").attr({
					"onclick" : 'hiddenDiv.addToPlayList("' + song.id + '");'
				})
				$songEle.find(".hidden .play").attr({
					"onclick" : 'hiddenDiv.addToPlayListThenPlay("' + song.id + '");'
				})
				$songEle.bind("mouseover", function() {
					var name = $(this).find(".name").text();
					$(this).find(".hidden").css("display", "block");
					$(this).find(".name").text(limitStringLength(name, 7));
				})
				$songEle.bind("mouseout", function() {
					var name = $(this).find(".name").attr("data-name");
					$(this).find(".hidden").css("display", "none");
					$(this).find(".name").text(name);
				})
				$songEle.attr({
					"id" : song.id
				})
				$songListEle.append($songEle);
			}

			$songListEle.append("<li><span class='all' data-url='songList' data-href='songList/{" + songList.id
					+ "}' onclick='jump(this);'>查看全部></span></li>");

			$songListEle.attr("id", songList.id);
			$songListsEle.append($songListEle);
		}
	}

	var albumDiv = new function() {
		this.init = function() {
			$("#albumDiv .previous").click(function(event) {
				changeAlbum(true);
			});
			$("#albumDiv .next").click(function(event) {
				changeAlbum(false);
			});
		}
		this.unbindEvent = function() {
			$("#albumDiv .previous").unbind('click');
			$("#albumDiv .next").unbind('click');
		}

		function changeAlbum(flag) {
			albumDiv.unbindEvent();
			var $hidden = $("#albumDiv ul[style]");
			var $visible = $("#albumDiv ul").not($hidden[0]);
			if (flag) {
				$hidden.css('left', '-100%').animate({
					'left' : '0%'
				}, 1000);
				$visible.animate({
					'left' : '100%'
				}, 1000, function() {
					$hidden.removeAttr('style');
					$visible.css('left', '100%');
					albumDiv.init();
				});
			} else {
				$hidden.animate({
					'left' : '0%'
				}, 1000);
				$visible.animate({
					'left' : '-100%'
				}, 1000, function() {
					$hidden.removeAttr('style');
					$visible.css('left', '100%');
					albumDiv.init();
				});
			}
		}
	}

	var imageDiv = new function() {
		this.unbind = function() {
			$("#image_before").unbind();
			$("#image_after").unbind();
		}

		function autoChange() {
			// console.log(new Date())
			imageDiv.calculateImage($('#image_after').siblings('ul')[0], 'next');
		}
		this.init = function() {
			$("#image_before").click(function(event) {
				imageDiv.calculateImage($(this).siblings('ul')[0], 'previous');
			});
			$("#image_after").click(function(event) {
				imageDiv.calculateImage($(this).siblings('ul')[0], 'next');
			});
			clearInterval(sessionStorage.getItem("image_interval_id"));
			sessionStorage.setItem("image_interval_id", setInterval(autoChange, 3000));
		}
		this.calculateImage = function(imageEle, direction) {
			var imgs = $(imageEle)[0];
			var now = $(imageEle).children('.now').index();
			if (direction == 'previous') {
				now--;
			} else {
				now++;
			}
			var next = now + 1;
			var previous = now - 1;
			if (direction == 'previous') {
				if (now < 0) {
					now = 4;
					next = 0;
					previous = now - 1;
				} else if (now == 0) {
					previous = 4;
				}
				previousImage(imgs, previous, now, next);
			} else {
				if (now > 4) {
					now = 0;
					next = now + 1;
					previous = 4;
				} else if (now == 4) {
					next = 0;
				}
				nextImage(imgs, previous, now, next);
			}
		}

		function nextImage(imgs, previous, now, next) {
			changePoint($(imgs).siblings('ul[class=points]')[0], now);
			changeImage(imgs, {
				'expr' : 'next',
				'index' : now
			}, {
				'expr' : 'now',
				'left' : '20%',
				'index' : previous,
				'expr_after' : 'previous'
			}, {
				'expr' : 'previous'
			}, {
				'index' : next,
				'left_before' : '85%',
				'left_after' : '80%',
				'expr_after' : 'next'
			});
		}
		function previousImage(imgs, previous, now, next) {
			changePoint($(imgs).siblings('ul[class=points]')[0], now);
			changeImage(imgs, {
				'expr' : 'previous',
				'index' : now
			}, {
				'expr' : 'now',
				'left' : '80%',
				'index' : next,
				'expr_after' : 'next'
			}, {
				'expr' : 'next'
			}, {
				'index' : previous,
				'left_before' : '15%',
				'left_after' : '20%',
				'expr_after' : 'previous'
			});
		}
		function changePoint(points, now) {
			$(points).children().removeAttr('class').eq(now).addClass('point_now');
		}
		function changeImage(imgs, big, small, hidden, visible) {
			imageDiv.unbind();
			var $imgs = $(imgs);
			$imgs.children('.' + hidden.expr).children('img').css('z-index', '1').stop(true).animate({
				'left' : '50%',
				'width' : '20%'
			}, 700);
			$imgs.children('.' + small.expr).children('img').css('z-index', '2').stop(true).animate({
				'width' : '31%',
				'left' : small.left
			}, 700);
			$imgs.children('.' + big.expr).children('img').css({
				'z-index' : '3',
				'opacity' : '1'
			}).stop(true).animate({
				'width' : '41%',
				'left' : '50%'
			}, 700);
			$imgs.children().eq(visible.index).children('img').css({
				'z-index' : '1',
				'left' : visible.left_before,
				'visibility' : 'visible',
				'width' : '21%'
			}).stop(true).animate({
				'width' : '31%',
				'left' : visible.left_after,
				'opacity' : '0.5'
			}, 700, function() {
				$imgs.children().removeAttr('class').children('img').removeAttr('style');
				$imgs.children().eq(big.index).attr('class', 'now');
				$imgs.children().eq(small.index).attr('class', small.expr_after);
				$imgs.children().eq(visible.index).attr('class', visible.expr_after);
				imageDiv.init();
			});
		}
	}
	function userDiv() {
		var $userDiv = $(".material_right .userDiv");
		var user = JSON.parse(sessionStorage.getItem("user"));

		if (user && user.name) {
			$userDiv.find(".logout").css("display", "none");
			var $login = $userDiv.find(".login").removeAttr("style");
			$login.find("img").attr({
				"src" : user.icon
			}) //
			.parent().attr({
				"data-href" : "home/{" + user.id + "}"
			});
			$login.find(".name").attr({
				"data-href" : "home/{" + user.id + "}"
			}).text(user.name);
			// $login.find(".level").text(user.level);
			// var $nums = $login.find(".userDiv_bottom .num")
			// $nums.eq(0).text(user.dynamicNum);
			// $nums.eq(1).text(user.attentionNum);
			// $nums.eq(2).text(user.fansNum);
		}
	}
	imageDiv.init();
	albumDiv.init();
	userDiv();
})()