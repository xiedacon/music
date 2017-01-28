(function() {
	function loadComments(selector, data) {
		var template = `
			{{if hotList && hotList.length > 0}}
			<h3>最热评论<span>{{hotList.length}}</span></h3>
			{{each hotList as comment}}
			<li class="comment">
				<a href="#home?id={{comment.creatorId}}">
					<img src="{{comment.creatorIcon}}">
				</a>
				<div class="comment_left">
					<p class="comment_material">
						<a class="name" href="#home?id={{comment.creatorId}}" title="{{comment.creatorName}}">
							{{comment.creatorName}}
						</a>
						<span class="content">
							:{{#comment.content}}
						</span>
					</p>
					<p class="comment_reply" style="display: none">
						<i></i> <a class="name" href="javascript:void(0);" data-href="" onclick="jump(this);"></a> <span class="content"></span>
					</p>
					<div class="comment_bottom">
						<span class="createTime">
							{{comment.createTime | dateFormatter:'MM月dd号 HH:mm'}}
						</span>
						<div class="right">
							<p>
								<i class="icomoon"></i>
								<span class="num" onclick="MMR.agreeComment(this,'{{comment.id}}');">
									{{comment.agreeNum}}
								</span>
							</p>
							<span class="interval">|</span> <span class="reply">回复</span>
						</div>
					</div>
				</div>
			</li>
			{{/each}}
			{{/if}}
			<h3>最新评论<span>{{pageBean.count}}</span></h3>
			{{each pageBean.beans as comment}}
			<li class="comment">
				<a href="#home?id={{comment.creatorId}}">
					<img src="{{comment.creatorIcon}}">
				</a>
				<div class="comment_left">
					<p class="comment_material">
						<a class="name" href="#home?id={{comment.creatorId}}" title="{{comment.creatorName}}">
							{{comment.creatorName}}
						</a>
						<span class="content">
							:{{#comment.content}}
						</span>
					</p><!--
					<p class="comment_reply" style="display: none">
						<i></i> <a class="name" href="javascript:void(0);" data-href="" onclick="jump(this);"></a> <span class="content"></span>
					</p>-->
					<div class="comment_bottom">
						<span class="createTime">
							{{comment.createTime | dateFormatter:'MM月dd号 HH:mm'}}
						</span>
						<div class="right">
							<p onclick="MMR.agreeComment(this,'{{comment.id}}');">
								<i class="icomoon"></i>
								<span class="num">
									{{comment.agreeNum}}
								</span>
							</p>
							<span class="interval">|</span> <span class="reply">回复</span>
						</div>
					</div>
				</div>
			</li>
			{{/each}}
		`;

		document.querySelector(selector).innerHTML = Template.compile(template)(data);
	}
	function loadPages(selector, data) {
		var pageBean = data.pageBean;
		if (pageBean.totalPage < 2) {
			return;
		}

		var pagesEle = document.querySelector(selector), //
		template = '';
		Array.from(pagesEle.children?pagesEle.children:[]).forEach(function(pageEle){
			pagesEle.removeChild(pageEle);
		})

		Page({
			addEle : function(index) {
				var className = "button num ";
				if (index == pageBean.page) {
					className += "unable ";
				}
				template += '<li class="'+className+'" data-page="'+index+'">' + index + '</li>';
			},
			addPrevious : function() {
				template += '<li class="button" data-page="'+(pageBean.page-1)+'">上一页</li>';
			},
			addNext : function() {
				template += '<li class="button" data-page="'+(pageBean.page+1)+'">下一页</li>';
			},
			totalPage : pageBean.totalPage,
			page : pageBean.page,
			limit : pageBean.limit
		});

		pagesEle.innerHTML = template;
		pagesEle.addEventListener("click", function(e) {
			$.ajax({
				url : data.urlPrefix + e.target.attributes["data-page"].value,
				dataType : "json",
				type : "GET"
			}).done(function(source) {
				process(source);
				source.data.urlPrefix = data.urlPrefix;
				FUNCTION.loadCommentsAndPages(source.data);
			});
		})

		FUNCTION.align($(".material_right"), $(".material_left"));
	}

	FUNCTION.loadCommentsAndPages = function loadCommentsAndPages(data){
		loadComments("ul#commentList", data);
		loadPages("ul#pages", data);
		FUNCTION.loadEmojis();
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

	FUNCTION.loadEmojis = function(selector) {
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

		$.ajax({
			url : "json/emoji",
			type : "GET",
			dataType : "json",
			success : function(emojis) {
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
