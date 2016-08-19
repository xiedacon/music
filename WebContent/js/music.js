function musicDiv() {
	var $musicDiv = $("#musicDiv");

	var progressModule = new progress(this);
	var volumeDivModule = new volumeDiv(this);

	var playListDivModule = new playListDiv(this);

	this.playListDivModule = playListDivModule;
	this.play = playListDivModule.play;
	this.setSong = playListDivModule.setSong;
	this.init = function() {
		playListDivModule.init();
		progressModule.init();
		volumeDivModule.init();

		var $i = $musicDiv.find(".show i");
		$i.click(function() {
			var id;
			var type = $(this).text();
			if (type == "") {
				$(this).text("");
				$i.unbind("hover");
				$musicDiv.unbind();
			} else {
				$(this).text("");
				$i.hover(function() {
					hiddenDiv.showDiv("musicDiv");
					clearTimeout(id);
				}, function() {
				});
				$musicDiv.hover(function() {
					clearTimeout(id);
				}, function() {
					id = setTimeout(hiddenMusicDiv, 1000);
				})
			}
		});

		var $buttons = $musicDiv.find(".music_left");
		$buttons.find(".previous").click(function() {
			playListDivModule.previous();
		});
		$buttons.find(".middle").click(function() {
			var text = $(this).text();
			if (text === "") {
				playListDivModule.play();
			} else {
				playListDivModule.pause();
			}
		});
		$buttons.find(".next").click(function() {
			playListDivModule.next();
		});

		var $playModeDiv = $musicDiv.find(".playModeDiv");
		var id;
		$playModeDiv.find(".playMode").click(function() {
			if ($playModeDiv.find("i").css('display') != "none") {
				clearTimeout(id);
			}
			$playModeDiv.find("i").removeAttr('style');
			id = setTimeout(hiddenPlayMode, 1000);
			var playMode = $playModeDiv.find("i").text();
			switch (playMode) {
				case '循环' :
					$playModeDiv.find(".playMode").text("").attr('title', '单曲循环');
					$playModeDiv.find("i").text("单曲循环");
					playListDivModule.setPlayMode("单曲循环");
					break;
				case '单曲循环' :
					$playModeDiv.find(".playMode").text("").attr('title', '随机');
					$playModeDiv.find("i").text("随机");
					playListDivModule.setPlayMode("随机");
					break;
				case '随机' :
					$playModeDiv.find(".playMode").text("").attr('title', '循环');
					$playModeDiv.find("i").text("循环");
					playListDivModule.setPlayMode("循环");
					break;
			}
		})
	}
	this.show = function() {
		$musicDiv.stop(true).animate({
			'top' : '100%'
		}, 500);
	}
	this.hidden = hiddenMusicDiv;
	this.changePlayTime = playListDivModule.changeTime;
	this.changeVolume = playListDivModule.changeVolume;
	this.changePlayProgress = progressModule.changePlayProgress;
	this.changeCacheProgress = progressModule.changeCacheProgress;
	this.changeVolumeHeight = volumeDivModule.changeVolumeHeight;

	function hiddenMusicDiv() {
		$musicDiv.stop(true).animate({
			'top' : '107%'
		}, 500);
	}

	function hiddenPlayMode() {
		$musicDiv.find(".playModeDiv i").css('display', 'none');
	}
}

function playListDiv(musicDiv) {
	var $songNum = $("#musicDiv .num");
	var $playListDiv = $("#playListDiv");
	var $songList = $playListDiv.find(".list_body");
	var $prototype = $playListDiv.find(".prototype").clone().removeClass("prototype");
	var height = parseFloat($playListDiv.find(".playList_hidden_bottom").height());
	var songList = new Array();

	var $pointer;

	var listScrollDivModule = new listScrollDiv();
	var songScrollDivModule = new songScrollDiv();

	this.setSong = function(songId, song) {
		songList[songId] = song;
	}
	this.init = function() {
		listScrollDivModule.init();
		songScrollDivModule.init();

		$("#musicDiv .playList").click(function() {
			showPlayListDiv();
		})
		$playListDiv.find(".song_head i").click(function() {
			hiddenPlayListDiv();
		})
		$playListDiv.find(".list_head .collectionAllSpan").click(function() {
			hiddenDiv.showDiv("collectionDiv");
		})
		$playListDiv.find(".list_head .clearSpan").click(function() {
			$playListDiv.find(".list_body .song").not(".prototype").remove();
			$playListDiv.find(".list_body .none").css('display', 'block');
			$songNum.text(0);
			changeHeight();
		})

		$playListDiv.find(".song_body .fullScreen").click(function() {
			$playListDiv.find(".song_body .lrc").css('width', '100%')[0].webkitRequestFullScreen();
		})
		$playListDiv.find(".song_body .exitFullScreen").click(function() {
			$playListDiv.find(".song_body .lrc").css('width', '95%');
			document.webkitCancelFullScreen();
		})

		var volume_default = 0.5;
		audio.volume = volume_default;
		_musicDiv.changeVolumeHeight(volume_default);
	}
	this.show = showPlayListDiv;
	this.hidden = hiddenPlayListDiv;
	this.add = add;
	this.play = play;
	this.pause = pause;
	this.previous = previous;
	this.next = next;
	this.changeTime = changeTime;
	this.changeVolume = changeVolume;

	function previous() {
		var $songs = $songList.children().not(".prototype, .none");
		$pointer = $songs.eq($songs.index($pointer) - 1);
		stop();
		play();
	}
	function next() {
		var $songs = $songList.children().not(".prototype, .none");
		$pointer = $songs.eq($songs.index($pointer) + 1);
		stop();
		play();
	}
	var timeOutId;
	function add(songId) {
		if ($songList.children("li[data-songId='" + songId + "']").length > 0) {
			return;
		}

		clearTimeout(timeOutId);
		$(".playListDiv .message").removeAttr("style") //
		.find(".content").text("已添加到播放列表");
		timeOutId = setTimeout(function() {
			$(".playListDiv .message").fadeOut("fast");
		}, 1000);

		$playListDiv.find(".list_body .none").css('display', 'none');

		var song = songList[songId];
		var $songEle = $prototype.clone();

		$songEle.attr({
			"data-songId" : songId
		});

		$songEle.find(".songName").attr({
			"title" : song.name,
			"onclick" : 'hiddenDiv.play("' + songId + '");'
		}).text(limitStringLength(song.name, 15));
		$songEle.find(".collection");
		$songEle.find(".delete").click(function() {
			$(this).parents(".song").remove();
			$songNum.text(parseInt($songNum.text()) - 1);
			changeHeight();
		});
		$songEle.find(".songer").attr({
			"title" : song.singerName,
			"data-href" : "singer/{" + song.singerId + "}"
		}).text(limitStringLength(song.singerName, 3));
		$songEle.find(".time").text(song.time);
		$songEle.find(".from").attr({
		// "data-href" : "songList/{" + song.id + "}"
		});

		$songList.append($songEle);

		$songNum.text(parseInt($songNum.text()) + 1);
		$playListDiv.find(".head_left .num").text($songNum.text())

		changeHeight();
	}
	var $playButton = $("#musicDiv .music_left .middle");
	var $play = $("#musicDiv .music_middle");
	var audio = $("#music")[0];
	var flag = true;
	var playModeFunction = playMode_loop;
	this.setPlayMode = function(playMode) {
		switch (playMode) {
			case "循环" :
				playModeFunction = playMode_loop;
				break;
			case "随机" :
				playModeFunction = playMode_random;
				break;
			default : // 单曲循环
				playModeFunction = playMode_singleLoop;
				break;
		}
	}
	function playMode_random() {
		audio.ontimeupdate = function() {
			if (audio.currentTime >= audio.duration) {
				var $songs = $songList.children().not(".prototype, .none");
				$pointer = $songs.eq(parseInt(Math.random() * $songs.length));
				stop();
				play();
			}
		}
	}
	function playMode_singleLoop() {
		audio.ontimeupdate = function() {
			if (audio.currentTime >= audio.duration) {
				stop();
				play();
			}
		}
	}
	function playMode_loop() {
		audio.ontimeupdate = function() {
			if (audio.currentTime >= audio.duration) {
				var $songs = $songList.children().not(".prototype, .none");
				var num = $songs.index($pointer) + 1;
				if (num === $songs.length) {
					num = 0;
				}
				$pointer = $songs.eq(num);
				stop();
				play();
			}
		}
	}
	function stop() {
		pause();
		flag = true;
	}
	function pause() {
		$playButton.text("");
		clearInterval(intervalId);
		audio.pause();
	}
	function play(songId) {
		$playButton.text("");
		clearInterval(intervalId);

		if (songId) {
			flag = true;
			$pointer = $songList.find("li[data-songId='" + songId + "']");
		}

		if ($songList.children().size() <= 2) {
			return pause();
		} else if (!$pointer) {
			$pointer = $songList.children().not(".prototype, .none").eq(0);
		} else if ($songList.children().index($pointer) < 0) {
			$pointer = $songList.children().not(".prototype, .none").eq(0);
		}

		var songId = $pointer.attr("data-songId");
		var song = songList[songId];

		$pointer.prepend("<s class='icomoon'></s>").addClass("now") //
		.siblings().removeClass("now").children("s").remove();

		$play.find("img").attr({
			"src" : song.icon
		}).parent().attr({
			"data-href" : "song/{" + song.id + "}"
		});
		$play.find(".song_name").attr({
			"data-href" : "song/{" + song.id + "}",
			"title" : song.name
		}).text(song.name);
		$play.find(".songer").attr({
			"data-href" : "singer/{" + song.singerId + "}",
			"title" : song.singerName
		}).text(song.singerName);
		$time = $play.find(".time").removeAttr("style");
		$time.find(".total").text(song.time);
		$time.find(".now").text("00:00");
		time = new Date();

		if (flag) {
			audio.src = "file/" + song.id;
			flag = false;
		}
		playModeFunction();
		audio.play();

		intervalId = setInterval(change, 1000);
	}
	var intervalId;
	var $time;
	var _musicDiv = musicDiv;
	function change() {
		var now = audio.currentTime;
		var total = audio.duration;
		if (now >= total) {
			clearInterval(intervalId);
			return;
		}
		$time.find(".now").text(new DateFormatter("mm:ss").format(now * 1000));

		var playRatio = now / total;
		var cacheRatio = audio.buffered.end(0) / total;
		_musicDiv.changePlayProgress(playRatio);
		_musicDiv.changeCacheProgress(cacheRatio);
	}
	function changeTime(ratio) {
		var now = ratio * audio.duration;
		// 谷歌不兼容
		audio.currentTime = now;
	}
	function changeVolume(ratio) {
		audio.volume = ratio;
	}
	function changeHeight() {
		var ratio = 8.5 / parseFloat($songNum.text());
		if (ratio > 1) {
			ratio = 1;
		}
		var topRatio = parseFloat($playListDiv.find("#listScrollDiv .vernier").css("top")) / height;
		if ((ratio + topRatio) > 1) {
			topRatio = 1 - ratio;
			$playListDiv.find("#listScrollDiv .vernier").css("top", topRatio * height + "px");
		}
		$playListDiv.find("#listScrollDiv .vernier").css("height", ratio * 100 + "%");
	}
	function showPlayListDiv() {
		$playListDiv.css({
			'display' : 'block',
			'visibility' : 'visible'
		});
	}
	function hiddenPlayListDiv() {
		$playListDiv.css({
			'display' : 'none',
		});
	}
}

function listScrollDiv() {
	var $listScrollDiv = $("#listScrollDiv");
	var $vernier = $listScrollDiv.find(".vernier");
	var $playListDiv = $("#playListDiv");

	this.init = function() {
		$vernier.mouseover(function(event) {
			bindPointEvent();
		});
		$(document).mouseup(function(e) {
			unbindPointEvent();
		})

		function unbindPointEvent() {
			$("body").css('-webkit-user-select', 'inherit');
			$playListDiv.unbind();
			$vernier.unbind().mouseover(function(event) {
				bindPointEvent();
			});
		}
		function bindPointEvent() {
			$("body").css('-webkit-user-select', 'none');
			var y, height, _height;
			$vernier.mousedown(function(e) {
				var temp = this.offsetTop;
				y = e.clientY;
				height = $listScrollDiv.css('height');
				height = new Number(height.substring(0, height.length - 2));
				_height = $vernier.css('height');
				_height = new Number(_height.substring(0, _height.length - 2));
				$playListDiv.mousemove(function(e) {
					var top = temp + e.clientY - y;
					if (top > (height - _height) || top < 0)
						return false;
					var percent = top / (height - _height);
					var height_ = $playListDiv.find(".list_body .song").css('height');
					height_ = new Number(height_.substring(0, height_.length - 2));
					var num = $playListDiv.find(".list_body .song").length - 1;
					$playListDiv.find(".list_body").scrollTop(percent * (height_ * num - height));
					changeHeight(top);
				})
			});
		}
		function changeHeight(top) {
			$("#listScrollDiv .vernier").css('top', top + "px");
		}
	}
	this.show = function() {
		// $("#volumeDiv").removeAttr('style');
	}
	this.hidden = function() {
		// $("#volumeDiv").css('display','none');
	}
}

function songScrollDiv() {
	var $songScrollDiv = $("#songScrollDiv");
	var $vernier = $songScrollDiv.find(".vernier");
	var $playListDiv = $("#playListDiv");

	this.init = function() {
		$vernier.mouseover(function(event) {
			bindPointEvent();
		});
		$(document).mouseup(function(e) {
			unbindPointEvent();
		})
	}
	this.show = function() {
		// $("#volumeDiv").removeAttr('style');
	}
	this.hidden = function() {
		// $("#volumeDiv").css('display','none');
	}

	function unbindPointEvent() {
		$("body").css('-webkit-user-select', 'inherit');
		$playListDiv.unbind();
		$vernier.unbind().mouseover(function(event) {
			bindPointEvent();
		});
	}
	function bindPointEvent() {
		$("body").css('-webkit-user-select', 'none');
		var y, height, _height;
		$vernier.mousedown(function(e) {
			var temp = this.offsetTop;
			y = e.clientY;
			height = $songScrollDiv.css('height');
			height = new Number(height.substring(0, height.length - 2));
			_height = $vernier.css('height');
			_height = new Number(_height.substring(0, _height.length - 2));
			$playListDiv.mousemove(function(e) {
				var top = temp + e.clientY - y;
				if (top > (height - _height) || top < 0)
					return false;
				var percent = top / (height - _height);
				var height_ = $playListDiv.find(".song_body .lrc p").css('height');
				height_ = new Number(height_.substring(0, height_.length - 2));
				var num = $playListDiv.find(".song_body .lrc p").length;
				$playListDiv.find(".song_body .lrc").scrollTop(percent * (height_ * num - height));

				changeHeight(top);
			})
		});
	}
	function changeHeight(top) {
		$vernier.css('top', top + "px");
	}
}

function volumeDiv(musicDiv) {
	var $volumeDiv = $("#volumeDiv");
	var $musicDiv = $("#musicDiv");
	var flag = false;
	var height_;
	var _musicDiv = musicDiv;
	this.init = function() {
		height_ = $musicDiv.offset().top;
		$musicDiv.find(".volume").click(function() {
			if (flag) {
				flag = false;
				hiddenVolumeDiv();
			} else {
				flag = true;
				showVolumeDiv();
			}
		})
		$volumeDiv.find(".point").mouseover(function(event) {
			bindPointEvent();
		});
		$volumeDiv.find(".max").click(function(e) {
			var max_height = $(this).css('height');
			max_height = new Number(max_height.substring(0, max_height.length - 2));
			var div_height = $volumeDiv.css('height');
			div_height = new Number(div_height.substring(0, div_height.length - 2));
			var length = e.clientY - (height_ - max_height - (div_height - max_height) / 2);
			changeVolume(length, max_height);
		});
		$(document).mouseup(function(e) {
			unbindPointEvent();
		})
	}
	this.show = showVolumeDiv;
	this.hidden = hiddenVolumeDiv;
	this.changeVolumeHeight = changeVolumeHeight;

	function showVolumeDiv() {
		$volumeDiv.removeAttr('style');
	}
	function hiddenVolumeDiv() {
		$volumeDiv.css('display', 'none');
	}
	function unbindPointEvent() {
		$("body").css('-webkit-user-select', 'inherit');
		$volumeDiv.unbind();
		$volumeDiv.find(".point").unbind().mouseover(function(event) {
			bindPointEvent();
		});
	}
	function bindPointEvent() {
		$("body").css('-webkit-user-select', 'none');
		var y, height;
		$volumeDiv.find(".point").mousedown(function(e) {
			var temp = this.offsetTop;
			y = e.clientY;
			height = $volumeDiv.find(".max").css('height');
			height = new Number(height.substring(0, height.length - 2));
			$volumeDiv.mousemove(function(e) {
				var top = temp + e.clientY - y + $volumeDiv.find(".max").offset().top - $volumeDiv.offset().top;
				if (top > height || top < 0)
					return false;
				changeVolume(top, height);
			})
		});
	}
	var $volume = $(".volumeDiv .volume");
	function changeVolume(top, height) {
		_musicDiv.changeVolume((height - top) / height);

		var radio = (height - top) / height;
		if (radio === 0) {
			$volume.text("");
		} else if (radio < 0.3) {
			$volume.text("");
		} else if (radio < 0.7) {
			$volume.text("");
		} else {
			$volume.text("");
		}

		$volumeDiv.find(".point").css('top', top + "px");
		$volumeDiv.find(".now").css('height', height - top + "px");
	}
	function changeVolumeHeight(radio) {
		var height = $volumeDiv.find(".max").height();
		changeVolume(height * (1 - radio), height);
	}
}

function progress(musicDiv) {
	var $musicDiv = $("#musicDiv");
	var $progress = $musicDiv.find(".progress");
	var $playing = $progress.find(".playing");
	var $point = $progress.find(".point");
	var $cache = $progress.find(".downloading");
	var _musicDiv = musicDiv;

	this.init = function() {
		$point.mouseover(function(event) {
			bindPointEvent();
		});

		$progress.click(function(e) {
			var length = e.clientX - $progress.offset().left;
			changeTime(length);
		});

		$(document).mouseup(function(e) {
			unbindPointEvent();
		});
	}
	this.changePlayProgress = function(ratio) {
		var width = ratio * $progress.width();
		$playing.width(width);
		$point.css("left", width + "px");
	}
	this.changeCacheProgress = function(ratio) {
		var width = ratio * $progress.width();
		$cache.width(width);
	}
	function unbindPointEvent() {
		$("body").css('-webkit-user-select', 'inherit');
		$musicDiv.unbind();
		$point.unbind().mouseover(function(event) {
			bindPointEvent();
		});
	}
	function bindPointEvent() {
		$("body").css('-webkit-user-select', 'none');
		var x, width;
		$point.mousedown(function(e) {
			var temp = this.offsetLeft;
			x = e.clientX;
			width = $progress.css('width');
			width = new Number(width.substring(0, width.length - 2));
			$musicDiv.mousemove(function(e) {
				var left = temp + e.clientX - x;
				if (left > width || left < 0)
					return false;
				changeTime(left);
			})
		});
	}
	function changeTime(left) {
		_musicDiv.changePlayTime(parseFloat(left / $progress.width()));
		$point.css('left', left + "px");
		$playing.css('width', left + "px");
	}
}