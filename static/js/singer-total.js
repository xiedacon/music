(function() {
	var url = "singer";
	if (PageScope.params.classifyId) {
		if (PageScope.params.classifyId === "all") {
			url = "singer";
		} else if (PageScope.params.classifyId === "hot") {
			url = "singer/hot";
		} else {
			url += "/classifyId_" + PageScope.params.classifyId;
		}
	}
	AJAX({
		url : url,
		success : loadFouction
	});
	AJAX({
		url : "json/userClassify",
		success : loadClassify
	});

	function loadClassify(classifyList) {
		
		if (!classifyList) {
			return;
		}

		var $classifyListEle = $("#classifyList");
		var $prototype = $classifyListEle.children(".prototype").clone().removeClass("prototype");
		var $classifyEle, classify;

		var childId = PageScope.params.classifyId;

		for (var i = 0; i < classifyList.length; i++) {
			$classifyEle = $prototype.clone();
			classify = classifyList[i];

			$classifyEle.find("h3").text(classify.name);

			var childs = classify.childs;
			var $_prototype = $classifyEle.children(".prototype").clone().removeClass("prototype");
			var $child, child;
			for (var j = 0; j < childs.length; j++) {
				$child = $_prototype.clone();
				child = childs[j];

				$child.find(".songerList").attr({
					"data-href" : "singers?classifyId=" + child.id + "&classifyName=" + child.name,
					"title" : child.name
				}).text(child.name);

				if (childId == child.id) {
					$child.addClass("now");
				}

				$classifyEle.append($child);
			}

			$classifyListEle.append($classifyEle);
		}
	}

	function loadFouction(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var data = data.data;
		$("#singerList").css("display", "none");
		$("#simpleSingerList").css("display", "none");
		switch (PageScope.params.classifyId) {
			case "all" :
				$("h2").text("入驻歌手");
				$("#classify_all").addClass("now");
				loadAll(data);
				break;
			case "hot" :
				$("h2").text("热门歌手");
				$("#classify_hot").addClass("now");
				loadHot(data);
				break;
			default :
				$("h2").text(PageScope.params.classifyName);
				loadByClassify(data);
				break;
		}
	}

	function loadByClassify(singerList) {
		loadSingerList(singerList);

		// ajax下拉加载

	}

	function loadHot(data) {
		var top10List = new Array();
		var remainList = new Array();
		for (var i = 0; i < data.length; i++) {
			if (i < 10) {
				top10List[i] = data[i];
			} else {
				remainList[i - 10] = data[i];
			}
		}

		loadSingerList(top10List);
		loadSimpleSingerList(remainList);
	}

	function loadAll(singerList) {
		loadSingerList(singerList);

		// ajax下拉加载

	}

	function loadSingerList(singerList) {
		var $singerListEle = $("#singerList");

		$singerListEle.removeAttr("style") //
		.children().not(".prototype").remove();

		var $prototype = $singerListEle.find(".prototype").clone().removeClass("prototype");
		var $singerEle, singer;

		for (var i = 0; i < singerList.length; i++) {
			$singerEle = $prototype.clone();
			singer = singerList[i];

			$singerEle.find("img").attr({
				"title" : singer.name + "的音乐",
				"src" : singer.icon,
				"data-href" : "singer?singerId=" + singer.id
			});
			$singerEle.find(".name").attr({
				"title" : singer.name + "的音乐",
				"data-href" : "singer?singerId=" + singer.id
			}).text(singer.name);
			if (singer.userId) {
				$singerEle.find(".icon").removeAttr("style").attr({
					"title" : singer.name + "的个人主页",
					"data-href" : "homePage?userId=" + singer.userId
				});
			}

			if ((i + 1) % 5 === 0) {
				$singerEle.css("padding", "25px 0 0 0");
			}

			$singerListEle.append($singerEle);
		}
	}

	function loadSimpleSingerList(singerList) {
		var $simpleSingerListEle = $("#simpleSingerList");

		$simpleSingerListEle.removeAttr("style") //
		.children().not(".prototype").remove();

		var $prototype = $simpleSingerListEle.find(".prototype").clone().removeClass("prototype");
		var $simpleSingerEle, singer;

		for (var i = 0; i < singerList.length; i++) {
			$simpleSingerEle = $prototype.clone();
			singer = singerList[i];

			$simpleSingerEle.find(".name").attr({
				"title" : singer.name + "的音乐",
				"data-href" : "singer?singerId=" + singer.id
			}).text(limitStringLength(singer.name,5.5));
			if (singer.userId) {
				$simpleSingerEle.find(".icon").removeAttr("style").attr({
					"title" : singer.name + "的个人主页",
					"data-href" : "homePage?userId=" + singer.userId
				});
			}

			if ((i + 1) % 5 === 0) {
				$simpleSingerEle.css("padding", "0");
			}

			$simpleSingerListEle.append($simpleSingerEle);
		}
	}
}())