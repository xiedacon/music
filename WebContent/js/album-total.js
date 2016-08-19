(function() {

	var realUrl = document.getElementById("realUrl").innerHTML;
	var pageScope = router.getPageScope(realUrl);
	var ajaxConfigs = new router.AjaxConfigs(realUrl);

	pageScope.setAttribute("tagId", "all");

	ajaxConfigs.setAjaxConfigs({
		url : realUrl + "/hot",
		success : loadHotAlbumList
	}, {
		url : realUrl + "/" + pageScope.getAttribute("tagId") + "/1",
		success : loadAlbumList
	}, {
		url : realUrl + "/tags",
		success : loadTagList
	});

	ajaxConfigs.startAjaxs();

	function loadTagList(tagList) {
		var $tagListEle = $("#tags");
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

			pageScope.setAttribute("tagId", id);

			if (!ajaxConfigs.hasAjaxConfig(realUrl + "/" + pageScope.getAttribute("tagId") + "/1")) {
				ajaxConfigs.setAjaxConfig({
					url : realUrl + "/" + pageScope.getAttribute("tagId") + "/1",
					success : function(pageBean) {
						loadAlbumList(pageBean);
					}
				});
			}

			ajaxConfigs.startAjax(realUrl + "/" + pageScope.getAttribute("tagId") + "/1");
		})
	}

	function loadHotAlbumList(albumList) {
		var $hotListEle = $("#hotList");
		loadAlbumList_base($hotListEle, albumList);
	}

	function loadAlbumList(pageBean) {
		var $albumListEle = $("#albumList");
		$albumListEle.children().not(".prototype").remove();
		loadAlbumList_base($albumListEle, pageBean.beans);
		loadPage(pageBean);
	}

	function loadAlbumList_base($albumListEle, albumList) {
		var $prototype = $albumListEle.find(".prototype").clone().removeClass("prototype");
		var album, $albumEle;

		for (var i = 0; i < albumList.length; i++) {
			album = albumList[i];
			$albumEle = $prototype.clone();

			$albumEle.find("img").attr({
				"src" : album.icon,
				"title" : album.name,
				"data-href" : "album/{" + album.id + "}"
			});
			$albumEle.find(".play").attr({
				"onclick" : "hiddenDiv.playFromAlbum('" + album.id + "')"
			});
			$albumEle.find(".name").attr({
				"title" : album.name,
				"data-href" : "album/{" + album.id + "}"
			}).text(album.name);
			$albumEle.find(".songer").attr({
				"title" : album.singerName,
				"data-href" : "singer/{" + album.singerId + "}"
			}).text(album.singerName);

			$albumListEle.append($albumEle);
		}
	}

	function loadPage(pageBean) {
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

				if (!ajaxConfigs.hasAjaxConfig(realUrl + "/" + pageScope.getAttribute("tagId") + "/" + page)) {
					ajaxConfigs.setAjaxConfig({
						url : realUrl + "/" + pageScope.getAttribute("tagId") + "/" + page,
						success : function(pageBean) {
							loadAlbumList(pageBean);
						}
					});
				}

				ajaxConfigs.startAjax(realUrl + "/" + pageScope.getAttribute("tagId") + "/" + page);
			})
		}
	}
}())