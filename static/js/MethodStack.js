(function() {
	window.FUNCTION = {};
	FUNCTION.loadCommentsAndPages = function (data){
		loadComments("ul#commentList", data);
		loadPages("ul#pages", data.pageBean, FUNCTION.loadCommentsAndPages);
		loadEmojis();
	}

	FUNCTION.loadComments = function (selector, data) {
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
	FUNCTION.loadSongs = function loadSongs(selector, data) {
		var template = `
		{{each songs as song index}}
			<tr id="{{song.id}}" class="{{if index%2==0}}{{else}}singular{{/if}}">
				<td>
					<span class="num">{{index + 1}}</span>
					<i class="play icomoon" onclick="MMR.get('music').addThenPlay('aaa');"></i>
				</td>
				<td>
					<a class="songName" href="#song?id={{song.id}}" title="{{song.name}}">
						{{song.name | lengthLimit:'20'}}
					</a>
				</td>
				<td>
					<span class="time">{{song.time}}</span>
					<p class="hidden">
						<span class="addToPlaylist icomoon" onclick="MMR.get('music').add('{{song.id}}');"></span>
						<span class="collection icomoon" onclick="MMR.get('collection').collect('{{song.id}}');"></span>
						<span class="share icomoon"></span>
						<span class="download icomoon"></span>
					</p>
				</td>
				<td>
					<a class="songer" href="#singer?id={{song.singerId}}" title="{{song.singerName}}">
						{{song.singerName | lengthLimit:'5'}}
					</a>
				</td>
				<td>
					<a class="special" href="#album?id={{song.albumId}}" title="{{song.albumName}}">
						{{song.albumName | lengthLimit:'7'}}
					</a>
				</td>
			</tr>
		{{/each}}
		`;
		document.querySelector(selector).innerHTML = Template.compile(template)(data);
	}
	FUNCTION.loadSongMenus = function (selector, data) {
		var template = `
		{{each pageBean.beans as songMenu index}}
		<li class="songMenu entity" style="{{if (index+1)%5===0}}padding-right:0{{/if}}">
			<div class="image">
				<a href="#songMenu?id={{songMenu.id}}">
					<img alt="{{songMenu.name}}" src="{{songMenu.icon}}" title="{{songMenu.name}}">
				</a>
				<div class="image_bottom">
					<i></i>
					<span class="num">{{songMenu.playNum}}</span>
					<i class="playthis" title="播放" onclick="MMR.get('music').batchAddThenPlay('songMenuId','{{songMenu.id}}')"></i>
				</div>
			</div>
			<a class="name" href="#songMenu?id={{songMenu.id}}" title="{{songMenu.name}}">{{songMenu.name}}</a>
			<span class="by">by</span>
			<a class="songer" href="#home?id={{songMenu.creatorId}}" title="{{songMenu.creatorName}}">{{songMenu.creatorName}}</a>
		</li>
		{{/each}}
		`;

		document.querySelector(selector).innerHTML = Template.compile(template)(data);
	}
	FUNCTION.loadAlbums = function (selector, data) {
		var template = `
		{{each albums as album}}
		<li class="album entity">
			<div class="image">
				<a href="#album?id={{album.id}}">
					<img src="{{album.icon}}" title="{{album.name}}">
				</a>
				<div class="image_left">
					<span class="circle"></span>
					<span class="rectangle"></span>
					<span class="triangle"></span>
				</div>
				<i class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('albumId','{{album.id}}')"></i>
			</div>
			<a href="#album?id={{album.id}}" title="{{album.name}}" class="name">{{album.name}}</a>
			<a href="#singer?id={{album.singerId}}" title="{{album.singerName}}" class="songer">{{album.singerName}}</a>
		</li>
		{{/each}}
		`;

		document.querySelector(selector).innerHTML = Template.compile(template)(data);
	}

	FUNCTION.loadPages = function (selector, data, excutor) {
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
		pagesEle.onclick = function(e) {
			var prefix = data.urlPrefix ? data.urlPrefix : "", //
			suffix = data.urlSuffix ? data.urlSuffix : "" //
			;

			$.ajax({
				url : prefix + e.target.attributes["data-page"].value + suffix,
				dataType : "json",
				type : "GET"
			}).done(function(source) {
				excutor.source = source;
				excutor.excute();
			});
		};
	}
	// 保持高度相等
	FUNCTION.align = function align($ele1, $ele2) {
		var max = Math.max($ele1.outerHeight(), $ele2.outerHeight());
		$ele1.css("min-height", max);
		$ele2.css("min-height", max);
	}

	 FUNCTION.loadEmojis = function (selector) {
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
