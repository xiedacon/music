(function() {
	PageScope["orderBy"] = "hot";
	if (PageScope.params["secondTagId"]) {
		PageScope["supplement"] = "/secondTagId_" + PageScope.params["secondTagId"];
	} else {
		PageScope["supplement"] = "";
	}
	AJAX({
		url : "songMenu/" + PageScope["orderBy"] + "_1" + PageScope["supplement"],
		success : loadSongMenuList
	});
	AJAX({
		url : "songMenuTag",
		success : loadSongMenuSecondTag
	});

	init();

	function init() {
		$("#tagName").text(PageScope.params["secondTagName"]);
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
			PageScope["orderBy"] = orderBy;
			AJAX({
				url : "songMenu/" + orderBy + "_1" + PageScope["supplement"],
				success : loadSongMenuList
			});
		})
	}

	function loadSongMenuSecondTag(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var tagList = data.data //
		, $tagListEle = $("#secondTagList");
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
					"data-href" : "songMenus?secondTagId=" + secondTag.id + "&secondTagName=" + secondTag.name,
					"title" : secondTag.name
				}).text(secondTag.name)

				if (secondTag.id == PageScope.params["secondTagId"]) {
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

	function loadSongMenuList(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var pageBean = data.data //
		, songMenuList = pageBean.beans;
		var $songMenuListEle = $("#songMenuList");
		$songMenuListEle.children().not(".prototype").remove();
		var $prototype = $songMenuListEle.find(".prototype").clone().removeClass("prototype");
		var songMenu, $songMenuEle;

		FUNCTION.loadPageBean({
			pageBean : pageBean,
			$pagesEle : $("#pages"),
			loadBeans : function() {
				for (var i = 0; i < songMenuList.length; i++) {
					songMenu = songMenuList[i];
					$songMenuEle = $prototype.clone();
					FUNCTION.loadSongMenu(songMenu, $songMenuEle, i);
					$songMenuListEle.append($songMenuEle);
				}
			},
			click : function(page) {
				var orderBy = PageScope["orderBy"];
				AJAX({
					url : "songMenu/" + orderBy + "_" + page + PageScope["supplement"],
					success : loadSongMenuList
				});
			}
		});
	}
}())