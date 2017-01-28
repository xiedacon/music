PageScope.params.userId? "" : PageScope.params.userId = UserManager.getUserId();
(function() {
	AJAX({
		url : "user/" + PageScope.params.userId,
		success : loadUser
	});
	AJAX({
		url : "songMenu/creatorId_" + PageScope.params.userId,
		success : loadSongMenus_create
	});
	AJAX({
		url : "songMenu/collectorId_" + PageScope.params.userId,
		success : loadSongMenus_collect
	});

	function loadUser(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var user = data.data //
		, $userEle = $("#user");

		$userEle.find("img").attr("src", user.icon);
		$userEle.find("h2").text(user.name);
		$userEle.find(".level").text("Lv." + user.level);
		$userEle.find(".attention");
		if (user.singerId) {
			$userEle.find(".toSongerPage").removeAttr("style").attr({
				"data-href" : "singer?singerId=" + user.singerId
			});
		}

		if (user.attentionNum || user.attentionNum === 0) {
			$userEle.find(".attention .num").text(user.attentionNum);
		}
		if (user.fansNum || user.fansNum === 0) {
			$userEle.find(".fans .num").text(user.fansNum);
		}

		$details = $userEle.find(".entityMessage_right")
		if (user.introduction) {
			$details.append("<p class='detail'>个人介绍：" + user.introduction + "</p>")
		}
		if (user.area) {
			$details.append("<p class='detail'>所在地区：" + user.area + "</p>")
		}
		if (user.age) {
			$details.append("<p class='detail'>年龄：" + user.age + "</p>")
		}

		$userEle.find(".createSongMenuNum").text(user.createSongMenuNum);
		if (user.createSongMenuNum || user.createSongMenuNum > 0) {
			$(".createSongMenu").text(user.name + "创建的歌单（" + user.createSongMenuNum + "）") //
			.parent().removeAttr("style");
		}
		if (user.collectSongMenuNum && user.collectSongMenuNum > 0) {
			$(".collectionSongMenu").text(user.name + "收藏的歌单（" + user.collectSongMenuNum + "）") //
			.parent().removeAttr("style");
		}
	}

	function loadSongMenus_create(data) {
		var ele = $(".createSongMenu").siblings("ul")[0];
		loadSongMenus(data, ele);
	}

	function loadSongMenus_collect(data) {
		var ele = $(".collectionSongMenu").siblings("ul")[0];
		loadSongMenus(data, ele);
	}

	function loadSongMenus(data, ele) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var songMenus = data.data //
		, $songMenuListEle = $(ele);
		var $prototype = $songMenuListEle.find(".prototype").clone().removeClass("prototype");
		var $songMenuEle, songMenu;

		for (var i = 0; i < songMenus.length; i++) {
			$songMenuEle = $prototype.clone();
			songMenu = songMenus[i];
			$songMenuEle.find("img").attr({
				"alt" : songMenu.name,
				"src" : songMenu.icon,
				"title" : songMenu.name
			});
			$songMenuEle.find("img").parent().attr({
				"data-href" : "songMenu?songMenuId=" + songMenu.id
			});
			$songMenuEle.find(".num").text(songMenu.playNum);
			$songMenuEle.find(".name").attr({
				"title" : songMenu.name,
				"data-href" : "songMenu?songMenuId=" + songMenu.id
			}).text(limitStringLength(songMenu.name, 20));

			$songMenuEle.attr("id", songMenu.id);
			if ((i + 1) % 5 === 0) {
				$songMenuEle.css("padding", "25px 0 0 0")
			}

			$songMenuListEle.append($songMenuEle);
		}
	}
}())
