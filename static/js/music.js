(function() {
	var $musicDiv = $("#musicDiv");
	function Music() {
		var $playButton = $musicDiv.find(".music_left .middle");
		var $play = $musicDiv.find(".music_middle");

		var progressModule = new progress(changeTime);
		var volumeDivModule = new volumeDiv(changeVolume);
		var playListDivModule = new playListDiv();
		function changeTime(ratio) {
			var now = ratio * audio.duration;
			// 谷歌不兼容
			// audio.currentTime = now;
		}
		function changeVolume(ratio) {
			audio.volume = ratio;
		}

		this.play_pause = play_pause;
		this.fix = fix;
		this.changePlayMode = changePlayMode;
		this.clear = clear;
		this.previous = previous;
		this.next = next;
		this.play = play;
		this.remove = remove;
		this.add = add;
		this.showPlayListDiv = playListDivModule.show;
		this.hiddenPlayListDiv = playListDivModule.hidden;
		this.init = init;

		function init() {
			playListDivModule.init();
			progressModule.init();
			volumeDivModule.init();

			var volume_default = 0.3;
			audio.volume = volume_default;
			volumeDivModule.changeVolumeHeight(volume_default);

			playMode_type = playMode_loop;
			playMode(playMode_type);
			songNum = 0;
			songList = new Array();
		}

		var audio = $("#music")[0];
		var songList; // id:Object
		var songNum;
		var $pointer;
		var playMode_type;
		var newSong_Flag = true;
		var $time = $musicDiv.find(".time");
		var timeChangeId;

		function stop() {
			pause();
			newSong_Flag = true;
		}
		function pause() {
			$playButton.text("");
			clearInterval(timeChangeId);
			audio.pause();
		}
		function play(songId) {
			$playButton.text("");

			if (songNum <= 0) { // 必要条件
				return pause();
			}
			if (songId) { // 有指定的songId
				newSong_Flag = true;
				$pointer = playListDivModule.get$(songId);
			} else if ($pointer) {// 没有songId需从$pointer获取
				songId = $pointer.attr("data-songId");
				if (!songList[songId]) {// 存在$pointer，但songList中不一定有对应的song
					$pointer = playListDivModule.getSongs$(songId).eq(0);
					songId = $pointer.attr("data-songId");
				}
			} else {// $pointer不存在，需要先获取$pointer
				$pointer = playListDivModule.getSongs$(songId).eq(0);
				songId = $pointer.attr("data-songId");
			}

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
			$time.removeAttr("style");
			$time.find(".total").text(song.time);
			$time.find(".now").text("00:00");

			if (newSong_Flag) {
				audio.src = "file/" + song.id;
				newSong_Flag = false;
			}
			audio.play();
			loadLrc(songId);

			clearInterval(timeChangeId);
			timeChangeId = setInterval(timeChange, 1000);

			return song.id;
		}

		function timeChange() {
			var now = audio.currentTime;
			var total = audio.duration;
			if (now >= total) {
				clearInterval(timeChangeId);
				return;
			}
			$time.find(".now").text(new DateFormatter("mm:ss").format(now * 1000));

			var playRatio = now / total;
			var cacheRatio = audio.buffered.end(0) / total;
			progressModule.changePlayProgress(playRatio);
			progressModule.changeCacheProgress(cacheRatio);
			changeLrc(now, total);
		}
		function add(songSerialized) {
			var song = deserialize(songSerialized);

			if (songList[song.id]) { // 播放列表中有
				return song.id;
			} else {
				songList[song.id] = song;
				songNum++;
				playListDivModule.add(song);
				return song.id;
			}
		}
		function remove(songId) {
			songList[songId] = undefined;
			songNum--;
			if (songNum === 0) {
				playListDivModule.clear();
			} else {
				playListDivModule.remove(songId);
			}
		}
		function previous() {
			var $songs = playListDivModule.getSongs$();
			var index = $songs.index($pointer) - 1;
			if (index < 0) {
				index = $songs.length - 1;
			}
			$pointer = $songs.eq(index);
			stop();
			play();
		}
		function next() {
			var $songs = playListDivModule.getSongs$();
			var index = $songs.index($pointer) + 1;
			if (index > $songs.length - 1) {
				index = 0;
			}
			$pointer = $songs.eq(index);
			stop();
			play();
		}
		function clear() {
			songList = new Array();
			songNum = 0;
			playListDivModule.clear();
		}
		function fix(that) {
			var fix_id;
			var type = $(that).text();
			if (type == "") {// 固定
				$(that).text("").unbind("hover");
				$musicDiv.unbind("hover");
			} else {// 不固定
				$(that).text("").hover(function() {
					show();
					clearTimeout(fix_id);
				}, function() {
				});
				$musicDiv.hover(function() {
					clearTimeout(fix_id);
				}, function() {
					fix_id = setTimeout(hidden, 1000);
				});
			}
		}
		function show() {
			$musicDiv.stop(true).animate({
				'top' : '100%'
			}, 500);
		}
		var changePlayMode_id;
		function changePlayMode(that) {
			var $i = $(that).siblings("i");
			if ($i.filter("i[style]").length === 0) { // 消失时间间隔内的点击
				clearTimeout(changePlayMode_id);
			}

			$i.removeAttr('style');
			changePlayMode_id = setTimeout(hiddenPlayMode, 1000);

			var playMode = $i.text();
			var icon, text;

			switch (playMode) {
				case '循环' :
					icon = "";
					text = "单曲循环";
					break;
				case '单曲循环' :
					icon = "";
					text = "随机";
					break;
				case '随机' :
					icon = "";
					text = "循环";
					break;
			}

			$(that).text(icon).attr('title', text);
			$i.text(text);
			setPlayMode(text);
		}
		function play_pause(that) {
			var text = $(that).text();
			if (text === "") {
				play();
			} else {
				pause();
			}
		}
		function hidden() {
			$musicDiv.stop(true).animate({
				'top' : '107%'
			}, 500);
		}
		function hiddenPlayMode() {
			$musicDiv.find(".playModeDiv i").css('display', 'none');
		}
		function setPlayMode(_playMode) {
			switch (_playMode) {
				case "循环" :
					playMode_type = playMode_loop;
					break;
				case "随机" :
					playMode_type = playMode_random;
					break;
				default : // 单曲循环
					playMode_type = playMode_singleLoop;
					break;
			}
			playMode(playMode_type);
		}
		function playMode(typeFunction) {
			audio.ontimeupdate = function() {
				if (audio.currentTime >= audio.duration) {
					typeFunction();
				}
			}
		}
		function playMode_random() {
			var $songs = playListDivModule.getSongs$();
			$pointer = $songs.eq(parseInt(Math.random() * $songs.length));
			stop();
			play();
		}
		function playMode_singleLoop() {
			stop();
			play();
		}
		function playMode_loop() {
			var $songs = playListDivModule.getSongs$();
			var num = $songs.index($pointer) + 1;
			if (num === $songs.length) {
				num = 0;
			}
			$pointer = $songs.eq(num);
			stop();
			play();
		}
	}

	var $playListDiv = $("#playListDiv");
	function playListDiv(musicDiv) {

		var listScrollDivModule = new listScrollDiv();
		var songScrollDivModule = new songScrollDiv();

		var $songNum = $("#musicDiv .num");
		var $songList = $playListDiv.find(".list_body");
		var $prototype = $playListDiv.find(".prototype").clone().removeClass("prototype");
		var $message = $(".playListDiv .message");
		var height = parseFloat($playListDiv.find(".playList_hidden_bottom").height());

		this.getSongs$ = getSongs$;
		this.init = init;
		this.clear = clear;
		this.add = add;
		this.get$ = get$;
		this.show = show;
		this.hidden = hidden;

		var messageId;

		function get$(songId) {
			return $songList.children("li[data-songId='" + songId + "']");
		}
		function remove(songId) {
			get$(songId).remove();
			$songNum.text(parseInt($songNum.text()) - 1);
			changeHeight();
		}
		function add(song) {
			$playListDiv.find(".list_body .none").css('display', 'none');

			var $songEle = $prototype.clone();

			$songEle.attr({
				"data-songId" : song.id
			});
			$songEle.find(".songName").attr({
				"title" : song.name,
				"onclick" : "MMR.get('music').play('" + song.id + "');"
			}).text(limitStringLength(song.name, 15));
			$songEle.find(".collection");
			$songEle.find(".delete").attr({
				"onclick" : "MMR.get('music').remove('" + song.id + "');"
			});
			$songEle.find(".songer").attr({
				"title" : song.singerName,
				"data-href" : "singer/{" + song.singerId + "}"
			}).text(limitStringLength(song.singerName, 3));
			$songEle.find(".time").text(song.time);
			// $songEle.find(".from").attr({
			// // "data-href" : "songList/{" + song.id + "}"
			// });

			$songList.append($songEle);

			var songNum = parseInt($songNum.text());
			songNum++;
			$songNum.text(songNum);
			$playListDiv.find(".head_left .num").text(songNum);

			changeHeight();

			clearTimeout(messageId);
			$message.removeAttr("style") //
			.find(".content").text("已添加到播放列表");

			messageId = setTimeout(function() {
				$message.fadeOut("fast");
			}, 1000);
		}
		function getSongs$() {
			return $songList.children().not(".prototype, .none");
		}
		function clear() {
			$songNum.text(0);
			$playListDiv.find(".list_body .song").not(".prototype").remove();
			$playListDiv.find(".list_body .none").css('display', 'block');
			changeHeight();
		}
		function init() {
			listScrollDivModule.init();
			songScrollDivModule.init();

			$playListDiv.find(".song_body .fullScreen").click(function() {
				$playListDiv.find(".song_body .lrc").css('width', '100%')[0].webkitRequestFullScreen();
			})
			$playListDiv.find(".song_body .exitFullScreen").click(function() {
				$playListDiv.find(".song_body .lrc").css('width', '95%');
				document.webkitCancelFullScreen();
			})
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
		function show() {
			$playListDiv.css({
				'display' : 'block',
				'visibility' : 'visible'
			});
		}
		function hidden() {
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

	function volumeDiv(volumeChange) {
		var $volumeDiv = $("#volumeDiv");
		var $musicDiv = $("#musicDiv");
		var flag = false;
		var height_;
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
			});
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
			volumeChange((height - top) / height);

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

	function progress(timeChange) {
		var $musicDiv = $("#musicDiv");
		var $progress = $musicDiv.find(".progress");
		var $playing = $progress.find(".playing");
		var $point = $progress.find(".point");
		var $cache = $progress.find(".downloading");

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
			timeChange(parseFloat(left / $progress.width()));
			$point.css('left', left + "px");
			$playing.css('width', left + "px");
		}
	}

	Music.prototype.addThenPlay = addThenPlay;
	Music.prototype.batchAdd = batchAdd;
	Music.prototype.batchAddThenPlay = batchAddThenPlay;
	Music.prototype.deserialize = deserialize;
	Music.prototype.serialize = serialize;

	function deserialize(songSerialized) {
		songSerialized = songSerialized.replace(/@/g, "'");
		return JSON.parse(songSerialized);
	}
	function serialize(song) {
		var songSerialized = JSON.stringify(song);
		return songSerialized.replace(/'/g, "@");
	}
	function addThenPlay(songSerialized) {
		var songId = music.add(songSerialized);
		music.play(songId);
	}
	function batchAddThenPlay(type, id) {
		batchAdd(type, id, function(songs) {
			music.play(songs[0].id);
		});
	}
	function batchAdd(type, id, callback) {
		$.ajax({
			url : "song/" + type + "_" + id,
			type : "GET",
			dataType : "json",
			success : function(data) {
				if (data.code != 200) {
					MMR.get("simpleMsg").showError(data.error.value);
					return;
				}

				var song //
				, songs = data.data //
				;
				if (!songs || songs.length === 0) {
					return;
				}
				for (var i = 0; i < songs.length; i++) {
					song = songs[i];
					music.add(JSON.stringify(song));
				}

				if (callback && callback instanceof Function) {
					callback(songs);
				}
			},
			error : function(e) {
				console.error(e);
			}
		})
		if (type === "singerId") {
			return;
		}
		$.ajax({
			url : type.replace("Id", "") + "/" + id + "/playNum",
			type : "PUT"
		})
	}

	var $lrcEle = $playListDiv.find(".song_body .lrc");
	function loadLrc(songId) {
		$.ajax({
			url : "song/" + songId + "/lrc",
			dataType : "json",
			success : function(data) {
				if (data.code === 200) {
					$lrcEle.children("p").remove();
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
						var strs = p.match(/\[(\d{2}:\d{2})\.(\d{2})\](.*)[\n\r]/);
						// if (parseInt(strs[2]) / 10 > 4) {
						// var ms = strs[1].split(":");
						// var m = parseInt(ms[0]);
						// var s = parseInt(ms[1]) + 1;
						// if (s === 60) {
						// m += 1;
						// s = 0;
						// }
						// if (m < 10) {
						// m = "0" + m;
						// }
						// if (s < 10) {
						// s = "0" + s;
						// }
						// strs[1] = m + ":" + s;
						// }
						$lrcEle.append("<p data-time=" + strs[1] + ">" + strs[3] + "</p>");
					}
					var ratio = 7 / parseFloat(ps.length);
					if (ratio > 1) {
						ratio = 1;
					}
					var topRatio = parseFloat($songScrollDiv.find(".vernier").css("top")) / $songScrollDiv.height();
					if ((ratio + topRatio) > 1) {
						topRatio = 1 - ratio;
						$songScrollDiv.find(".vernier").css("top", topRatio * $songScrollDiv.height() + "px");
					}
					$songScrollDiv.find(".vernier").css("height", ratio * 100 + "%");
				}
			}
		})
	}
	var $songScrollDiv = $("#songScrollDiv");
	var $childs;
	var totalHeight;
	function changeLrc(now, total) {
		var $now = $lrcEle.find("p[data-time='" + new DateFormatter("mm:ss").format(now * 1000) + "']");//
		$childs = $lrcEle.children();
		totalHeight = parseInt($childs.eq(0).css("line-height")) * $childs.length;
		if ($now.length > 0) {
			$now.addClass("now") //
			.prev().removeClass("now");
			var ratio = now / total;

		}
	}

	var music = new Music();
	music.init();
	MMR.addModule("music", music);
}())