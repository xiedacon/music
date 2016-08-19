(function() {

	window.hiddenDiv = new function() {
		var $hiddenDiv = $("#hiddenDiv");

		var loginDivModule = new loginDiv();
		var loginByPhoneDivModule = new loginByPhoneDiv();
		var registByPhoneDivModule = new registByPhoneDiv();
		var checkCaptchaDivModule = new checkCaptchaDiv();
		var msgDivModule = new msgDiv();
		var collectionDivModule = new collectionDiv();
		var addMenuDivModule = new addMenuDiv();
		var musicDivModule = new musicDiv();
		var usernameDivModule = new usernameDiv();
		var simpleMsgDivModule = new simpleMsgDiv();

		this.init = function() {
			loginDivModule.init();
			loginByPhoneDivModule.init();
			registByPhoneDivModule.init();
			checkCaptchaDivModule.init();
			msgDivModule.init();
			collectionDivModule.init();
			addMenuDivModule.init();
			musicDivModule.init();
			usernameDivModule.init();
			$(window).keydown(function(e) {
				if (e.keyCode == 27) {
					_hiddenAll();
				}
			});
		}
		this.collection = function() {
			collectionDivModule.show();
		}
		this.play = function(songId) {
			musicDivModule.play(songId);
		}
		this.addToPlayList = function(songId) {
			musicDivModule.playListDivModule.add(songId);
		}
		this.addToPlayListThenPlay = function(songId) {
			musicDivModule.playListDivModule.add(songId);
			musicDivModule.play(songId);
		}
		this.setSong = musicDivModule.setSong;
		this.addFromAlbum = addFromAlbum;
		this.playFromAlbum = function(albumId) {
			addFromAlbum(albumId, function(songs) {
				musicDivModule.play(songs[0].id);
			})
		}
		function addFromAlbum(albumId, callback) {
			$.ajax({
				url : "album/" + albumId + "/songList",
				type : "GET",
				dataType : "json",
				success : function(songs) {
					if (!songs || songs.length === 0) {
						return;
					}
					var song;
					for (var i = 0; i < songs.length; i++) {
						song = songs[i];
						musicDivModule.setSong(song.id, song);
						musicDivModule.playListDivModule.add(song.id);
					}
					if (callback) {
						callback(songs);
					}
				},
				error : function(e) {
					console.log(e);
				}
			})
		}
		this.addFromSongMenu = addFromSongMenu;
		this.playFromSongMenu = function(songMenuId) {
			addFromSongMenu(songMenuId, function(songs) {
				musicDivModule.play(songs[0].id);
			})
		}
		function addFromSongMenu(songMenuId, callback) {
			$.ajax({
				url : "songMenu/" + songMenuId + "/songList",
				type : "GET",
				dataType : "json",
				success : function(songs) {
					if (!songs || songs.length === 0) {
						return;
					}
					var song;
					for (var i = 0; i < songs.length; i++) {
						song = songs[i];
						musicDivModule.setSong(song.id, song);
						musicDivModule.playListDivModule.add(song.id);
					}
					if (callback) {
						callback(songs);
					}
				},
				error : function(e) {
					console.log(e);
				}
			})
		}
		this.addFromSongList = addFromSongList;
		this.playFromSongList = function(songListId) {
			addFromSongList(songListId, function(songs) {
				musicDivModule.play(songs[0].id);
			})
		}
		function addFromSongList(songListId, callback) {
			$.ajax({
				url : "songList/" + songListId + "/songs",
				type : "GET",
				dataType : "json",
				success : function(songs) {
					if (!songs || songs.length === 0) {
						return;
					}
					var song;
					for (var i = 0; i < songs.length; i++) {
						song = songs[i];
						musicDivModule.setSong(song.id, song);
						musicDivModule.playListDivModule.add(song.id);
					}
					if (callback) {
						callback(songs);
					}
				},
				error : function(e) {
					console.log(e);
				}
			})
		}
		this.addFromSinger = addFromSinger;
		this.playFromSinger = function(singerId) {
			addFromSinger(singerId, function(songs) {
				musicDivModule.play(songs[0].id);
			})
		}
		function addFromSinger(singerId, callback) {
			$.ajax({
				url : "singer/" + singerId + "/songs",
				type : "GET",
				dataType : "json",
				success : function(songs) {
					if (!songs || songs.length === 0) {
						return;
					}
					var song;
					for (var i = 0; i < songs.length; i++) {
						song = songs[i];
						musicDivModule.setSong(song.id, song);
						musicDivModule.playListDivModule.add(song.id);
					}
					if (callback) {
						callback(songs);
					}
				},
				error : function(e) {
					console.log(e);
				}
			})
		}

		this.hiddenAll = _hiddenAll;

		this.show = function() {
			var height = $("body").css('height');
			var width = $("body").css('width');
			$hiddenDiv.css({
				'height' : height,
				'width' : width,
				'visibility' : 'visible'
			});
			$("body").attr("onmousewheel", "return false;");
		}

		this.hidden = function() {
			$hiddenDiv.css({
				'visibility' : 'hidden'
			});
			$("body").removeAttr("onmousewheel");
		}

		this.showDiv = function(name) {
			var div = hiddenDiv.getDiv(name);
			div.show();
			return div;
		}

		this.getDiv = function(name) {
			switch (name) {
				case "loginDiv" :
					return loginDivModule;
				case "loginByPhoneDiv" :
					return loginByPhoneDivModule;
				case "registByPhoneDiv" :
					return registByPhoneDivModule;
				case "checkCaptchaDiv" :
					return checkCaptchaDivModule;
				case "msgDiv" :
					return msgDivModule;
				case "collectionDiv" :
					return collectionDivModule;
				case "addMenuDiv" :
					return addMenuDivModule;
				case "musicDiv" :
					return musicDivModule;
				case "usernameDiv" :
					return usernameDivModule;
				case "simpleMsgDiv" :
					return simpleMsgDivModule;
				default :
					return null;
			}
		}

		this.addSongMenu = function addSongMenu(data) {
			var name = data.name;
			// 校验
			if (name && name.length == 0) {
				data.error("歌单名不能为空");
			}
			// 发送
			// 返回
		}

		this.collect = collect;
		this.addToSongMenu = addToSongMenu;
		function addToSongMenu(songMenuId, type, id) {
			$.ajax({
				url : "songMenu/" + songMenuId +"/song",
				type : "POST",
				data : {
					"type" : type,
					"id" : id
				},
				dataType : "json",
				success : function() {
					hiddenDiv.getDiv("simpleMsgDiv").setData({"state":"info","content":"操作成功"});
				},
				error : function() {
					hiddenDiv.getDiv("simpleMsgDiv").setData({"state":"error","content":"操作失败"});
				}
			})
		}
		function collect(type, id) {
			var user = userDiv.getUser();
			if (user) {
				$.ajax({
					url : "user/" + user.id + "/createdSongMenu",
					dataType : "json",
					type : "GET",
					success : function(data) {
						var $songMenus = $collectionDiv.find(".login_material");
						var $prototype = $songMenus.find(".prototype").clone().removeClass("prototype");
						var $songMenu, songMenu;

						for (var i = 0; i < data.length; i++) {
							songMenu = data[i];
							$songMenu = $prototype.clone();

							$songMenu.find("img").attr({
								"src" : songMenu.icon,
								"onclick" : "hiddenDiv.addToSongMenu(" + songMenu.id + "," + type + "," + id + ");"
							});
							$songMenu.find(".name").text(songMenu.name);
							$songMenu.find(".num").text(songMenu.songNum + "首");
						}

						hiddenDiv.showDiv("collectionDiv");
					},
					error : function(e) {
						console.error(e);
					}
				})
			} else {
				hiddenDiv.showDiv("loginDiv");
			}
		}

		this.login = function login(ele) {
			var phone = $(ele).parent().find('input[name=phone]').val();
			var password = $(ele).parent().find('input[name=password]').val();
			var login_auto = $(ele).parent().find('input[name=login_auto]').prop("checked");

			var $loginByPhoneDiv = $("#loginByPhoneDiv");

			// 检验/发送
			post({
				"eleName" : "loginByPhoneDiv",
				"phone" : phone,
				"before" : function() {
					$loginByPhoneDiv.find(".errorMessage").css('display', 'none');
					$loginByPhoneDiv.find(".login").text("登录中...");
				},
				"password" : password,
				"url" : "user/login_phone",
				"data" : "phone=" + phone + "&&password=" + b64_md5(password),// 加密
				"success" : function(data) {
					// 返回结果
					if (data.code == 200) {
						sessionStorage.setItem("user", JSON.stringify(data.user));

						// 自动登录选项相关
						if (login_auto) {
							localStorage.setItem("login_auto", true);
							localStorage.setItem("user", JSON.stringify(data.user));
						} else {
							localStorage.setItem("login_auto", false);
							localStorage.removeItem("user");
						}

						userDiv.login();
						hiddenDiv.hiddenAll();
						hiddenDiv.showDiv("simpleMsgDiv").setData({
							"status" : "success",
							"content" : "登陆成功"
						})
					} else {
						var error = data.error;
						msg('loginByPhoneDiv', error.name, error.value);
						$loginByPhoneDiv.find(".login").text("下一步");
					}
				}
			});
		}

		function post(data) {
			// 校验
			if (data.phone && data.phone.match(/1[3|4|5|8]\d{9}/g)) {
				if (data.password && data.password.match(/.{5,15}/g)) {
					if (data.before) {
						data.before();
					}
					$.ajax({
						url : data.url,
						data : data.data,
						type : "post",
						dataType : "json",
						success : function(_data) {
							data.success(_data);
						},
						error : function(e) {
							console.log(e);
						}
					})
				} else {
					msg(data.eleName, 'password', '密码格式错误');
				}
			} else {
				msg(data.eleName, 'phone', '手机号格式错误');
			}
		}

		this.regist = function regist(ele) {
			var phone = $(ele).parent().find('input[name=phone]').val();
			var password = $(ele).parent().find('input[name=password]').val();

			var $registByPhoneDiv = $("#registByPhoneDiv");
			// 发送
			post({
				"eleName" : "registByPhoneDiv",
				"phone" : phone,
				"before" : function() {
					$registByPhoneDiv.find(".errorMessage").css('display', 'none');
					$registByPhoneDiv.find(".login").text("正在发送...");
				},
				"password" : password,
				"url" : "user/regist",
				"data" : "phone=" + phone + "&&password=" + password,
				"success" : function(data) {
					// 返回结果
					if (data.code == 200) {
						sessionStorage.setItem("user", JSON.stringify(data.user));
						sessionStorage.setItem("user_login_flag", false);

						hiddenDiv.showDiv("checkCaptchaDiv");
					} else if (data.code == 302) {
						sessionStorage.setItem("user", JSON.stringify(data.user));

						hiddenDiv.showDiv("msgDiv").setData({
							"title" : $registByPhoneDiv.find(".login_head span").text(),
							"content" : "该手机号已与云音乐帐号<span> " + data.user.name + " </span>绑定，<br />以后你可以直接用该手机号+密码登录",
							"callback" : function() {
								userDiv.login();
							}
						});
					} else {
						var error = data.error;
						msg('registByPhoneDiv', error.name, error.value);
					}
				}
			});
		}

		this.send = function send(ele) {
			var $checkCaptchaDiv = $("#checkCaptchaDiv");
			var captcha = $(ele).parent().find('input[name=captcha]').val();

			// 校验
			if (!captcha || captcha.length != 4) {
				return msg('checkCaptchaDiv', 'captcha', '验证码错误');
			}

			$checkCaptchaDiv.find(".errorMessage").css('display', 'none');
			$checkCaptchaDiv.find(".login").text("正在验证...");

			// 加密
			// 发送
			// 返回结果

			$("#checkCaptchaDiv .login").text("下一步");
			hiddenDiv.showDiv("usernameDiv");
		}

		this.setUsername = function setUsername(ele) {
			var username = $(ele).parent().find('input[name=username]').val();

			// 校验
			if (!username || username.replace(/\d/g, "").length < 4) {
				return msg('usernameDiv', 'username', '昵称格式错误');;
			}

			var $usernameDiv = $("#usernameDiv");
			$usernameDiv.find(".errorMessage").css('display', 'none');
			$usernameDiv.find(".login").text("设置中...");
			// 发送
			$.ajax({
				url : "user/" + userDiv.getUser().id + "/changeUsername",
				data : "id=" + userDiv.getUser().id + "&&username=" + username,
				dataType : "json",
				type : "POST",
				success : function(data) {
					// 返回结果
					if (data.code == 200) {
						sessionStorage.setItem("user", JSON.stringify(data.user));

						hiddenDiv.showDiv("msgDiv").setData({
							"title" : $usernameDiv.find(".login_head span").text(),
							"content" : "恭喜，设置成功！",
							"callback" : function() {
								userDiv.login();
							}
						});
					} else {
						var error = data.error;
						msg('usernameDiv', error.name, error.value);
					}
				},
				error : function(e) {
					console.log(e);
				}
			});

		}
		function msg(eleName, name, e) {
			$("#" + eleName + " .errorMessage").css('display', 'block').html("<i></i>" + e);
			$("#" + eleName + " input[name=" + name + "]").focus();
		}

		function _hiddenAll() {
			loginDivModule.hidden();
			loginByPhoneDivModule.hidden();
			registByPhoneDivModule.hidden();
			checkCaptchaDivModule.hidden();
			msgDivModule.hidden();
			collectionDivModule.hidden();
			addMenuDivModule.hidden();
			usernameDivModule.hidden();
		}
	}

	function addMenuDiv() {
		var $addMenuDiv = $("#addMenuDiv");

		this.init = function() {
			$addMenuDiv.find(".exit").click(function() {
				hiddenAddMenuDiv();
			})
			$addMenuDiv.find(".login").click(function() {
				hiddenDiv.addSongMenu(this);
			})
			$addMenuDiv.find(".regist").click(function() {
				hiddenAddMenuDiv();
			})
		}
		this.show = function() {
			$addMenuDiv.removeAttr('style');
			$addMenuDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$addMenuDiv.find(".name").val("");
			$addMenuDiv.find(".errorMessage").css('display', 'none');
			$addMenuDiv.find(".name").focus();
			hiddenDiv.show();
		}
		this.hidden = hiddenAddMenuDiv;

		function hiddenAddMenuDiv() {
			$addMenuDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
	}

	function collectionDiv() {
		var $collectionDiv = $("#collectionDiv");

		this.init = function() {
			$collectionDiv.find(".exit").click(function() {
				hiddenCollectionDiv();
			})
			$collectionDiv.find(".newMenu").click(function() {
				hiddenDiv.showDiv("addMenuDiv");
			})
		}
		this.show = function() {
			$collectionDiv.removeAttr('style');
			$collectionDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			hiddenDiv.show();

		}
		this.hidden = hiddenCollectionDiv;

		function hiddenCollectionDiv() {
			$collectionDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
	}

	function loginDiv() {
		var $loginDiv = $("#loginDiv");

		this.show = function() {
			$loginDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$loginDiv.removeAttr('style');
			hiddenDiv.show();
		}
		this.hidden = function() {
			$loginDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
		this.init = function() {
			$loginDiv.find(".exit").click(function(event) {
				hiddenDiv.hiddenAll();
			});
			$loginDiv.find("#toLoginByPhone").click(function(event) {
				hiddenDiv.showDiv("loginByPhoneDiv");
			});
			$loginDiv.find("#toRegistByPhone").click(function(event) {
				hiddenDiv.showDiv("registByPhoneDiv");
			});
		}
	}

	function loginByPhoneDiv() {
		var $loginByPhoneDiv = $("#loginByPhoneDiv");

		this.show = function() {
			$loginByPhoneDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$loginByPhoneDiv.removeAttr('style');
			$loginByPhoneDiv.find("input[name=phone]").val("");
			$loginByPhoneDiv.find("input[name=password]").val("");
			$loginByPhoneDiv.find(".errorMessage").css('display', 'none');
			$loginByPhoneDiv.find("input[name=phone]").focus();
			$loginByPhoneDiv.find(".login").text("登 录");
			hiddenDiv.show();
		}
		this.hidden = function() {
			$loginByPhoneDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
		this.init = function() {
			$loginByPhoneDiv.find(".exit").click(function(event) {
				hiddenDiv.hiddenAll();
			});
			$loginByPhoneDiv.find(".otherway").click(function(event) {
				hiddenDiv.showDiv("loginDiv");
			});
			$loginByPhoneDiv.find(".toRegistByPhone").click(function(event) {
				hiddenDiv.showDiv("registByPhoneDiv");
			});
			$loginByPhoneDiv.find(".login").click(function(event) {
				hiddenDiv.login(this);
			});
			$loginByPhoneDiv.find("input[name=phone]").change(function(event) {
				$loginByPhoneDiv.find(".errorMessage").css('display', 'none');
			});
			$loginByPhoneDiv.find("input[name=phone]").keypress(function(e) {
				if (e.keyCode == 13) {
					$loginByPhoneDiv.find("input[name=password]").focus();
				}
			});
			$loginByPhoneDiv.find("input[name=password]").keypress(function(e) {
				if (e.keyCode == 13) {
					hiddenDiv.login(this);
				}
			});
		}
	}

	function registByPhoneDiv() {
		var $registByPhoneDiv = $("#registByPhoneDiv");

		this.show = function() {
			$registByPhoneDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$registByPhoneDiv.removeAttr('style');
			$registByPhoneDiv.find("input[name=phone]").val("");
			$registByPhoneDiv.find("input[name=password]").val("");
			$registByPhoneDiv.find(".errorMessage").css('display', 'none');
			$registByPhoneDiv.find(".login").text("下一步");
			$registByPhoneDiv.find("input[name=phone]").focus();
			hiddenDiv.show();
		}
		this.hidden = function() {
			$registByPhoneDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
		this.init = function() {
			$registByPhoneDiv.find(".exit").click(function(event) {
				hiddenDiv.hiddenAll();
			});
			$registByPhoneDiv.find(".toLogin").click(function(event) {
				hiddenDiv.showDiv("loginDiv");
			})
			$registByPhoneDiv.find(".login").click(function(event) {
				hiddenDiv.regist(this);
			});
			$registByPhoneDiv.find("input[name=phone]").change(function(event) {
				$registByPhoneDiv.find(".errorMessage").css('display', 'none');
			});
			$registByPhoneDiv.find("input[name=phone]").keypress(function(e) {
				if (e.keyCode == 13) {
					$registByPhoneDiv.find("input[name=password]").focus();
				}
			});
			$registByPhoneDiv.find("input[name=password]").keypress(function(e) {
				if (e.keyCode == 13) {
					hiddenDiv.regist(this);
				}
			});
		}
	}

	function checkCaptchaDiv() {
		var $checkCaptchaDiv = $("#checkCaptchaDiv");

		this.show = function() {
			$checkCaptchaDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$checkCaptchaDiv.removeAttr('style');
			var phone = userDiv.getUser().phone;
			$checkCaptchaDiv.find(".phonenum").text(phone.substring(0, 3) + "****" + phone.substring(7))
			$checkCaptchaDiv.find("input[name=captcha]").val("");
			$checkCaptchaDiv.find(".regist_send").replaceWith("<span class='regist'>重新发送</span>");
			$checkCaptchaDiv.find(".login").text("下一步");
			hiddenDiv.show();
		}
		this.hidden = function() {
			$checkCaptchaDiv.css('display', 'none');
			hiddenDiv.hidden();
			clearInterval(id);
		}
		this.init = function() {
			$checkCaptchaDiv.find(".exit").click(function(event) {
				hiddenDiv.hiddenAll();
			});
			$checkCaptchaDiv.find(".toLogin").click(function(event) {
				hiddenDiv.showDiv("loginDiv");
			})
			$checkCaptchaDiv.find(".login").click(function(event) {
				hiddenDiv.send(this);
			});
			$checkCaptchaDiv.find("input[name=captcha]").keypress(function(e) {
				if (e.keyCode == 13) {
					hiddenDiv.send(this);
				}
			});
			$checkCaptchaDiv.find(".regist").click(function(event) {
				$(this).replaceWith("<span class='regist_send' data-time='60'>01:00</span>");
				id = setInterval(changeTime, 1000);
			});
		}

		var id;
		function changeTime() {
			var time = $checkCaptchaDiv.find(".regist_send").attr("data-time");
			time--;
			$checkCaptchaDiv.find(".regist_send").attr('data-time', time).text('00:' + time);
			if (time < 10) {
				$checkCaptchaDiv.find(".regist_send").attr('data-time', time).text('00:0' + time);
			}

			if (time == 0) {
				clearInterval(id);
				$checkCaptchaDiv.find(".regist_send").replaceWith("<span class='regist'>重新发送</span>");
				$checkCaptchaDiv.find(".regist").click(function(event) {
					$(this).replaceWith("<span class='regist_send' data-time='60'>01:00</span>");
					id = setInterval(changeTime, 1000);
				});
			}
		}
	}

	function msgDiv() {
		var $msgDiv = $("#msgDiv");

		this.show = function() {
			$msgDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$msgDiv.removeAttr('style');
			hiddenDiv.show();
		}
		this.hidden = function() {
			$msgDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
		this.init = function() {
		}
		this.setData = function(data) {
			setContent(data.content);
			setTitle(data.title);
			setCallback(data.callback);
			return hiddenDiv.getDiv("msgDiv");
		}

		function setCallback(callback) {
			$msgDiv.find(".login").unbind().click(function(event) {
				hiddenDiv.hiddenAll();
				if (callback) {
					callback();
				}
			});
			$msgDiv.find(".exit").unbind().click(function(event) {
				hiddenDiv.hiddenAll();
				if (callback) {
					callback();
				}
			});
		}
		function setContent(content) {
			$msgDiv.find(".login_material p").html(content);
		}
		function setTitle(title) {
			$msgDiv.find(".login_head span").text(title);
		}
	}

	function usernameDiv() {
		var $usernameDiv = $("#usernameDiv");

		this.show = function() {
			$usernameDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$usernameDiv.removeAttr('style');
			hiddenDiv.show();
		}
		this.hidden = function() {
			$usernameDiv.css('display', 'none');
			hiddenDiv.hidden();
		}
		this.init = function() {
			$usernameDiv.find(".exit").click(function(event) {
				hiddenDiv.hiddenAll();
			});
			$usernameDiv.find(".login").click(function(event) {
				hiddenDiv.setUsername(this);
			});
		}
	}

	function simpleMsgDiv() {
		var $simpleMsgDiv = $("#simpleMsgDiv");

		this.show = function() {
			$simpleMsgDiv.siblings().not($(".hiddenDiv .music")).css('display', 'none');
			$simpleMsgDiv.css("display", "block");
			setTimeout(_hidden, 1000);
		}
		this.hidden = _hidden;
		this.setData = function(data) {
			switch (data.status) {
				case "success" :
					setStatus({
						"className" : "success",
						"text" : ""
					});
					setContent(data.content);
					break;
				case "info" :
					setStatus({
						"className" : "info",
						"text" : ""
					});
					setContent(data.content);
					break;
				case "error" :
					setStatus({
						"className" : "error",
						"text" : ""
					});
					setContent(data.content);
					break;
			}
			return hiddenDiv.getDiv("simpleMsgDiv");
		}

		function setContent(content) {
			$simpleMsgDiv.find("span").text(content);
		}
		function setStatus(status) {
			$simpleMsgDiv.find("i").removeClass("success info errror").addClass(status.className).text(status.text);
		}
		function _hidden() {
			$simpleMsgDiv.css('display', 'none');
		}
	}

	function musicDiv() {
		var $musicDiv = $("#musicDiv");

		var progressModule = new progress(this);
		var volumeDivModule = new volumeDiv(this);

		var playListDivModule = new playListDiv(this);

		this.playListDivModule = playListDivModule;
		this.play = playListDivModule.play;
		this.setSong = playListDivModule.setSong;
		this.init = function() {
			playListDivModule.init();
			progressModule.init();
			volumeDivModule.init();

			var $i = $musicDiv.find(".show i");
			$i.click(function() {
				var id;
				var type = $(this).text();
				if (type == "") {
					$(this).text("");
					$i.unbind("hover");
					$musicDiv.unbind();
				} else {
					$(this).text("");
					$i.hover(function() {
						hiddenDiv.showDiv("musicDiv");
						clearTimeout(id);
					}, function() {
					});
					$musicDiv.hover(function() {
						clearTimeout(id);
					}, function() {
						id = setTimeout(hiddenMusicDiv, 1000);
					})
				}
			});

			var $buttons = $musicDiv.find(".music_left");
			$buttons.find(".previous").click(function() {
				playListDivModule.previous();
			});
			$buttons.find(".middle").click(function() {
				var text = $(this).text();
				if (text === "") {
					playListDivModule.play();
				} else {
					playListDivModule.pause();
				}
			});
			$buttons.find(".next").click(function() {
				playListDivModule.next();
			});

			var $playModeDiv = $musicDiv.find(".playModeDiv");
			var id;
			$playModeDiv.find(".playMode").click(function() {
				if ($playModeDiv.find("i").css('display') != "none") {
					clearTimeout(id);
				}
				$playModeDiv.find("i").removeAttr('style');
				id = setTimeout(hiddenPlayMode, 1000);
				var playMode = $playModeDiv.find("i").text();
				switch (playMode) {
					case '循环' :
						$playModeDiv.find(".playMode").text("").attr('title', '单曲循环');
						$playModeDiv.find("i").text("单曲循环");
						playListDivModule.setPlayMode("单曲循环");
						break;
					case '单曲循环' :
						$playModeDiv.find(".playMode").text("").attr('title', '随机');
						$playModeDiv.find("i").text("随机");
						playListDivModule.setPlayMode("随机");
						break;
					case '随机' :
						$playModeDiv.find(".playMode").text("").attr('title', '循环');
						$playModeDiv.find("i").text("循环");
						playListDivModule.setPlayMode("循环");
						break;
				}
			})
		}
		this.show = function() {
			$musicDiv.stop(true).animate({
				'top' : '100%'
			}, 500);
		}
		this.hidden = hiddenMusicDiv;
		this.changePlayTime = playListDivModule.changeTime;
		this.changeVolume = playListDivModule.changeVolume;
		this.changePlayProgress = progressModule.changePlayProgress;
		this.changeCacheProgress = progressModule.changeCacheProgress;
		this.changeVolumeHeight = volumeDivModule.changeVolumeHeight;

		function hiddenMusicDiv() {
			$musicDiv.stop(true).animate({
				'top' : '107%'
			}, 500);
		}

		function hiddenPlayMode() {
			$musicDiv.find(".playModeDiv i").css('display', 'none');
		}
	}

	function playListDiv(musicDiv) {
		var $songNum = $("#musicDiv .num");
		var $playListDiv = $("#playListDiv");
		var $songList = $playListDiv.find(".list_body");
		var $prototype = $playListDiv.find(".prototype").clone().removeClass("prototype");
		var height = parseFloat($playListDiv.find(".playList_hidden_bottom").height());
		var songList = new Array();

		var $pointer;

		var listScrollDivModule = new listScrollDiv();
		var songScrollDivModule = new songScrollDiv();

		this.setSong = function(songId, song) {
			songList[songId] = song;
		}
		this.init = function() {
			listScrollDivModule.init();
			songScrollDivModule.init();

			$("#musicDiv .playList").click(function() {
				showPlayListDiv();
			})
			$playListDiv.find(".song_head i").click(function() {
				hiddenPlayListDiv();
			})
			$playListDiv.find(".list_head .collectionAllSpan").click(function() {
				hiddenDiv.showDiv("collectionDiv");
			})
			$playListDiv.find(".list_head .clearSpan").click(function() {
				$playListDiv.find(".list_body .song").not(".prototype").remove();
				$playListDiv.find(".list_body .none").css('display', 'block');
				$songNum.text(0);
				changeHeight();
			})

			$playListDiv.find(".song_body .fullScreen").click(function() {
				$playListDiv.find(".song_body .lrc").css('width', '100%')[0].webkitRequestFullScreen();
			})
			$playListDiv.find(".song_body .exitFullScreen").click(function() {
				$playListDiv.find(".song_body .lrc").css('width', '95%');
				document.webkitCancelFullScreen();
			})

			var volume_default = 0.5;
			audio.volume = volume_default;
			_musicDiv.changeVolumeHeight(volume_default);
		}
		this.show = showPlayListDiv;
		this.hidden = hiddenPlayListDiv;
		this.add = add;
		this.play = play;
		this.pause = pause;
		this.previous = previous;
		this.next = next;
		this.changeTime = changeTime;
		this.changeVolume = changeVolume;

		function previous() {
			var $songs = $songList.children().not(".prototype, .none");
			$pointer = $songs.eq($songs.index($pointer) - 1);
			stop();
			play();
		}
		function next() {
			var $songs = $songList.children().not(".prototype, .none");
			$pointer = $songs.eq($songs.index($pointer) + 1);
			stop();
			play();
		}
		var timeOutId;
		function add(songId) {
			if ($songList.children("li[data-songId='" + songId + "']").length > 0) {
				return;
			}

			clearTimeout(timeOutId);
			$(".playListDiv .message").removeAttr("style") //
			.find(".content").text("已添加到播放列表");
			timeOutId = setTimeout(function() {
				$(".playListDiv .message").fadeOut("fast");
			}, 1000);

			$playListDiv.find(".list_body .none").css('display', 'none');

			var song = songList[songId];
			var $songEle = $prototype.clone();

			$songEle.attr({
				"data-songId" : songId
			});

			$songEle.find(".songName").attr({
				"title" : song.name,
				"onclick" : 'hiddenDiv.play("' + songId + '");'
			}).text(limitStringLength(song.name, 15));
			$songEle.find(".collection");
			$songEle.find(".delete").click(function() {
				$(this).parents(".song").remove();
				$songNum.text(parseInt($songNum.text()) - 1);
				changeHeight();
			});
			$songEle.find(".songer").attr({
				"title" : song.singerName,
				"data-href" : "singer/{" + song.singerId + "}"
			}).text(limitStringLength(song.singerName, 3));
			$songEle.find(".time").text(song.time);
			$songEle.find(".from").attr({
			// "data-href" : "songList/{" + song.id + "}"
			});

			$songList.append($songEle);

			$songNum.text(parseInt($songNum.text()) + 1);
			$playListDiv.find(".head_left .num").text($songNum.text())

			changeHeight();
		}
		var $playButton = $("#musicDiv .music_left .middle");
		var $play = $("#musicDiv .music_middle");
		var audio = $("#music")[0];
		var flag = true;
		var playModeFunction = playMode_loop;
		this.setPlayMode = function(playMode) {
			switch (playMode) {
				case "循环" :
					playModeFunction = playMode_loop;
					break;
				case "随机" :
					playModeFunction = playMode_random;
					break;
				default : // 单曲循环
					playModeFunction = playMode_singleLoop;
					break;
			}
		}
		function playMode_random() {
			audio.ontimeupdate = function() {
				if (audio.currentTime >= audio.duration) {
					var $songs = $songList.children().not(".prototype, .none");
					$pointer = $songs.eq(parseInt(Math.random() * $songs.length));
					stop();
					play();
				}
			}
		}
		function playMode_singleLoop() {
			audio.ontimeupdate = function() {
				if (audio.currentTime >= audio.duration) {
					stop();
					play();
				}
			}
		}
		function playMode_loop() {
			audio.ontimeupdate = function() {
				if (audio.currentTime >= audio.duration) {
					var $songs = $songList.children().not(".prototype, .none");
					var num = $songs.index($pointer) + 1;
					if (num === $songs.length) {
						num = 0;
					}
					$pointer = $songs.eq(num);
					stop();
					play();
				}
			}
		}
		function stop() {
			pause();
			flag = true;
		}
		function pause() {
			$playButton.text("");
			clearInterval(intervalId);
			audio.pause();
		}
		function play(songId) {
			$playButton.text("");
			clearInterval(intervalId);

			if (songId) {
				flag = true;
				$pointer = $songList.find("li[data-songId='" + songId + "']");
			}

			if ($songList.children().size() <= 2) {
				return pause();
			} else if (!$pointer) {
				$pointer = $songList.children().not(".prototype, .none").eq(0);
			} else if ($songList.children().index($pointer) < 0) {
				$pointer = $songList.children().not(".prototype, .none").eq(0);
			}

			var songId = $pointer.attr("data-songId");
			var song = songList[songId];

			$pointer.prepend("<s class='icomoon'></s>").addClass("now") //
			.siblings().removeClass("now").children("s").remove();

			$play.find("img").attr({
				"src" : song.icon
			}).parent().attr({
				"data-href" : "song/{" + song.id + "}"
			});
			$play.find(".song_name").attr({
				"data-href" : "song/{" + song.id + "}",
				"title" : song.name
			}).text(song.name);
			$play.find(".songer").attr({
				"data-href" : "singer/{" + song.singerId + "}",
				"title" : song.singerName
			}).text(song.singerName);
			$time = $play.find(".time").removeAttr("style");
			$time.find(".total").text(song.time);
			$time.find(".now").text("00:00");
			time = new Date();

			if (flag) {
				audio.src = "file/" + song.id;
				flag = false;
			}
			playModeFunction();
			audio.play();

			intervalId = setInterval(change, 1000);
		}
		var intervalId;
		var $time;
		var _musicDiv = musicDiv;
		function change() {
			var now = audio.currentTime;
			var total = audio.duration;
			if (now >= total) {
				clearInterval(intervalId);
				return;
			}
			$time.find(".now").text(new DateFormatter("mm:ss").format(now * 1000));

			var playRatio = now / total;
			var cacheRatio = audio.buffered.end(0) / total;
			_musicDiv.changePlayProgress(playRatio);
			_musicDiv.changeCacheProgress(cacheRatio);
		}
		function changeTime(ratio) {
			var now = ratio * audio.duration;
			// 谷歌不兼容
			audio.currentTime = now;
		}
		function changeVolume(ratio) {
			audio.volume = ratio;
		}
		function changeHeight() {
			var ratio = 8.5 / parseFloat($songNum.text());
			if (ratio > 1) {
				ratio = 1;
			}
			var topRatio = parseFloat($playListDiv.find("#listScrollDiv .vernier").css("top")) / height;
			if ((ratio + topRatio) > 1) {
				topRatio = 1 - ratio;
				$playListDiv.find("#listScrollDiv .vernier").css("top", topRatio * height + "px");
			}
			$playListDiv.find("#listScrollDiv .vernier").css("height", ratio * 100 + "%");
		}
		function showPlayListDiv() {
			$playListDiv.css({
				'display' : 'block',
				'visibility' : 'visible'
			});
		}
		function hiddenPlayListDiv() {
			$playListDiv.css({
				'display' : 'none',
			});
		}
	}

	function listScrollDiv() {
		var $listScrollDiv = $("#listScrollDiv");
		var $vernier = $listScrollDiv.find(".vernier");
		var $playListDiv = $("#playListDiv");

		this.init = function() {
			$vernier.mouseover(function(event) {
				bindPointEvent();
			});
			$(document).mouseup(function(e) {
				unbindPointEvent();
			})

			function unbindPointEvent() {
				$("body").css('-webkit-user-select', 'inherit');
				$playListDiv.unbind();
				$vernier.unbind().mouseover(function(event) {
					bindPointEvent();
				});
			}
			function bindPointEvent() {
				$("body").css('-webkit-user-select', 'none');
				var y, height, _height;
				$vernier.mousedown(function(e) {
					var temp = this.offsetTop;
					y = e.clientY;
					height = $listScrollDiv.css('height');
					height = new Number(height.substring(0, height.length - 2));
					_height = $vernier.css('height');
					_height = new Number(_height.substring(0, _height.length - 2));
					$playListDiv.mousemove(function(e) {
						var top = temp + e.clientY - y;
						if (top > (height - _height) || top < 0)
							return false;
						var percent = top / (height - _height);
						var height_ = $playListDiv.find(".list_body .song").css('height');
						height_ = new Number(height_.substring(0, height_.length - 2));
						var num = $playListDiv.find(".list_body .song").length - 1;
						$playListDiv.find(".list_body").scrollTop(percent * (height_ * num - height));
						changeHeight(top);
					})
				});
			}
			function changeHeight(top) {
				$("#listScrollDiv .vernier").css('top', top + "px");
			}
		}
		this.show = function() {
			// $("#volumeDiv").removeAttr('style');
		}
		this.hidden = function() {
			// $("#volumeDiv").css('display','none');
		}
	}

	function songScrollDiv() {
		var $songScrollDiv = $("#songScrollDiv");
		var $vernier = $songScrollDiv.find(".vernier");
		var $playListDiv = $("#playListDiv");

		this.init = function() {
			$vernier.mouseover(function(event) {
				bindPointEvent();
			});
			$(document).mouseup(function(e) {
				unbindPointEvent();
			})
		}
		this.show = function() {
			// $("#volumeDiv").removeAttr('style');
		}
		this.hidden = function() {
			// $("#volumeDiv").css('display','none');
		}

		function unbindPointEvent() {
			$("body").css('-webkit-user-select', 'inherit');
			$playListDiv.unbind();
			$vernier.unbind().mouseover(function(event) {
				bindPointEvent();
			});
		}
		function bindPointEvent() {
			$("body").css('-webkit-user-select', 'none');
			var y, height, _height;
			$vernier.mousedown(function(e) {
				var temp = this.offsetTop;
				y = e.clientY;
				height = $songScrollDiv.css('height');
				height = new Number(height.substring(0, height.length - 2));
				_height = $vernier.css('height');
				_height = new Number(_height.substring(0, _height.length - 2));
				$playListDiv.mousemove(function(e) {
					var top = temp + e.clientY - y;
					if (top > (height - _height) || top < 0)
						return false;
					var percent = top / (height - _height);
					var height_ = $playListDiv.find(".song_body .lrc p").css('height');
					height_ = new Number(height_.substring(0, height_.length - 2));
					var num = $playListDiv.find(".song_body .lrc p").length;
					$playListDiv.find(".song_body .lrc").scrollTop(percent * (height_ * num - height));

					changeHeight(top);
				})
			});
		}
		function changeHeight(top) {
			$vernier.css('top', top + "px");
		}
	}

	function volumeDiv(musicDiv) {
		var $volumeDiv = $("#volumeDiv");
		var $musicDiv = $("#musicDiv");
		var flag = false;
		var height_;
		var _musicDiv = musicDiv;
		this.init = function() {
			height_ = $musicDiv.offset().top;
			$musicDiv.find(".volume").click(function() {
				if (flag) {
					flag = false;
					hiddenVolumeDiv();
				} else {
					flag = true;
					showVolumeDiv();
				}
			})
			$volumeDiv.find(".point").mouseover(function(event) {
				bindPointEvent();
			});
			$volumeDiv.find(".max").click(function(e) {
				var max_height = $(this).css('height');
				max_height = new Number(max_height.substring(0, max_height.length - 2));
				var div_height = $volumeDiv.css('height');
				div_height = new Number(div_height.substring(0, div_height.length - 2));
				var length = e.clientY - (height_ - max_height - (div_height - max_height) / 2);
				changeVolume(length, max_height);
			});
			$(document).mouseup(function(e) {
				unbindPointEvent();
			})
		}
		this.show = showVolumeDiv;
		this.hidden = hiddenVolumeDiv;
		this.changeVolumeHeight = changeVolumeHeight;

		function showVolumeDiv() {
			$volumeDiv.removeAttr('style');
		}
		function hiddenVolumeDiv() {
			$volumeDiv.css('display', 'none');
		}
		function unbindPointEvent() {
			$("body").css('-webkit-user-select', 'inherit');
			$volumeDiv.unbind();
			$volumeDiv.find(".point").unbind().mouseover(function(event) {
				bindPointEvent();
			});
		}
		function bindPointEvent() {
			$("body").css('-webkit-user-select', 'none');
			var y, height;
			$volumeDiv.find(".point").mousedown(function(e) {
				var temp = this.offsetTop;
				y = e.clientY;
				height = $volumeDiv.find(".max").css('height');
				height = new Number(height.substring(0, height.length - 2));
				$volumeDiv.mousemove(function(e) {
					var top = temp + e.clientY - y + $volumeDiv.find(".max").offset().top - $volumeDiv.offset().top;
					if (top > height || top < 0)
						return false;
					changeVolume(top, height);
				})
			});
		}
		var $volume = $(".volumeDiv .volume");
		function changeVolume(top, height) {
			_musicDiv.changeVolume((height - top) / height);

			var radio = (height - top) / height;
			if (radio === 0) {
				$volume.text("");
			} else if (radio < 0.3) {
				$volume.text("");
			} else if (radio < 0.7) {
				$volume.text("");
			} else {
				$volume.text("");
			}

			$volumeDiv.find(".point").css('top', top + "px");
			$volumeDiv.find(".now").css('height', height - top + "px");
		}
		function changeVolumeHeight(radio) {
			var height = $volumeDiv.find(".max").height();
			changeVolume(height * (1 - radio), height);
		}
	}

	function progress(musicDiv) {
		var $musicDiv = $("#musicDiv");
		var $progress = $musicDiv.find(".progress");
		var $playing = $progress.find(".playing");
		var $point = $progress.find(".point");
		var $cache = $progress.find(".downloading");
		var _musicDiv = musicDiv;

		this.init = function() {
			$point.mouseover(function(event) {
				bindPointEvent();
			});

			$progress.click(function(e) {
				var length = e.clientX - $progress.offset().left;
				changeTime(length);
			});

			$(document).mouseup(function(e) {
				unbindPointEvent();
			});
		}
		this.changePlayProgress = function(ratio) {
			var width = ratio * $progress.width();
			$playing.width(width);
			$point.css("left", width + "px");
		}
		this.changeCacheProgress = function(ratio) {
			var width = ratio * $progress.width();
			$cache.width(width);
		}
		function unbindPointEvent() {
			$("body").css('-webkit-user-select', 'inherit');
			$musicDiv.unbind();
			$point.unbind().mouseover(function(event) {
				bindPointEvent();
			});
		}
		function bindPointEvent() {
			$("body").css('-webkit-user-select', 'none');
			var x, width;
			$point.mousedown(function(e) {
				var temp = this.offsetLeft;
				x = e.clientX;
				width = $progress.css('width');
				width = new Number(width.substring(0, width.length - 2));
				$musicDiv.mousemove(function(e) {
					var left = temp + e.clientX - x;
					if (left > width || left < 0)
						return false;
					changeTime(left);
				})
			});
		}
		function changeTime(left) {
			_musicDiv.changePlayTime(parseFloat(left / $progress.width()));
			$point.css('left', left + "px");
			$playing.css('width', left + "px");
		}
	}

	window.userDiv = new function() {
		var $userDiv = $("#userDiv");

		this.init = function() {
			if (localStorage.getItem("login_auto")) {
				sessionStorage.setItem("user", localStorage.getItem("user"));
			}

			var user = userDiv.getUser();

			if (user && user.name) {
				this.login();
			} else {
				_logout();
				// 第三方登录，暂时先这样
				if (location.search && location.search !== "") {
					var params = location.search.substring(1).split("&");
					var id = params[0];
					var name = params[1];
					$.ajax({
						url : "user/" + id,
						type : "GET",
						dataType : "json",
						success : function(data) {
							sessionStorage.setItem("user", JSON.stringify(data));

							if (!name || name === "") {
								hiddenDiv.showDiv("usernameDiv");
							} else {
								userDiv.login();
							}
						},
						error : function(e) {
							console.log(e);
						}
					})
					history.replaceState(null, null, location.href.split("?")[0]);
				}
			}
		}
		this.logout = function() {
			_logout();

			router.startRouter("index");
			hiddenDiv.showDiv("simpleMsgDiv").setData({
				"status" : "info",
				"content" : "退出成功"
			});
		}
		var firstFlag = true;
		this.login = function() {
			var user = userDiv.getUser();

			$userDiv.find(".username").empty().append("<img src='" + user.icon + "'>" + user.name + "<i></i>");
			unbindEvents();
			bindEvents('nav_login');

			router.startRouter("index");

			$("#myMusic").attr({
				"data-href" : "myMusic/{" + user.id + "}"
			})

			if (firstFlag) {
				firstFlag = false;
				return;
			}

			hiddenDiv.showDiv("simpleMsgDiv").setData({
				"status" : "success",
				"content" : "登录成功"
			});
		}
		this.getUser = function(flag) {
			if (flag) {
				if (sessionStorage.getItem("user_login_flag") !== "false") {
					return JSON.parse(sessionStorage.getItem("user"));
				} else {
					return null;
				}
			} else {
				return JSON.parse(sessionStorage.getItem("user"));
			}
		}
		this.getUserId = function(flag) {
			var user = userDiv.getUser(flag);
			if (user) {
				return user.id;
			} else {
				return undefined;
			}
		}

		function _logout() {
			sessionStorage.removeItem("user");
			localStorage.removeItem("user");
			localStorage.removeItem("login_auto");

			$userDiv.find(".username").empty().text("登录");
			unbindEvents();
			bindEvents('nav_logout');
		}
		function unbindEvents() {
			$userDiv.unbind() //
			.find(".user_nav").css("display", "none");
		}

		function bindEvents(eleName) {
			$userDiv.hover(function() {
				$(this).children('.' + eleName).stop(true).slideDown(300);
			}, function() {
				$(this).children('.' + eleName).stop(true).slideUp(300);
			});
		}
	}

	window.limitStringLength = function limitStringLength(content, length) {
		if (!content) {
			return;
		}

		var num = content.split(/\w|\s/).length - 1;
		var realLength = (content.length - num) * 2 + num;
		if (realLength > length * 2) {
			var _length = length * 2;
			var i = 0;
			var _content = "";
			while (_length > 0) {
				if (content.charAt(i).match(/\w|\s/)) {
					_length -= 1;
				} else {
					_length -= 2;
				}
				_content += content.charAt(i);
				i++;
			}
			return _content + "..";
		} else {
			return content;
		}
	}

	// $yyyy-$MM-$dd $HH:$mm:$ss
	window.DateFormatter = function DateFormatter(regExpString) {
		var array = new Array();
		var str = regExpString;
		str = parse(str);

		function parse(regExpString) {
			var possibleValues = new Array();
			var str = regExpString;

			possibleValues.splice(0, possibleValues.length, 2, 4);
			str = parse_base("y", "year", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("M", "month", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("d", "day", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("H", "hour", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("m", "minute", str, possibleValues);

			possibleValues.splice(0, possibleValues.length, 2);
			str = parse_base("s", "second", str, possibleValues);

			return str;
		}

		function parse_base(part, total, regExpString, possibleValues) {
			var num = regExpString.split(part).length - 1;
			var str = regExpString;
			array[total] = new DateItem();

			for (var i = 0; i < possibleValues.length; i++) {
				if (num === possibleValues[i] && str.split(increaseStr(part, num)).length === 2) {
					var _str = increaseStr(part, num);
					str = str.replace(_str, "$" + _str);
					array[total].regExp = "$" + _str;
					array.length += 1;
					return str;
				}
				if (num === 0) {
					return str;
				}
			}
			alert("格式错误：" + regExpString);
			throw "格式错误：" + regExpString;
		}

		function increaseStr(str, num) {
			var result = "";
			if (num === 0) {
				return result;
			}
			for (var i = 0; i < num; i++) {
				result += str;
			}
			return result;
		}

		function DateItem(regExp, num) {
			this.regExp = regExp;
			this.num = num;
		}

		this.format = function(time) {
			var date = new Date();
			date.setTime(time);
			array["year"].num = date.getFullYear();
			array["month"].num = date.getMonth() + 1;
			array["day"].num = date.getDate();
			array["hour"].num = date.getHours();
			array["minute"].num = date.getMinutes();
			array["second"].num = date.getSeconds();

			return replace(array, str);
		}

		function replace(array, str) {
			var _str = str;
			for ( var i = 0 in array) {
				var num = "";
				var regExp = array[i].regExp;
				if (regExp) {
					if (array[i].num || array[i].num === 0) {
						num = array[i].num.toString();
						if (num.length < regExp.length - 1) {// 时分秒为0
							num = increaseStr("0", regExp.length - 1 - num.length) + num;
						} else {// 年只有两位
							num = num.substring(num.length - regExp.length + 1);
						}
					}
					_str = _str.replace(regExp, num);
				}
			}
			return _str;
		}
	}

	$(document).ready(function() {
		hiddenDiv.init();
		userDiv.init();
	})
}())
