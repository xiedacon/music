(function() {
	$.ajax({
		url : "json/index",
		type : "GET",
		dataType : "json"
	}).done(function(data){
		loadSongMenuList(data);
		loadSongMenuSecondTagList(data);
		loadAlbums(data);
		loadUsers(data);
		loadSongLists(data);
	});

	function loadSongMenuSecondTagList(data){
		var template = '{{each songMenuTags as tag}}'
		+                '<li>'
		+	                 '<a href="#songMenus?secondTagId={{tag.id}}">{{tag.name}}</a>'
		+                '</li>'
		+              '{{/each}}';

		document.querySelector("ul#songMenuSecondTagList").innerHTML = Template.compile(template)(data);
	}

	function loadSongMenuList(data){
		var template = '{{each songMenus as songMenu}}'
		+                '<li id="{{songMenu.id}}" class="song">'
		+               	 '<div class="image">'
		+		                 '<a href="#songMenu?id={{songMenu.id}}">'
		+                      '<img alt="{{songMenu.name}}" src="{{songMenu.icon}}" title="{{songMenu.name}}">'
		+                    '</a>'
		+                    '<div class="image_bottom">'
		+                      '<i></i> <span class="num">{{songMenu.playNum}}</span>'
		+                      '<i class="playthis" title="播放" onclick="MMR.get(\'music\').batchAddThenPlay(\'songMenuId\',\'{{songMenu.id}}\')"></i>'
		+                    '</div>'
		+                  '</div>'
		+                '<a href="#songMenu?id={{songMenu.id}}" class="name" title="{{songMenu.name}}">{{songMenu.name | lengthLimit:\'20\'}}</a>'
		+              '</li>'
		+            '{{/each}}';

		document.querySelector("ul#songMenuList").innerHTML = Template.compile(template)(data);
	}

	function loadAlbums(data) {
		var source1 = {
			albums : []
		}, //
		source2 = {
			albums : []
		};

		data.albums.forEach(function(album,index){
			if(index > 4){
				source2.albums.push(album);
			}else{
				source1.albums.push(album)
			}
		})

		var template = '{{each albums as album}}'
		+              '<li class="album">'
		+	               '<div class="image">'
		+		               '<a href="#album?id={{album.id}}">'
		+			               '<img src="{{album.icon}}" title="{{album.name}}" alt="{{album.name}}">'
		+                  '</a>'
		+		             '<div class="image_left">'
		+			             '<span class="circle"></span> <span class="rectangle"></span> <span class="triangle"></span>'
		+		             '</div>'
		+		             '<i class="play" title="播放" onclick="MMR.get(\'music\').batchAddThenPlay(\'albumId\',\'{{album.id}}\')"></i>'
		+	             '</div>'
		+	             '<a title="{{album.name}}" class="name" href="#album?id={{album.id}}">{{album.name | lengthLimit:\'6\'}}</a>'
		+		           '<a title="{{album.singerName}}"class="songer" href="#singer?id={{album.singerId}}">{{album.singerName | lengthLimit:\'6\'}}</a>'
		+						 '</li>'
		+            '{{/each}}';

		document.querySelector("ul#albumList1").innerHTML = Template.compile(template)(source1);
		document.querySelector("ul#albumList2").innerHTML = Template.compile(template)(source2);
		albumDiv.init();
	}

	function loadUsers(data){
		var template = `{{each users as user}}
		<li>
			<a href="#home?id={{user.id}}">
				<img src="{{user.icon}}" title="{{user.name}}" alt="{{user.name}}">
				<p>
					<span class="name" title="{{user.name}}">{{user.name | lengthLimit:'9'}}</span>
					<span class="desc" title="{{user.singerName}}">{{user.introduction | lengthLimit:'9'}}</span>
				</p>
			</a>
		</li>
		{{/each}}`;

		document.querySelector("ul#userList").innerHTML = Template.compile(template)(data);
	}

	function loadSongLists(data) {
		var template = `{{each songLists as songList}}
		<li id="{{songList.id}}">
			<ul>

			</ul>
		</li>
		{{/each}}`;

		var songListsEle = document.querySelector("ul#songLists");
		songListsEle.innerHTML = Template.compile(template)(data);
		var songListEles = songListsEle.querySelectorAll("ul");
		document.querySelectorAll(".head_bottom .subnav li")[1].children[0].href = "#songList?songListId=" + data.songLists[0].id;
		document.querySelector(".lists .more").href = "#songList?songListId=" + data.songLists[0].id;

		data.songLists.forEach(function(songList, index){
			loadSongs(songList, songListEles[index]);
		});
	}
	function loadSongs(songList, songListEle) {
		var template = `<li>
			<a href="#songList?id={{id}}">
				<img title="{{name}}" src="{{icon}}" alt="{{name}}">
			</a>
			<div class="right">
				<a href="#songList?id={{id}}">
					<h3 title="{{name}}">{{name}}</h3>
				</a>
				<i class="play" title="播放" style="-webkit-user-select: none;" onclick="MMR.get('music').batchAddThenPlay('songListId','{{id}}')"></i>
				<!-- <i class="collection" title="收藏" style="-webkit-user-select: none;"></i> -->
			</div>
		</li>
		{{each songs as song index}}
		<li id={{song.id}} class="{{if index%2==0}}odd{{else}}even{{/if}}">
			<i class="num" style="-webkit-user-select: none;">{{song.rank}}</i>
			<a title="{{song.name}}" class="name" href="#song?id={{song.id}}">{{song.name | lengthLimit:'13'}}</a>
			<span class="hidden">
				<i class="play" title="播放" onclick="MMR.get('music').addThenPlay('{{song.id}}');"></i>
				<i class="add" title="添加到播放列表" onclick="MMR.get('music').add('{{song.id}}');"></i>
				<i class="collection" title="收藏" onclick="MMR.get('collection').collect('{{song.id}}');"></i>
			</span>
		</li>
		{{/each}}
		<li>
			<a class='all' href='#songList?id={{id}}'>查看全部></a>
		</li>`;
		$.ajax({
			url : "song/songListId_" + songList.id+ "/10",
			type : "GET",
			dataType : "json",
			success : function(data) {
				process(data);
				songList.songs = data.data;
				songListEle.innerHTML = Template.compile(template)(songList);
			}
		})
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
				"data-href" : "home?userId=" + user.id
			});
			$login.find(".name").attr({
				"data-href" : "home?userId=" + user.id
			}).text(user.name);
			// $login.find(".level").text(user.level);
			// var $nums = $login.find(".userDiv_bottom .num")
			// $nums.eq(0).text(user.dynamicNum);
			// $nums.eq(1).text(user.attentionNum);
			// $nums.eq(2).text(user.fansNum);
		}
	}
	imageDiv.init();
	userDiv();
})()
