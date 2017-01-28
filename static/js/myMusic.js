(function() {
	AJAX({
		url : "songMenu/creatorId_" + PageScope.params.userId,
		success : loadCreatedSongMenus
	});
	AJAX({
		url : "songMenu/collectorId_" + PageScope.params.userId,
		success : loadCollectedSongMenus
	});

	function loadSongMenu(songMenuId) {
		var $songMenus = $(".material_right li").not(".prototype");
		$songMenus.filter("li[data-id='" + songMenuId + "']").addClass("now") //
		$songMenus.filter("li[data-id!='" + songMenuId + "']").removeClass("now");

		PageScope.loadForFirst = "comment/songMenuId_" + songMenuId;
		PageScope.loadPageBean = "comment/songMenuId_" + songMenuId + "/";

		$("#showPage").css("display", "block").siblings().css("display", "none");

		AJAX({
			url : "songMenu/" + songMenuId,
			success : _loadSongMenu
		});
		AJAX({
			url : "song/songMenuId_" + songMenuId,
			success : FUNCTION.loadSongs
		});
		AJAX({
			url : PageScope.loadForFirst,
			success : FUNCTION.loadForFirst
		});
	}

	function loadCreatedSongMenus(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var data = data.data //
		, $ele = $("#createdSongMenus");
		if (data.length > 0) {
			$ele.siblings("#createdSongMenusHead").find(".content").text("创建的歌单(" + data.length + ")");
			loadSongMenus($ele, data);
		} else {
			$ele.css("display", "none");
			$ele.siblings("#createdSongMenusHead").css("display", "none");
		}
	}

	function loadCollectedSongMenus(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var data = data.data //
		, $ele = $("#collectedSongMenus");
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
			success : function(data) {
				if(data.code != 200){
					MMR.get("simpleMsg").showError(data.error.value);
					return;
				}
				
				var songMenu = data.data //
				;
				songMenu_globe = songMenu;
				$editPage.attr("data-id", songMenu.id);
				$editPage.find(".name").attr({
					"data-href" : "songMenu?songMenuId=" + songMenu.id
				}).text(songMenu.name);
				$editPage.find("input").val(songMenu.name);
				$editPage.find(".introduction").text("");
				$editPage.find(".num").text(1000);
				if (songMenu.introduction) {
					$editPage.find(".introduction").text(songMenu.introduction);
					$editPage.find(".num").text(1000 - songMenu.introduction.length);
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
				$editPage.find(".introduction").on("focusin", function() {
					$editPage.find(".introduction").on("keydown", function() {
						$(this).siblings(".num").text(1000 - $(this).val().length)
					});
				});
				$editPage.find(".introduction").on("focusout", function() {
					$editPage.find(".introduction").off("keydown");
				});

				$editPage.css("display", "block").siblings().css("display", "none");
			}
		})

		$editPage.find("#updateSongMenu").off().on("click", updateSongMenu);
		function updateSongMenu() {
			var name = $editPage.find("#songMenuName").val();
			var introduction = $editPage.find(".introduction").val();
			var $tags = $editPage.find("#tags .tag");

			if (!name || name == "") {
				return alert("歌单名不能为空");
			}

			var $tag;
			var tags = "";
			for (var i = 0; i < $tags.length; i++) {
				$tag = $tags.eq(i);
				tags += $tag.attr("data-id");
				if (i < $tags.length - 1) {
					tags += "-";
				}
			}
			
			$.ajax({
				url : "songMenu/" + $("#editPage").attr("data-id"),
				type : "PUT",
				data : {"name":name,"introduction":introduction,"tags":tags},
				dataType : "json",
				success : function(data) {
					if (data.code === 200) {
						router.startRouter("myMusic?userId=" + PageScope.params.userId, true);
					} else {
						MMR.get("simpleMsg").setData({
							status : "error",
							content : "编辑失败"
						}).show();
					}
				}
			})
		}
		var songMenu_globe;
		function check() {
			var name = $editPage.find("#songMenuName").val();
			var introduction = $editPage.find(".introduction").text();
			var $tags = $editPage.find("#tags");
			var $updateSongMenu = $("#updateSongMenu");

			if (songMenu_globe.name != name) {
				return $updateSongMenu.removeClass("disable").addClass("enable");
			} else if (songMenu_globe.introduction != introduction) {
				return $updateSongMenu.removeClass("disable").addClass("enable");
			} else {
				for (var i = 0; i < $tags.length; i++) {
					var tagId = $tags.eq(i).attr("data-id");
					for (var j = 0; j < songMenu_globe.songMenuSecondTagList.length; j++) {
						if (tagId === songMenu_globe.songMenuSecondTagList[j]) {
							return $updateSongMenu.removeClass("disable").addClass("enable");
						}
					}
				}
				return $updateSongMenu.removeClass("enable").addClass("disable");
			}
		}
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
		$imageEditPage.find("#image_quit").on("click", function() {
			$("#editPage").css("display", "block").siblings().css("display", "none");
		});

		var $image_update = $imageEditPage.find("#image_update").addClass("disable");
		$("#main").removeData("Jcrop").removeAttr("style").attr("src", "image/default_cover.png") //
		.siblings(".jcrop-holder").remove();
		$("#main").off().on("load", function() {
			if ($(this).attr("src") != "image/default_cover.png") {
				$imageEditPage.find("#image_update").removeClass("disable");
			}
		})
		$("#image_max,#image_min").attr("src", "image/default_cover.png");
		$("#imageUpload").siblings("input[name='file']").remove();
		$("#imageUpload").parent().append("<input name='file' type='file' style='visibility: hidden;width: 0px;height: 0px'>");
		$imageEditPage.css("display", "block").siblings().css("display", "none");
		position = undefined;
	}

	function _loadSongMenu(data) {
		if(data.code != 200){
			MMR.get("simpleMsg").showError(data.error.value);
			return;
		}
		
		var songMenu = data.data //
		, $songMenuEle = $("#songMenu");
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
	var $input;
	$("#imageUpload").on("click", function() {
		$input = $(this).siblings("input");
		$input.click();
		$input.one("change", function() {
			$("#imageUpload").text("加载中..");
			$("#main").one("load", function() {
				$("#image_max").attr("src", $(this).attr("src"));
				$("#image_min").attr("src", $(this).attr("src"));
				if ($(this).attr("style")) {
					$("#main").siblings(".jcrop-holder").find("img").attr("src", $(this).attr("src"));
				} else {
					chooseImage();
				}
				$("#imageUpload").text("上传封面");
			})
			previewImage($(this)[0], "main");
		});
	})
	$("#image_update").on("click", function() {
		if (position) {
			var formData = new FormData();
			formData.append("x1", position.x);
			formData.append("y1", position.y);
			formData.append("x2", position.x2);
			formData.append("y2", position.y2);
			formData.append("width", 320);
			formData.append("height", 320);
			formData.append("image", $("#main").attr("src"));

			$(this).text("保存中..");

			$.ajax({
				url : "songMenu/" + $("#editPage").attr("data-id") + "/icon",
				type : "PUT",
				processData : false,
				contentType : false,
				context : this,
				data : formData,
				dataType : "json",
				success : function(data) {
					$(this).text("保存");

					if (data.code === 200) {
						router.startRouter("myMusic?userId=" + PageScope.params.userId, true);
					} else {
						MMR.get("simpleMsg").setData({
							status : "error",
							content : "上传失败"
						}).show();
					}
				}
			})
		}

	})
	var position;
	var jcrop_api, boundx, boundy;
	function chooseImage() {
		var $pcnt1 = $('#max');
		var $pimg1 = $('#max img');
		var xsize1 = $pcnt1.width();
		var ysize1 = $pcnt1.height();
		var $pcnt2 = $('#min');
		var $pimg2 = $('#min img');
		var xsize2 = $pcnt2.width();
		var ysize2 = $pcnt2.height();
		var $image = $('#main');
		initJcrop();
		function initJcrop() {
			$image.Jcrop({
				onChange : updatePreview,
				onSelect : updatePreview,
				aspectRatio : xsize1 / ysize1
			}, function() {
				jcrop_api = this;
				jcrop_api.animateTo([60, 60, 260, 260]);
				jcrop_api.setOptions({
					allowSelect : !!this.checked,
					aspectRatio : 1,
					minSize : [80, 80],
					maxSize : [350, 350],
					boxWidth : $image.width(),
					boxHeight : $image.height()
				});

				var bounds = this.getBounds();
				boundx = bounds[0];
				boundy = bounds[1];
			});
		};
		function updatePreview(c) {
			position = c;
			if (parseInt(c.w) > 0) {
				var rx1 = xsize1 / c.w;
				var ry1 = ysize1 / c.h;

				$pimg1.css({
					width : Math.round(rx1 * boundx) + 'px',
					height : Math.round(ry1 * boundy) + 'px',
					marginLeft : '-' + Math.round(rx1 * c.x) + 'px',
					marginTop : '-' + Math.round(ry1 * c.y) + 'px'
				});

				var rx2 = xsize2 / c.w;
				var ry2 = ysize2 / c.h;

				$pimg2.css({
					width : Math.round(rx2 * boundx) + 'px',
					height : Math.round(ry2 * boundy) + 'px',
					marginLeft : '-' + Math.round(rx2 * c.x) + 'px',
					marginTop : '-' + Math.round(ry2 * c.y) + 'px'
				});
			}
		};
	}
	function previewImage(fileObj, imgPreviewId) {
		var divPreviewId = imgPreviewId;

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
	function compress(img) {
		var initSize = img.src.length;
		var width = img.width;
		var height = img.height;

		// 如果图片大于四百万像素，计算压缩比并将大小压至400万以下
		var ratio;
		if ((ratio = width * height / 4000000) > 1) {
			ratio = Math.sqrt(ratio);
			width /= ratio;
			height /= ratio;
		} else {
			ratio = 1;
		}

		canvas.width = width;
		canvas.height = height;

		// 铺底色
		ctx.fillStyle = "#fff";
		ctx.fillRect(0, 0, canvas.width, canvas.height);

		// 如果图片像素大于100万则使用瓦片绘制
		var count;
		if ((count = width * height / 1000000) > 1) {
			count = ~~(Math.sqrt(count) + 1); // 计算要分成多少块瓦片

			// 计算每块瓦片的宽和高
			var nw = ~~(width / count);
			var nh = ~~(height / count);

			tCanvas.width = nw;
			tCanvas.height = nh;

			for (var i = 0; i < count; i++) {
				for (var j = 0; j < count; j++) {
					tctx.drawImage(img, i * nw * ratio, j * nh * ratio, nw * ratio, nh * ratio, 0, 0, nw, nh);

					ctx.drawImage(tCanvas, i * nw, j * nh, nw, nh);
				}
			}
		} else {
			ctx.drawImage(img, 0, 0, width, height);
		}

		// 进行最小压缩
		var ndata = canvas.toDataURL('image/jpeg', 0.1);

		console.log('压缩前：' + initSize);
		console.log('压缩后：' + ndata.length);
		console.log('压缩率：' + ~~(100 * (initSize - ndata.length) / initSize) + "%");

		tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;

		return ndata;
	}
}())
