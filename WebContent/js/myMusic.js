(function() {
	AJAX({
		url : "songMenu/s/creatorId_" + PageScope.params.userId,
		success : loadCreatedSongMenus
	});
	AJAX({
		url : "songMenu/s/collectorId_" + PageScope.params.userId,
		success : loadCollectedSongMenus
	});

	function loadSongMenu(songMenuId) {
		var $songMenus = $(".material_right li").not(".prototype");
		$songMenus.filter("li[data-id='" + songMenuId + "']").addClass("now") //
		$songMenus.filter("li[data-id!='" + songMenuId + "']").removeClass("now");

		PageScope.loadForFirst = "comment/s/songMenuId_" + songMenuId;
		PageScope.loadPageBean = "comment/s/songMenuId_" + songMenuId + "/";

		$("#showPage").css("display", "block").siblings().css("display", "none");

		AJAX({
			url : "songMenu/" + songMenuId,
			success : _loadSongMenu
		});
		AJAX({
			url : "song/s/songMenuId_" + songMenuId,
			success : FUNCTION.loadSongs
		});
		AJAX({
			url : PageScope.loadForFirst,
			success : FUNCTION.loadForFirst
		});
	}

	function loadCreatedSongMenus(data) {
		var $ele = $("#createdSongMenus");
		if (data.length > 0) {
			$ele.siblings("#createdSongMenusHead").find(".content").text("创建的歌单(" + data.length + ")");
			loadSongMenus($ele, data);
		} else {
			$ele.css("display", "none");
			$ele.siblings("#createdSongMenusHead").css("display", "none");
		}
	}

	function loadCollectedSongMenus(data) {
		var $ele = $("#collectedSongMenus");
		if (data.length > 0) {
			$ele.siblings("#collectedSongMenusHead").find(".content").text("收藏的歌单(" + data.length + ")");
			loadSongMenus($ele, data);
		} else {
			$ele.css("display", "none");
			$ele.siblings("#collectedSongMenusHead").css("display", "none");
		}
	}

	function loadSongMenus($ele, songMenus) {
		var $songMenusEle = $ele;
		var $prototype = $songMenusEle.find(".prototype").clone().removeClass("prototype");
		var $songMenuEle, songMenu;

		for (var i = 0; i < songMenus.length; i++) {
			$songMenuEle = $prototype.clone();
			songMenu = songMenus[i];

			$songMenuEle.find("img").attr({
				"src" : songMenu.icon
			});

			var userId = UserManager.getUserId();

			if (songMenu.userId && userId === songMenu.userId) {
				$songMenuEle.find(".name").text("我" + songMenu.name);
			} else if (songMenu.userId) {
				$songMenuEle.find(".name").text(songMenu.creatorName + songMenu.name);
			} else {
				$songMenuEle.find(".name").text(songMenu.name);
			}
			$songMenuEle.find(".num").text(songMenu.songNum + "首");

			$songMenuEle.attr("data-id", songMenu.id).click(function() {
				loadSongMenu($(this).attr("data-id"));
			});

			if (songMenu.userId === userId) {// 我喜欢的音乐，无法删除或修改
				$songMenusEle.prepend($songMenuEle);
				loadSongMenu(songMenu.id);
				continue;
			}

			$songMenuEle.find(".option_rightF").removeAttr("style");

			if (songMenu.creatorId === userId) {
				$songMenuEle.find(".edit").removeAttr("style").on("click", function() {
					var songMenuId = $(this).parents(".option").attr("data-id");
					showEditPage(songMenuId);
					return false;
				});
			}

			$songMenuEle.find(".delete").on("click", function() {
				MMR.deleteSongMenu(this);
				return false;
			});

			$songMenusEle.append($songMenuEle);
		}
	}

	function showEditPage(songMenuId) {
		$("#createdSongMenus .option,#collectedSongMenus .option").removeClass("now");
		var $editPage = $("#editPage");
		$.ajax({
			url : "songMenu/" + songMenuId,
			dataType : "json",
			type : "GET",
			success : function(songMenu) {
				$editPage.attr("data-id", songMenu.id);
				$editPage.find(".name").attr({
					"data-href" : "songMenu?songMenuId=" + songMenu.id
				}).text(songMenu.name);
				$editPage.find("input").val(songMenu.name);
				if (songMenu.introduction) {
					$editPage.find(".introduction").text(songMenu.introduction);
				}
				var $tags = $editPage.find(".tags");
				$tags.children(".tag").remove();
				if (songMenu.songMenuSecondTagList) {
					var tagList = songMenu.songMenuSecondTagList;
					var tag;
					for (var i = 0; i < tagList.length; i++) {
						tag = tagList[i];
						$tags.prepend("<span data-id='" + tag.id + "' class='tag'>" + tag.name + "<i class='icomoon'></i></span>");
					}
					$tags.find("i").on("click", function() {
						$(this).parent().remove();
					})
				}
				$editPage.find("img").attr("src", songMenu.icon)
				$editPage.find(".toImageEditPage").on("click", showImageEditPage);

				$editPage.css("display", "block").siblings().css("display", "none");
			}
		})
	}

	function showImageEditPage() {
		var $imageEditPage = $("#imageEditPage");
		var imageSrc = $("#editPage img").attr("src");
		$imageEditPage.find(".name").attr({
			"data-href" : $("#editPage .name").attr("data-href")
		}).text($("#editPage .name").text());

		$imageEditPage.find(".turnToEditPage").on("click", function() {
			$("#editPage").css("display", "block").siblings().css("display", "none");
		});

		// chooseImage();
		$imageEditPage.css("display", "block").siblings().css("display", "none");
	}

	function _loadSongMenu(songMenu) {
		var $songMenuEle = $("#songMenu");
		$songMenuEle.find(".songMenuMessage_left img").attr({
			"src" : songMenu.icon
		});

		var userId = UserManager.getUserId();

		if (songMenu.userId && userId === songMenu.userId) {
			$songMenuEle.find(".songMenuMessage_right h2").text("我" + songMenu.name);
		} else if (songMenu.userId) {
			$songMenuEle.find(".songMenuMessage_right h2").text(songMenu.creatorName + songMenu.name);
		} else {
			$songMenuEle.find(".songMenuMessage_right h2").text(songMenu.name);
		}

		$songMenuEle.find(".creator_time .creator img").attr({
			"src" : songMenu.creatorIcon,
			"title" : songMenu.creatorName,
			"data-href" : "home/{" + songMenu.creatorId + "}"
		});
		$songMenuEle.find(".creator .creatorName").attr({
			"title" : songMenu.creatorName,
			"data-href" : "home/{" + songMenu.creatorId + "}"
		}).text(songMenu.creatorName);
		$songMenuEle.find(".createTime").text(new DateFormatter("yyyy-MM-dd").format(songMenu.createTime) + " 创建");
		$songMenuEle.find(".play_addToPlayList .play").attr({
			"onclick" : "hiddenDiv.playFromSongMenu('" + songMenu.id + "')"
		});
		$songMenuEle.find(".play_addToPlayList .addToPlaylist").attr({
			"onclick" : "hiddenDiv.addFromSongMenu('" + songMenu.id + "')"
		});
		$songMenuEle.find(".buttons .share").html("<i class='icomoon'></i>" + songMenu.shareNum);

		var user = UserManager.getUser();

		if (user && (user.id === songMenu.creatorId)) {// ||
			// user.collectedSongMenus[songMenu.id]
			$songMenuEle.find(".buttons .collection").addClass("disable");
		} else {
			$songMenuEle.find(".buttons .collection").removeClass("disable")
		}
		$songMenuEle.find(".buttons .collection").html("<i class='icomoon'></i>" + songMenu.collectionNum);

		$songMenuEle.find(".buttons .comment").html("<i class='icomoon'></i>" + songMenu.commentNum);
		var $tagsEle = $songMenuEle.find(".details .tags").empty();
		var $tagEle;
		for (var i = 0; i < songMenu.songMenuSecondTagList.length; i++) {
			$tagEle = $(" <a class='tag' href='javascript:void(0);' onclick='jump(this);'></a>");
			$tagEle.attr({
				"data-href" : "songMenus" + songMenu.songMenuSecondTagList[i].id + "}?" + songMenu.songMenuSecondTagList[i].name
			}).text(songMenu.songMenuSecondTagList[i].name);
			$tagsEle.append($tagEle);
		}
		if (songMenu.introduction) {
			$songMenuEle.find(".details .introduction").text("介绍：" + songMenu.introduction);
		}
		$(".songList .songList_detail").text(songMenu.songNum + " 首歌");
		$(".songList .songList_top .num").text(songMenu.playNum);
		$(".commentList .commentList_detail").text("共" + songMenu.commentNum + "条评论");
	}

	var allowExtention = ".jpg,.bmp,.png";
	var extention;
	$("#imageUpload").on("click",function(){
		var $input = $(this).siblings("input");
//		$(this).off().on("click",function(){
//			console.log($input[0].value)
//			var reader = new FileReader();
//			reader.onload = function(e) {
//				$("#main").attr("src", e.target.result);
//			}			
//			reader.readAsDataURL($input[0].files[0]);
//		})
		$input.click();
		console.log($input[0].value)
		//extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
	})
	function previewImage(fileObj, imgPreviewId, divPreviewId) {
		var allowExtention = ".jpg,.bmp,.gif,.png";// 允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
		var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
		var browserVersion = window.navigator.userAgent.toUpperCase();
		if (allowExtention.indexOf(extention) > -1) {
			if (fileObj.files) {// HTML5实现预览，兼容chrome、火狐7+等
				if (window.FileReader) {
					var reader = new FileReader();
					reader.onload = function(e) {
						document.getElementById(imgPreviewId).setAttribute("src", e.target.result);
					}
					reader.readAsDataURL(fileObj.files[0]);
				} else if (browserVersion.indexOf("SAFARI") > -1) {
					alert("不支持Safari6.0以下浏览器的图片预览!");
				}
			} else if (browserVersion.indexOf("MSIE") > -1) {
				if (browserVersion.indexOf("MSIE 6") > -1) {// ie6
					document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
				} else {// ie[7-9]
					fileObj.select();
					if (browserVersion.indexOf("MSIE 9") > -1)
						fileObj.blur();// 不加上document.selection.createRange().text在ie9会拒绝访问
					var newPreview = document.getElementById(divPreviewId + "New");
					if (newPreview == null) {
						newPreview = document.createElement("div");
						newPreview.setAttribute("id", divPreviewId + "New");
						newPreview.style.width = document.getElementById(imgPreviewId).width + "px";
						newPreview.style.height = document.getElementById(imgPreviewId).height + "px";
						newPreview.style.border = "solid 1px #d2e2e2";
					}
					newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"
							+ document.selection.createRange().text + "')";
					var tempDivPreview = document.getElementById(divPreviewId);
					tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
					tempDivPreview.style.display = "none";
				}
			} else if (browserVersion.indexOf("FIREFOX") > -1) {// firefox
				var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
				if (firefoxVersion < 7) {// firefox7以下版本
					document.getElementById(imgPreviewId).setAttribute("src", fileObj.files[0].getAsDataURL());
				} else {// firefox7.0+
					document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(fileObj.files[0]));
				}
			} else {
				document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
			}
		} else {
			alert("仅支持" + allowExtention + "为后缀名的文件!");
			fileObj.value = "";// 清空选中文件
			if (browserVersion.indexOf("MSIE") > -1) {
				fileObj.select();
				document.selection.clear();
			}
			fileObj.outerHTML = fileObj.outerHTML;
		}
	}
}())
