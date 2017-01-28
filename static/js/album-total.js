(function() {

	PageScope["tagId"] = "all";

	AJAX({
		url : "json/index",
		success : loadHotAlbumList
	});
	AJAX({
		url : "album/tagId_" + PageScope["tagId"] + "/1",
		success : loadAlbumList
	});
	AJAX({
		url : "albumTag",
		success : loadTagList
	});

	function loadTagList(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var tagList = data.data //
		, $tagListEle = $("#tags");
		var $prototype = $tagListEle.find(".prototype").clone().removeClass("prototype");
		var tag, $tagEle;

		// 全部
		$tagEle = $prototype.clone();
		$tagEle.attr({
			"data-id" : "all"
		}).text("全部");
		$tagListEle.append($tagEle);
		$tagListEle.append("<span>|</span>");

		for (var i = 0; i < tagList.length; i++) {
			tag = tagList[i];
			$tagEle = $prototype.clone();
			$tagEle.attr({
				"data-id" : tag.id
			}).text(tag.name);

			$tagListEle.append($tagEle);

			if (i === tagList.length - 1) {
				continue;
			}

			$tagListEle.append("<span>|</span>");
		}

		$tagListEle.find(".type").not(".prototype").click(function() {
			var id = $(this).attr("data-id");
			var name = $(this).text();
			$("#tags").siblings().text(name);

			PageScope["tagId"] = id;

			AJAX({
				url : "album/tagId_" + PageScope["tagId"] + "/1",
				success : function(pageBean) {
					loadAlbumList(pageBean);
				}
			});
		})
	}

	function loadHotAlbumList(data) {
		var $hotListEle = $("#hotList");
		loadAlbumList_base($hotListEle, data.albums);
	}

	function loadAlbumList(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var pageBean = data.data //
		, $albumListEle = $("#albumList");
		$albumListEle.children().not(".prototype").remove();
		
		FUNCTION.loadPageBean({
			pageBean : pageBean,
			$pagesEle : $("#pages"),
			loadBeans : function() {
				loadAlbumList_base($albumListEle, pageBean.beans);
			},
			click : function(page) {
				AJAX({
					url : "album/tagId_" + PageScope["tagId"] + "/" + page,
					success : function(pageBean) {
						loadAlbumList(pageBean);
					}
				});
			}
		});
	}

	function loadAlbumList_base($albumListEle, albumList) {
		var $prototype = $albumListEle.find(".prototype").clone().removeClass("prototype");
		var album, $albumEle;

		for (var i = 0; i < albumList.length; i++) {
			album = albumList[i];
			$albumEle = $prototype.clone();
			FUNCTION.loadAlbum(album, $albumEle);
			$albumListEle.append($albumEle);
		}
	}

}())