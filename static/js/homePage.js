(function() {
	PageScope.params.id? "" : PageScope.params.id = UserManager.getUserId();

	$.ajax({
		url : "user/" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		var template = `
		<div class=" entityMessage_left">
			<img src="{{user.icon}}">
		</div>
		<div class="entityMessage_right">
			<div class="right_top">
				<h2>{{user.name}}</h2>
				<span class="level">Lv.{{user.level}}</span>
				<span class="attention">
					<i class="icomoon"></i>关注
				</span>
				{{if user.singerId}}
				<a href="#singer?id={{user.singerId}}" class="toSongerPage">查看歌手页</a>
				{{/if}}
				<!--
				<p class="authentication">
					<s><i>v</i></s> <span>哈速度很快</span>
				</p>
				 -->
			</div>
			<div class="dynamic_attention_fans">
				<!--
				<p class="dynamic">
					<span class="num">213</span> 动态
				</p>
				-->
				{{if user.attentionNum}}
				<p class="attention attentionNum">
					<span class="num">{{user.attentionNum}}</span> 关注
				</p>
				{{/if}}
				{{if user.fansNum}}
				<p class="fans">
					<span class="num">{{user.fansNum}}</span> 粉丝
				</p>
				{{/if}}
			</div>
			{{if user.introduction}}
			<p class='detail'>个人介绍：{{user.introduction}}</p>
			{{/if}}
			{{if user.area}}
			<p class='detail'>所在地区：{{user.area}}</p>
			{{/if}}
			{{if user.age}}
			<p class='detail'>年龄：{{user.age}}</p>
			{{/if}}
		</div>
		`, //
		data = {
			user : process(source)
		};

		document.querySelector("div#user").innerHTML = Template.compile(template)(data);
		if (data.user.createSongMenuNum) {
			var createSongMenu = document.querySelector("h3#createSongMenu");//
			createSongMenu.innerHTML = data.user.name + "创建的歌单（" + data.user.createSongMenuNum + "）";
			createSongMenu.parentNode.style = "";
		}
		if (data.user.collectSongMenuNum) {
			var collectionSongMenu = document.querySelector("h3#collectionSongMenu");//
			collectionSongMenu.innerHTML = data.user.name + "收藏的歌单（" + data.user.collectSongMenuNum + "）";
			collectionSongMenu.parentNode.style = "";
		}

	});

	$.ajax({
		url : "songMenu/creatorId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		FUNCTION.loadSongMenus("ul#createSongMenus",{
			songMenus:process(source)
		});
	});

	$.ajax({
		url : "songMenu/collectorId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		FUNCTION.loadSongMenus("ul#collectionSongMenus",{
			songMenus:process(source)
		});
	});
}())
