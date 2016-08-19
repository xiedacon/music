(function() {
	var _realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(_realUrl);
	var orderBy = "hot";

	var rootRealUrl = _realUrl.split("/")[0];
	var realUrl = _realUrl;

	if (rootRealUrl !== _realUrl) {
		$("#tagName").text(_realUrl.split("?")[1]);
		realUrl = _realUrl.split("?")[0];
	}
	router.getPageScope(_realUrl).setAttribute("orderBy", orderBy);
	router.getPageScope(_realUrl).setAttribute("tagId", realUrl.split("/")[1]);

	var ajaxConfigs = new router.AjaxConfigs(_realUrl);
	ajaxConfigs.setAjaxConfigs({
		url : realUrl + "/" + orderBy + "/1",
		success : loadSongMenuList
	}, {
		url : rootRealUrl + "/tags",
		success : loadSongMenuSecondTag
	});

	ajaxConfigs.startAjaxs();

	init();

	function init() {
		$("#select").click(function() {
			var flag = $(this).attr("data-flag");
			if (flag === "hidden") {
				$(this).siblings().removeAttr("style");
				$(this).attr("data-flag", "show");
			} else {
				$(this).siblings().attr("style", "display: none");
				$(this).attr("data-flag", "hidden");
			}
		})
		$(".orderBy").click(function() {
			$(this).addClass("now");
			$(this).siblings().removeClass("now");

			var orderBy = $(this).attr("data-orderBy");
			pageScope.setAttribute("orderBy", orderBy);

			if (!ajaxConfigs.hasAjaxConfig(realUrl + "/" + orderBy + "/1")) {
				ajaxConfigs.setAjaxConfig({
					url : realUrl + "/" + orderBy + "/1",
					success : loadSongMenuList
				});
			}

			ajaxConfigs.startAjax(realUrl + "/" + orderBy + "/1");
		})
	}

	function loadSongMenuSecondTag(tagList) {
		var $tagListEle = $("#secondTagList");
		var $prototype = $tagListEle.children(".prototype").clone().removeClass("prototype");
		var tag, $tagEle, secondTagList, $secondTagListEle;

		for (var i = 0; i < tagList.length; i++) {
			tag = tagList[i];
			$tagEle = $prototype.clone();

			$tagEle.find("h3 span").text(tag.name);

			secondTagList = tag.secondTagList;
			$secondTagListEle = $tagEle.find(".types");

			var $_prototype = $secondTagListEle.find(".prototype").clone().removeClass("prototype");
			var secondTag, $secondTagEle;

			for (var j = 0; j < secondTagList.length; j++) {
				secondTag = secondTagList[j];
				$secondTagEle = $_prototype.clone();

				$secondTagEle.attr({
					"data-href" : "songMenus/{" + secondTag.id + "}?" + secondTag.name,
					"title" : secondTag.name
				}).text(secondTag.name)

				if (secondTag.id == pageScope.getAttribute("tagId")) {
					$secondTagEle.addClass("select");
				}

				$secondTagListEle.append($secondTagEle);

				if (j == secondTagList.length - 1) {
					continue;
				}
				$secondTagListEle.append("<span>|</span>");
			}

			$tagListEle.append($tagEle);
		}
	}

	function loadSongMenuList(pageBean) {
		var songMenuList = pageBean.beans;
		var $songMenuListEle = $("#songMenuList");
		$songMenuListEle.children().not(".prototype").remove();
		var $prototype = $songMenuListEle.find(".prototype").clone().removeClass("prototype");
		var songMenu, $songMenuEle;

		for (var i = 0; i < songMenuList.length; i++) {
			songMenu = songMenuList[i];
			$songMenuEle = $prototype.clone();

			$songMenuEle.find("img").attr({
				"src" : songMenu.icon,
				"alt" : songMenu.name,
				"title" : songMenu.name,
				"data-href" : "songMenu/{" + songMenu.id + "}"
			});
			$songMenuEle.find(".num").text(songMenu.playNum);
			$songMenuEle.find(".playthis").attr({
				"onclick" : "hiddenDiv.playFromSongMenu('" + songMenu.id + "')"
			});
			$songMenuEle.find(".name").attr({
				"title" : songMenu.name,
				"data-href" : "songMenu/{" + songMenu.id + "}"
			}).text(songMenu.name);
			$songMenuEle.find(".songer").attr({
				"title" : songMenu.creatorName,
				"data-href" : "home/{" + songMenu.creatorId + "}"
			}).text(songMenu.creatorName);
			if ((i + 1) % 5 === 0) {
				$songMenuEle.css("padding-right", 0);
			}

			$songMenuListEle.append($songMenuEle);
		}

		var $pagesEle = $("#pages");
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

				if (!ajaxConfigs.hasAjaxConfig(realUrl + "/" + orderBy + "/" + page)) {
					var orderBy = pageScope.getAttribute("orderBy");
					ajaxConfigs.setAjaxConfig({
						url : realUrl + "/" + orderBy + "/" + page,
						success : loadSongMenuList
					});
				}

				ajaxConfigs.startAjax(realUrl + "/" + orderBy + "/" + page);
			})
		}
	}
}())