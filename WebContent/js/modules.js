(function() {

	var $hiddenDiv = $("#hiddenDiv");
	function show($ele, resetFunction) {
		$ele.removeAttr('style') //
		.siblings().not($(".hiddenDiv .music")).css('display', 'none');
		var height = $("body").css('height');
		var width = $("body").css('width');
		$hiddenDiv.css({
			'height' : height,
			'width' : width,
			'visibility' : 'visible'
		});
		$("body").attr("onmousewheel", "return false;");
		if (resetFunction && resetFunction instanceof Function) {
			resetFunction();
		}
	}
	function hiddenAll($ele) {
		$ele.css('display', 'none');
		$hiddenDiv.css('visibility', 'hidden');
		$("body").removeAttr("onmousewheel");
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
					dataType : data.dataType,
					success : function(_data) {
						data.success(_data);
					},
					error : function(e) {
						console.error(e);
					}
				});
			} else {
				showErrorMsg(data.eleName, 'password', '密码格式错误');
			}
		} else {
			showErrorMsg(data.eleName, 'phone', '手机号格式错误');
		}
	}
	function showErrorMsg(eleName, name, e) {
		$("#" + eleName + " .errorMessage").css('display', 'block').html("<i></i>" + e);
		$("#" + eleName + " input[name=" + name + "]").focus();
	}

	function Prototype() {
	}
	Prototype.prototype = {
		show : function() {
			show(this.$ele);
		},
		hiddenAll : function() {
			hiddenAll(this.$ele);
		}
	};

	var $loginDiv = $("#loginDiv");
	function Login() {
		this.$ele = $loginDiv;
	}
	Login.prototype = new Prototype();
	var login = new Login();
	MMR.addModule("login", login);

	var $loginByPhoneDiv = $("#loginByPhoneDiv");
	function LoginByPhone() {
		this.$ele = $loginByPhoneDiv;
	}
	LoginByPhone.prototype = new Prototype();
	LoginByPhone.prototype.show = function() {
		show($loginByPhoneDiv, function() {
			$loginByPhoneDiv.find("input[name=phone]").val("");
			$loginByPhoneDiv.find("input[name=password]").val("");
			$loginByPhoneDiv.find(".errorMessage").css('display', 'none');
			$loginByPhoneDiv.find("input[name=phone]").focus();
			$loginByPhoneDiv.find(".login").text("登 录");
		});
	}
	LoginByPhone.prototype.init = function() {
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
				loginByPhone.login();
			}
		});
	}
	LoginByPhone.prototype.login = function login() {
		var phone = $loginByPhoneDiv.find('input[name=phone]').val();
		var password = $loginByPhoneDiv.find('input[name=password]').val();
		var login_auto = $loginByPhoneDiv.find('input[name=login_auto]').prop("checked");

		// 检验/发送
		post({
			eleName : "loginByPhoneDiv",
			phone : phone,
			password : password,
			before : function() {
				$loginByPhoneDiv.find(".errorMessage").css('display', 'none');
				$loginByPhoneDiv.find(".login").text("登录中...");
			},
			url : "user/login_phone",
			data : "phone=" + phone + "&password=" + b64_md5(password),// 加密
			dataType : "json",
			success : function(data) {// code,user,error
				// 返回结果
				if (data.code == 200) {
					UserManager.setUser(data.user, login_auto).login();

					MMR.get('loginByPhone').hiddenAll();
					MMR.get("simpleMsg").showSuccess("登陆成功");
				} else {
					var error = data.error;
					showErrorMsg('loginByPhoneDiv', error.name, error.value);
					$loginByPhoneDiv.find(".login").text("下一步");
				}
			}
		});
	}
	var loginByPhone = new LoginByPhone();
	loginByPhone.init();
	MMR.addModule("loginByPhone", loginByPhone);

	var $registByPhoneDiv = $("#registByPhoneDiv");
	function RegistByPhone() {
		this.$ele = $registByPhoneDiv;
	}
	RegistByPhone.prototype = new Prototype();
	RegistByPhone.prototype.show = function() {
		show($registByPhoneDiv, function() {
			$registByPhoneDiv.find("input[name=phone]").val("");
			$registByPhoneDiv.find("input[name=password]").val("");
			$registByPhoneDiv.find(".errorMessage").css('display', 'none');
			$registByPhoneDiv.find(".login").text("下一步");
			$registByPhoneDiv.find("input[name=phone]").focus();
		});
	}
	RegistByPhone.prototype.init = function() {
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
				MMR.get('registByPhone').regist(this);
			}
		});
	}
	RegistByPhone.prototype.regist = function regist() {
		var phone = $registByPhoneDiv.find('input[name=phone]').val();
		var password = $registByPhoneDiv.find('input[name=password]').val();

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
			"data" : "phone=" + phone + "&password=" + password,
			"success" : function(data) {
				// 返回结果
				if (data.code == 200) {
					UserManager.setUser_regist(data.user);
					MMR.get("checkCaptcha").show();
				} else if (data.code == 302) {
					UserManager.setUser(data.user, true);
					MMR.get("message").setData({
						"title" : $registByPhoneDiv.find(".login_head span").text(),
						"content" : "该手机号已与云音乐帐号<span> " + data.user.name + " </span>绑定，<br />以后你可以直接用该手机号+密码登录",
						"callback" : function() {
							UserManager.login();
						}
					}).show();
				} else {
					var error = data.error;
					showErrorMsg('registByPhoneDiv', error.name, error.value);
					$registByPhoneDiv.find(".login").text("下一步");
				}
			}
		});
	}
	var registByPhone = new RegistByPhone();
	registByPhone.init();
	MMR.addModule("registByPhone", registByPhone);

	var $checkCaptchaDiv = $("#checkCaptchaDiv");
	function CheckCaptcha() {
		var id;
		this.$ele = $checkCaptchaDiv;
		this.changeTime = function() {
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
					id = setInterval(MMR.get('checkCaptcha').changeTime, 1000);
				});
			}
		}
	}
	CheckCaptcha.prototype = new Prototype();
	CheckCaptcha.prototype.show = function() {
		show($checkCaptchaDiv, function() {
			var phone = userDiv.getUser_regist().phone;
			$checkCaptchaDiv.find(".phonenum").text(phone.substring(0, 3) + "****" + phone.substring(7))
			$checkCaptchaDiv.find("input[name=captcha]").val("");
			$checkCaptchaDiv.find(".regist_send").replaceWith("<span class='regist'>重新发送</span>");
			$checkCaptchaDiv.find(".login").text("下一步");
		});
	}
	CheckCaptcha.prototype.init = function() {
		$checkCaptchaDiv.find("input[name=captcha]").keypress(function(e) {
			if (e.keyCode == 13) {
				MMR.get("checkCaptcha").sendCaptcha();
			}
		});
		$checkCaptchaDiv.find(".regist").click(function(event) {
			$(this).replaceWith("<span class='regist_send' data-time='60'>01:00</span>");
			id = setInterval(changeTime, 1000);
		});
	}
	CheckCaptcha.prototype.sendCaptcha = function() {
		var captcha = $checkCaptchaDiv.find('input[name=captcha]').val();

		// 校验
		if (/\d{4}/.text(captcha)) {
			return showErrorMsg('checkCaptchaDiv', 'captcha', '验证码错误');
		}
		$checkCaptchaDiv.find(".errorMessage").css('display', 'none');
		$checkCaptchaDiv.find(".login").text("正在验证...");

		// 加密
		// 发送
		// 返回结果

		$checkCaptchaDiv.find(".login").text("下一步");
		MMR.get("username").show();
	}
	var checkCaptcha = new CheckCaptcha();
	checkCaptcha.init();
	MMR.addModule("checkCaptcha", checkCaptcha);

	var $msgDiv = $("#msgDiv");
	function Message() {
		this.$ele = $msgDiv;
	}
	Message.prototype = new Prototype();
	Message.prototype.setData = function(data) {
		setContent(data.content);
		setTitle(data.title);
		setCallback(data.callback);
		return MMR.get("message");

		function setCallback(callback) {
			$msgDiv.find(".login").unbind().click(function(event) {
				MMR.get('message').hiddenAll();
				if (callback) {
					callback();
				}
			});
			$msgDiv.find(".exit").unbind().click(function(event) {
				MMR.get('message').hiddenAll();
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
	var message = new Message();
	MMR.addModule("message", message);

	var $checkDiv = $("#checkDiv");
	function Check() {
		this.$ele = $checkDiv;
	}
	Check.prototype = new Prototype();
	Check.prototype.setData = function(data) {
		setContent(data.content);
		// setTitle(data.title);
		setCallback(data.callback);
		return MMR.get("check");

		function setCallback(callback) {
			$checkDiv.find(".login").unbind().click(function(event) {
				MMR.get('check').hiddenAll();
				if (callback) {
					callback.yes();
				}
			});
			$checkDiv.find(".exit, .regist").unbind().click(function(event) {
				MMR.get('check').hiddenAll();
				if (callback) {
					callback.no();
				}
			});
		}
		function setContent(content) {
			$checkDiv.find(".login_material p").html(content);
		}
		function setTitle(title) {
			$checkDiv.find(".login_head span").text(title);
		}
	}
	var check = new Check();
	MMR.addModule("check", check);

	var $usernameDiv = $("#usernameDiv");
	function Username() {
		this.$ele = $usernameDiv;
	}
	Username.prototype = new Prototype();
	Username.prototype.show = function() {
		show($usernameDiv, function() {
			$usernameDiv.find("input[name='username']").focus();
		});
	};
	Username.prototype.init = function() {
		$usernameDiv.find("input[name='username']").keypress(function(e) {
			if (e.keyCode == 13) {
				MMR.get("username").updateUsername();
			}
		})
	};
	Username.prototype.updateUsername = function() {
		var id = UserManager.getUser_regist().id;
		var username = $usernameDiv.find('input[name=username]').val();
		sessionStorage.removeItem("regist_user");
		// 校验
		if (!/\w+/.test(username)) {
			return showErrorMsg('usernameDiv', 'username', '昵称格式错误');;
		}
		$usernameDiv.find(".errorMessage").css('display', 'none');
		$usernameDiv.find(".login").text("设置中...");
		// 发送
		$.ajax({
			url : "user/" + id + "/username?id=" + id + "&username=" + username,
			dataType : "json",
			type : "PATCH",
			success : function(data) {
				// 返回结果
				if (data.code == 200) {
					UserManager.setUser(data.user);
					MMR.get("message").setData({
						"title" : $usernameDiv.find(".login_head span").text(),
						"content" : "恭喜，设置成功！",
						"callback" : function() {
							UserManager.login();
						}
					}).show();
				} else {
					var error = data.error;
					showErrorMsg('usernameDiv', error.name, error.value);
				}
			},
			error : function(e) {
				console.error(e);
			}
		});
	}
	var username = new Username();
	username.init();
	MMR.addModule("username", username);

	var $simpleMsgDiv = $("#simpleMsgDiv");
	function SimpleMsg() {
		this.$ele = $simpleMsgDiv;
	}
	SimpleMsg.prototype = new Prototype();
	SimpleMsg.prototype.show = function() {
		$simpleMsgDiv.css("display", "block");
		setTimeout(MMR.get("simpleMsg").hidden, 1000);
	}
	SimpleMsg.prototype.hidden = function() {
		$simpleMsgDiv.css('display', 'none');
	};
	SimpleMsg.prototype.showError = function(error) {
		this.setData({
			"status" : "error",
			"content" : error
		}).show();
	};
	SimpleMsg.prototype.showInfo = function(info) {
		this.setData({
			"status" : "info",
			"content" : info
		}).show();
	};
	SimpleMsg.prototype.showSuccess = function(success) {
		this.setData({
			"status" : "success",
			"content" : success
		}).show();
	};
	SimpleMsg.prototype.setData = function(data) {
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
		return this;

		function setContent(content) {
			$simpleMsgDiv.find("span").text(content);
		}
		function setStatus(status) {
			$simpleMsgDiv.find("i").removeClass("success info errror").addClass(status.className).text(status.text);
		}
	}
	var simpleMsg = new SimpleMsg();
	MMR.addModule("simpleMsg", simpleMsg);

	UserManager.init();// 第三方登录的问题

	var $addMenuDiv = $("#addMenuDiv");
	function AddMenu() {
		this.$ele = $addMenuDiv;
	}
	AddMenu.prototype = new Prototype();
	AddMenu.prototype.show = function() {
		show($addMenuDiv, function() {
			$addMenuDiv.find(".name").val("");
			$addMenuDiv.find(".errorMessage").css('display', 'none');
			$addMenuDiv.find(".name").focus();
		});
	}
	AddMenu.prototype.add = function() {
		var name = $addMenuDiv.find("input[name='name']").val();
		// 校验
		if (name && !/\W+/.test(name)) {
			var userId = UserManager.getUserId();
			if (!userId) {
				return MMR.get('simpleMsg').showError("添加失败");
			}
			// 发送
			$.ajax({
				url : "songMenu",
				type : "POST",
				context : this,
				data : {
					creatorId : userId,
					name : name
				},
				dataType : "json",
				success : function(data) {
					// 返回
					if (data.code === 200) {
						this.hiddenAll();
						MMR.get('simpleMsg').showSuccess("添加成功");
						router.startRouter("myMusic?userId=" + UserManager.getUserId(), true);
					} else {
						showErrorMsg("addMenuDiv", "name", data.error.value);
					}
				}
			})
		} else {
			showErrorMsg("addMenuDiv", "name", "歌单名格式错误");
		}
	}
	var addMenu = new AddMenu();
	MMR.addModule("addMenu", addMenu);

	MMR.deleteSongMenu = function(that) {
		var $songMenu = $(that).parents(".option");
		var id = $songMenu.attr("data-id");
		var name = $songMenu.find(".name").text();
		MMR.get('check').setData({
			content : "删除" + name + "？",
			callback : {
				yes : function() {
					$.ajax({
						url : "songMenu/" + id,
						type : "DELETE",
						dataType : "json",
						success : function(data) {
							if (data.code === 200) {
								MMR.get('simpleMsg').showSuccess("删除成功");
							} else {
								MMR.get('simpleMsg').showError(error.songMenu);
							}
							router.startRouter("myMusic?userId=" + UserManager.getUserId(), true);
						},
						error : function() {
							MMR.get('simpleMsg').showError("删除失败");
						}
					})
				},
				no : function() {
					MMR.get('check').hiddenAll();
				}
			}
		}).show();
	}

	MMR.collectSongMenu = function(songMenuId) {
		var userId = UserManager.getUserId();
		if (userId && songMenuId) {
			$.ajax({
				url : "user/" + userId + "/collectSongMenu",
				type : "POST",
				data : {
					"songMenuId" : songMenuId
				},
				dataType : "json",
				success : function(data) {
					if (data.code === 200) {
						MMR.get("simpleMsg").showSuccess("收藏成功");
						router.startRouter("songMenu?songMenuId=" + songMenuId, true);
					} else if (data.code === 302) {
						MMR.get("simpleMsg").showInfo(data.songMenuId);
					} else {
						MMR.get("simpleMsg").showError(data.error.value);
					}
				},
				error : function() {
					MMR.get("simpleMsg").showError("收藏失败");
				}
			})
		}
	}

	var $tagsDiv = $("#tagsDiv");
	function Tags() {
		this.$ele = $tagsDiv;
	}
	Tags.prototype = new Prototype();
	Tags.prototype.show = function() {
		$.ajax({
			url : "songMenuTag",
			type : "GET",
			dataType : "json",
			success : function(data) {
				if(data.code != 200){
					MMR.get("simpleMsg").showError(data.error.value);
					return;
				}
				
				var tags = data.data;
				show($tagsDiv, function() {
					var $existTags = $("#tags").find(".tag");
					var tagIds = new Array();
					for (var i = 0; i < $existTags.length; i++) {
						var id = $existTags.eq(i).attr("data-id");
						tagIds[id] = id;
					}
					var $tags = $tagsDiv.find("ul");
					$tags.children("li[class!='prototype']").remove();
					var $prototype = $tags.children(".prototype").clone().removeClass("prototype");
					var $_prototype = $prototype.find(".prototype").clone().removeClass("prototype");
					var $tag, tag;
					for (var i = 0; i < tags.length; i++) {
						$tag = $prototype.clone();
						tag = tags[i];
						$tag.find("strong").text(tag.name + "：");
						var $childs = $tag.find(".buttons");
						var $child, child;

						for (var j = 0; j < tag.secondTagList.length; j++) {
							$child = $_prototype.clone();
							child = tag.secondTagList[j];
							$child.attr("data-id", child.id);
							$child.attr("data-name", child.name);
							$child.find(".content").text(child.name);

							if (tagIds[child.id]) {
								$child.find(".flag").css("display", "block");
								$child.attr("data-flag", "true");
							}
							$child.on("click", function(event) {
								if ($(this).attr("data-flag")) {
									$(this).removeAttr("data-flag");
									$(this).find(".flag").css("display", "none");
								} else {
									if ($tags.find(".button[data-flag='true']").length < 3) {
										$(this).attr("data-flag", "true");
										$(this).find(".flag").css("display", "block");
									} else {
										$tagsDiv.find(".message").stop(true).fadeIn(300).delay(500).fadeOut(300);
									}
								}
							});

							$childs.append($child);
						}
						$tags.append($tag);
					}
				});
			}
		})
	}
	Tags.prototype.save = function() {
		var $tags = $("#tags");
		$tags.children(".tag").remove();
		var $checkedTags = $tagsDiv.find(".button[data-flag='true']");
		for (var i = 0; i < $checkedTags.length; i++) {
			var id = $checkedTags.eq(i).attr("data-id");
			var name = $checkedTags.eq(i).attr("data-name");
			$tags.prepend("<span data-id='" + id + "' class='tag'>" + name + "<i class='icomoon'></i></span>");
		}
		$tags.find("i").on("click", function() {
			$(this).parent().remove();
		})
		MMR.get('tags').hiddenAll();
	}
	var tags = new Tags();
	MMR.addModule("tags", tags);

	var $collectionDiv = $("#collectionDiv");
	function Collection() {
		this.$ele = $collectionDiv;
	}
	Collection.prototype = new Prototype();
	Collection.prototype.collect = function(songId) {
		var userId = UserManager.getUserId();
		if (userId) {
			$.ajax({
				url : "songMenu/creatorId_" + userId,
				dataType : "json",
				type : "GET",
				success : function(data) {
					if(data.code != 200){
						MMR.get("simpleMsg").showError(data.error.value);
						return;
					}
					
					var songMenus = data.data //
					, $songMenus = $collectionDiv.find(".login_material");
					$songMenus.children("p[class='menu']").remove();
					var $prototype = $songMenus.find(".prototype").clone().removeClass("prototype");
					var $songMenu, songMenu;

					for (var i = 0; i < songMenus.length; i++) {
						songMenu = songMenus[i];
						$songMenu = $prototype.clone();

						$songMenu.attr({
							onclick : "MMR.get('collection').addToSongMenu('" + songMenu.id + "','" + songId + "');"
						})
						$songMenu.find("img").attr({
							"src" : songMenu.icon,
							"onclick" : "hiddenDiv.addToSongMenu(" + songId + ");"
						});
						$songMenu.find(".name").text(songMenu.name);
						$songMenu.find(".num").text(songMenu.songNum + "首");
						$songMenus.append($songMenu);
					}

					MMR.get("collection").show();
				},
				error : function(e) {
					console.error(e);
				}
			})
		} else {
			MMR.get("login").show();
		}
	}
	Collection.prototype.addToSongMenu = function(songMenuId, songId) {
		$.ajax({
			url : "songMenu/" + songMenuId + "/song",
			type : "POST",
			data : {
				"songId" : songId
			},
			dataType : "json",
			success : function(data) {
				MMR.get("collection").hiddenAll();
				if (data.code === 200) {
					MMR.get("simpleMsg").showSuccess("添加成功");
				} else {
					MMR.get("simpleMsg").showError(data.error.value);
				}
			},
			error : function() {
				MMR.get("collection").hiddenAll();
				MMR.get("simpleMsg").showError("添加失败");
			}
		})
	}
	var collection = new Collection();
	MMR.addModule("collection", collection);

	MMR.addComment = function(type, id) {
		var $newComment = $("#newComment");
		var userId = UserManager.getUserId();
		if (!userId) {
			return MMR.get("login").show();
		}
		var comment = $newComment.val();
		if (!comment || comment === "" || comment.length > 200) {
			return;
		}
		if (id) {
			comment = FUNCTION.shortnameToImage(comment);
			$.ajax({
				url : "comment/" + type,
				type : "POST",
				data : {
					creatorId : userId,
					typeId : id,
					comment : comment
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 200) {
						MMR.get("simpleMsg").showSuccess("评论成功");

						router.startRouter(type + "?" + type + "Id=" + PageScope.params[type + "Id"], true);
					} else {
						MMR.get("simpleMsg").showError("评论失败")
					}
				}
			})
		}
	}
	MMR.agreeComment = function(that, commentId) {
		var userId = UserManager.getUserId();
		if (!userId) {
			return MMR.get("login").show();
		}
		if (!commentId) {
			return;
		}
		var page = PageScope.page;
		if (page && !sessionStorage["flag_agreed_" + commentId]) {
			$.ajax({
				url : "comment/" + commentId + "/" + page,
				type : "PUT",
				data : {
					creatorId : userId
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 200) {
						that = $(that);
						if (sessionStorage["flag_agreed_" + commentId] && sessionStorage["flag_agreed_" + commentId] === "true") {
							sessionStorage["flag_agreed_" + commentId] = false;
							that.siblings().removeAttr("style");
							that.text(+(that.text()) - 1);
							MMR.get("simpleMsg").showInfo("取消成功");
						} else {
							sessionStorage["flag_agreed_" + commentId] = true;
							that.siblings().css("color", "#ff0000");
							that.text(+(that.text()) + 1);
							MMR.get("simpleMsg").showSuccess("点赞成功");
						}
					} else {
						MMR.get("simpleMsg").showError(data.error.value);
					}
				}
			})
		}
	}
}())
