

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
