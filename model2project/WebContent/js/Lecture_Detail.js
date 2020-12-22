$(function(){
$(".manageList").hide();
$(".cl").hide();
$(".lu").hide();
	$(".listTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".lectureList").show();
		$(".manageList").hide();
	});
	$(".manageTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".lectureList").hide();
		$(".manageList").show();
	});	
	$(".op").click(function(){
		$(".sidebar").hide();
		$(".cl").show();
		$(".op").hide();
		$(".lu").hide();
		$("iframe").css("width", "1310px");
	});
	$(".cl").click(function(){
		$(".cl").hide();
		$(".op").show();
		$(".sidebar").show();
		$("iframe").css("width", "900px");
	});	
	$(".lug").click(function(){
		$(".lug").hide();
		$(".lu").show();
	});	
	$(".back").click(function(){
		$(".lu").hide();
		$(".lug").show();
	});	
});

$(function () {
	var num = 0;
	var $move = $(".photoList > ul > li");
	
	$move.eq(num).find("a").addClass("on");
	
	function listUp() {
		if ($(".photoList > ul").queue().length > 0) return;
		if (num > 0) {
			num--;
			$move.find("a").removeClass("on");
			$move.eq(num).find("a").addClass("on");
			if (num > 0 && num < $move.length - 2) {
				$(".photoList > ul").animate({ "top": "+=51px" });
			}
		} else {
			return;
		}
	}
	
	function listDown() {
		if ($(".photoList > ul").queue().length > 0) return;
		if (num < $move.length - 1) {
			num++;
			$move.find("a").removeClass("on");
			$move.eq(num).find("a").addClass("on");
			if (num > 1 && num < $move.length - 1) {
				$(".photoList > ul").animate({ "top": "-=51px"});
			}
		} else {
			return;
		}
	}
	
	$(".up").click(function() { listUp(); });
	$(".down").click(function() { listDown(); });
	
	$(".lectureList").on("mousewheel DOMMouseScroll", function (e) {
		if (e.originalEvent.wheelDelta == 120 || e.originalEvent.detail == -3) {
			listUp();
		} else if (e.originalEvent.wheelDelta == -120 || e.originalEvent.detail == 3) {
			listDown();
		}
	});
	$(".manageList").on("mousewheel DOMMouseScroll", function (e) {
		if (e.originalEvent.wheelDelta == 120 || e.originalEvent.detail == -3) {
			listUp();
		} else if (e.originalEvent.wheelDelta == -120 || e.originalEvent.detail == 3) {
			listDown();
		}
	});
});