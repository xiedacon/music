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