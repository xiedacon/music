


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


this.collect = collect;
this.addToSongMenu = addToSongMenu;
function addToSongMenu(songMenuId, type, id) {
	$.ajax({
		url : "songMenu/" + songMenuId + "/song",
		type : "POST",
		data : {
			"type" : type,
			"id" : id
		},
		dataType : "json",
		success : function() {
			hiddenDiv.getDiv("simpleMsgDiv").setData({
				"state" : "info",
				"content" : "操作成功"
			});
		},
		error : function() {
			hiddenDiv.getDiv("simpleMsgDiv").setData({
				"state" : "error",
				"content" : "操作失败"
			});
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