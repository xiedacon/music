(function() {

	$.ajax({
		url : "songMenu/creatorId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		loadSongMenus("div#createdSongMenus",{
			type : "创建的歌单",
			songMenus : process(source)
		});
		loadSongMenu(dom("div#createdSongMenus ul.select").childs()[0].addClass("now").attr("id"));
	});

	$.ajax({
		url : "songMenu/collectorId_" + PageScope.params.id,
		type : "GET",
		dataType : "json"
	}).done(function(source){
		loadSongMenus("div#collectedSongMenus",{
			type : "收藏的歌单",
			songMenus : process(source)
		});
	});

	function loadSongMenus(selector,data){
		var template = `
		{{if songMenus.length > 0}}
		<h2>
			<i></i>
			<span class="content">{{type}}（{{songMenus.length}}）</span>
			{{if type==="创建的歌单"}}
			<span class="h2_rightF" onclick="MMR.get('addMenu').show();">
				<i class="icomoon"></i>新建
			</span>
			{{/if}}
		</h2>
		<ul class="select">
			{{each songMenus as songMenu}}
			<li id="{{songMenu.id}}" class="option">
				<img src="{{songMenu.icon}}">
				<div class="option_right">
					<span class="name">
						{{if songMenu.userId === '` + UserManager.getUserId() + `'}}
						我
						{{else if songMenu.userId}}
						{{songMenu.creatorName}}
						{{/if}}
						{{songMenu.name}}
					</span>
					<br>
					<span class="num">{{songMenu.songNum}}首</span>
				</div>
				{{if songMenu.userId !== '` + UserManager.getUserId() + `'}}
				<div class="option_rightF">
					{{if songMenu.creatorId === '` + UserManager.getUserId() + `'}}
					<i class="edit icomoon" title="修改"></i>
					{{/if}}
					<i class="delete icomoon" title="删除"></i>
				</div>
				{{/if}}
			</li>
			{{/each}}
		</ul>
		{{/if}}
		`;

		document.querySelector(selector).innerHTML = Template.compile(template)(data);

		var eles;
		dom(selector + " ul.select").on("click", function(e) {
			eles = eles ? eles : dom("div#createdSongMenus ul.select").childs().concat(dom("div#collectedSongMenus ul.select").childs());
			switch(e.target.tagName){
				case "LI":
					eles.forEach(function(ele){
						ele.attr("id") === e.target.id ? ele.addClass("now") : ele.removeClass("now");
					});

					loadSongMenu(e.target.id);
					dom("div#showPage").css({display:"block"}).siblings().forEach(function(ele){
						ele.css({display:"none"});
					});
					break;
				case "I":
					if(e.target.className.indexOf("edit")>=0){
						eles.forEach(function(ele){
							ele.removeClass("now");
						});

						loadEditPage(dom(e.target).parent("li").attr("id"));
						dom("div#editPage").css({display:"block"}).siblings().forEach(function(ele){
							ele.css({display:"none"});
						});
					}
					if(e.target.className.indexOf("delete")>=0){
						MMR.deleteSongMenu(this);
					}
					e.stopPropagation();
					break;
				default:
					loadSongMenu(dom(e.target).parent("LI").attr("id"));
			}
		});
	}

	function loadSongMenu(songMenuId) {
		var id = songMenuId;

		$.ajax({
			url : "songMenu/" + id,
			type : "GET",
			dataType : "json"
		}).done(function(source){
			var template = `
			<div class="songMenuMessage_left entityMessage_left">
				<img src="{{songMenu.icon}}">
			</div>
			<div class="songMenuMessage_right entityMessage_right">
				<div class="label">
					<span class="content">歌单</span>
					<span class="triangle"><i></i></span>
				</div>
				<h2>{{songMenu.name}}</h2>
				<div class="creator_time">
					<div class="creator">
						<a title="{{songMenu.creatorName}}" href="#home?id={{songMenu.creatorId}}">
							<img src="{{songMenu.creatorIcon}}" />
						</a>
						<a class="creatorName" href="#home?id={{songMenu.creatorId}}" title="{{songMenu.creatorName}}">
							{{songMenu.creatorName}}
						</a>
					</div>
					<span class="createTime">
						{{songMenu.createTime | dateFormatter:'yyyy-MM-dd'}}创建
					</span>
				</div>
				<div class="buttons">
					<div class="play_addToPlayList">
						<span class="play" title="播放" onclick="MMR.get('music').batchAddThenPlay('songMenuId','{{songMenu.id}}')">
							<i class="icomoon"></i>
							<span>播放</span>
						</span>
						<span class="addToPlaylist icomoon" title="添加到播放列表" onclick="MMR.get('music').batchAdd('songMenuId','{{songMenu.id}}')"></span>
					</div>
					<span class="collection button" onclick="MMR.collectSongMenu({{songMenu.id}})">
						<i class='icomoon'></i>{{songMenu.collectionNum}}
					</span>
					<span class="share button">
						<i class='icomoon'></i>{{songMenu.shareNum}}
					</span>
					<span class="download button"><i class="icomoon"></i>下载</span>
					<span class="comment button">
						<i class='icomoon'></i>{{songMenu.commentNum}}
					</span>
				</div>
				<div class="details">
					<p class="tags">
						标签：
						{{each songMenu.songMenuSecondTagList as secondTag}}
						<a class='tag' href='#songMenus?secondTagId={{secondTag.id}}&secondTagName={{secondTag.name}}'>
							{{secondTag.name}}
						</a>
						{{/each}}
					</p>
					<p class="introduction">
						介绍：
						{{if songMenu.introduction}}
						{{songMenu.introduction}}
						{{else}}
						无
						{{/if}}
					</p>
				</div>
			</div>
			`, //
			data = {
				songMenu : process(source)
			};

			document.querySelector("div#songMenu").innerHTML = Template.compile(template)(data);
			document.querySelector("span#songNum").innerHTML = data.songMenu.songNum + "首歌";
			document.querySelector("i#playNum").innerHTML = data.songMenu.playNum;
			document.querySelector("span#commentNum").innerHTML = "共" + data.songMenu.commentNum + "条评论";
			var user = UserManager.getUser();
			if (user) {
				document.querySelector("img#userIcon").src = user.icon;
			}
			document.querySelector("span#addComment").addEventListener("click",function(){
				MMR.addComment('songMenu', data.songMenu.id);
			})
		});

		$.ajax({
			url : "song/songMenuId_" + id,
			type : "GET",
			dataType : "json"
		}).done(function(source){
			var data = {
				songs : process(source)
			}
			FUNCTION.loadSongs("tbody#songList", data);
		});

		$.ajax({
			url : "comment/songMenuId_" + id + "/1",
			type : "GET",
			dataType : "json"
		}).done(function(source){
			Excutor({
				source : source,
				data : {
					urlPrefix : "comment/songMenuId_" + id + "/"
				},
				before : function(source, data, excutor){
					source = process(source);
					data.hotList = source.hotList;
					data.pageBean = source.pageBean;
				},
				excute : function(source, data, excutor){
					FUNCTION.loadComments("ul#commentList", data);
					FUNCTION.loadPages("ul#pages", data, excutor);
					FUNCTION.loadEmojis();
				}
			}).excute();
		});
	}

	function loadEditPage(songMenuId){
		$.ajax({
			url : "songMenu/" + songMenuId,
			dataType : "json",
			type : "GET"
		}).done(function(source){
			var template = `
			<div class="editPage_top">
				<a href="#songMenu?id={{songMenu.id}}" class="name">{{songMenu.name}}</a>
				<span>></span>
				<strong>编辑歌单</strong>
			</div>
			<div class="editPage_bottom">
				<div class="bottom_left">
					<p>
						<input id="id" type="text" value="{{songMenu.id}}">
					</p>
					<p>
						<span class="key">歌单名：</span>
						<span class="value">
							<input id="songMenuName" name="name" type="text" value="{{songMenu.name}}">
							<span class="errorMessage" style="visibility: hidden;">
								<i class="icomoon"></i>
							</span>
						</span>
					</p>
					<p>
						<span class="key">标签：</span>
						<span class="value">
							<span id="tags" class="tags">
								{{each songMenu.songMenuSecondTagList as tag}}
								<span id="{{tag.id}}" class="tag">
									{{tag.name}}
									<i class='icomoon'></i>
								</span>
								{{/each}}
								<a>选择标签</a>
							</span>
							<span class="message">选择适合的标签，最多选3个</span>
						</span>
					</p>
					<p>
						<span class="key">介绍：</span>
						<span class="value">
							{{if songMenu.introduction}}
							<textarea class="introduction">{{songMenu.introduction}}</textarea>
							<span class="num">
								{{1000 - songMenu.introduction.length}}
							</span>
							{{else}}
							<textarea class="introduction"></textarea>
							<span class="num">1000</span>
							{{/if}}
						</span>
					</p>
					<p>
						<span class="key"></span>
						<span class="value">
							<span id="updateSongMenu" class="button enable" style="margin: 0 30px 0 0;">保 存</span>
							<span class="button">取 消</span>
						</span>
					</p>
				</div>
				<div class="bottom_right">
					<img alt="{{songMenu.name}}" src="{{songMenu.icon}}">
					<span class="toImageEditPage">编辑封面</span>
				</div>
			</div>
			`, //
			data = {
				songMenu : process(source)
			};

			dom("div#editPage").innerHTML(Template.compile(template)(data));
			dom("span#tags").on("click",function(e){
				var ele = dom(e.target);
				((ele.type === "i") && (ele.parent().remove()) //
				 || (ele.type === "a") && (MMR.get('tags').show()));
			});

			var textarea = dom("textarea.introduction"), //
			num = textarea.sibling("span.num"), //
			i, //
			toImageEditPage = dom("span.toImageEditPage");
			textarea.on("focusin",function(){
				i = setInterval(function(){num.text(1000 - textarea.val().length)},500);
			}).on("focusout",function(){clearInterval(i);});

			template = `
			<div class="editPage_top">
				<a href="#songMenu?id={{songMenu.id}}" class="name">{{songMenu.name}}</a>
				<span>></span>
				<a class="turnToEditPage">编辑歌单</a>
				<span>></span>
				<strong>编辑封面</strong>
			</div>
			<div class="editPage_bottom">
				<div class="bottom_top">
					<form id="image_form">
						<a id="imageUpload" class="button" type="button">上传封面</a>
						<input name="file" type="file" style="visibility: hidden;width: 0px;height: 0px">
					</form>
					<span>支持jpg、png、bmp格式的图片，且文件小于20M</span>
				</div>
				<div class="bottom_middle">
					<div class="main">
						<img id="main" alt="" src="{{songMenu.icon || 'image/default_cover.png'}}">
					</div>
					<div class="desc">
						<span class="desc_head">歌单封面预览</span><br>
						<div id="max" class="show_max">
							<img id="image_max" alt="" src="{{songMenu.icon || 'image/default_cover.png'}}">
						</div>
						<span>大尺寸封面（180x180像素）</span>
						<br>
						<div id="min" class="show_min">
							<img id="image_min" alt="" src="{{songMenu.icon || 'image/default_cover.png'}}">
						</div>
						<span>小尺寸封面（40x40像素）</span>
					</div>
				</div>
				<div class="bottom_bottom">
					<a id="image_update" class="button disable" type="button" style="background: #4c61fd;color: #fff;">保存</a>
					<a id="image_quit" class="button" type="button">取消</a>
				</div>
			</div>
			`;

			dom("div#imageEditPage").innerHTML(Template.compile(template)(data));
			dom("div#imageEditPage").on("click",function(e){
				var tag = dom(e.target);
				if(tag.matches("a#image_quit") || tag.matches("a.turnToEditPage")){
					loadEditPage(data.songMenu.id);
					dom("div#editPage").css({display:"block"}).siblings().forEach(function(ele){
						ele.css({display:"none"});
					});
				}else if(tag.matches("a#imageUpload")){
					dom("input[name=file]").trigger("click");
				}
			});
			dom("input[name=file]").on("change",function(){
				var imageUpload = dom("a#imageUpload").text("加载中..");
				dom("img#main").once("load", function() {
					$("#image_max").attr("src", $(this).attr("src"));
					$("#image_min").attr("src", $(this).attr("src"));
					// if ($(this).attr("style")) {
					// 	$("#main").siblings(".jcrop-holder").find("img").attr("src", $(this).attr("src"));
					// } else {
					// 	chooseImage();
					// }
					imageUpload.text("上传封面");
				})
				previewImage($(this)[0], "main");
			});
			dom("a#image_update").on("click",function(){
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
			});

			toImageEditPage.on("click",function(){
				dom("div#imageEditPage").css({display:"block"}).siblings().forEach(function(ele){
					ele.css({display:"none"});
				});
			});

			dom("span#updateSongMenu").on("click",function(){
				var id = dom("input#id").val(), //
				name = dom("input#songMenuName").val(), //
				introduction = textarea.val(), //
				tagsEle = dom("span#tags").childs("span"), //
				tags = "";

				if(name.trim() === "") return MMR.get("simpleMsg").showError("歌单名不能为空");

				tagsEle.forEach(function(e){tags += e.attr("id") + "-";});
				tags = tags.substring(0,tags.length);

				$.ajax({
					url : "songMenu/" + id,
					type : "PUT",
					data : {"name":name,"introduction":introduction,"tags":tags},
					dataType : "json",
				}).done(function(source){
					if(process(source)){
						MMR.get("simpleMsg").showSuccess("修改成功");

						loadSongMenu(id);
						dom("div#createdSongMenus ul.select").childs().forEach(function(ele){
							ele.attr("id") === id ? ele.addClass("now") : ele.removeClass("now");
						});
						dom("div#showPage").css({display:"block"}).siblings().forEach(function(ele){
							ele.css({display:"none"});
						});
					}
				});
			});
		});
	}

	var allowExtention = ".jpg,.bmp,.png";
	var extention;
	var $input;

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
