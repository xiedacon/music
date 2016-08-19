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