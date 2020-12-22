$(function(){
	$(".loginTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".loginFormDiv").show();
		$(".joinFormDiv").hide();
	});
	$(".joinTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".loginFormDiv").hide();
		$(".joinFormDiv").show();
	});
	$(".studentTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".teacherInfo").hide();
		$(".studentInfo").show();
	});
	$(".teacherTab").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
		$(".studentInfo").hide();
		$(".teacherInfo").show();
	});
	
	$("#idSlot1").on("change", function(){
		var idReg = /^[A-za-z0-9]{5,15}/g;
        if( !idReg.test( $("#idSlot1").val() ) ) {
			$("#alertDiv1").css("color","red").html("5~15자의 영문 및 숫자만 사용하실 수 있습니다.");
			$("#joinSubmit1").prop("disabled", "true");
            return;
        } else {
			$("#alertDiv1").html("");
			duplicationCheck1();
		}
	});
	$("#idSlot2").on("change", function(){
		var idReg = /^[A-za-z0-9]{5,15}/g;
        if( !idReg.test( $("#idSlot2").val() ) ) {
			$("#alertDiv2").css("color","red").html("5~15자의 영문 및 숫자만 사용하실 수 있습니다.");
			$("#joinSubmit2").prop("disabled", "true");
            return;
        } else {
			$("#alertDiv2").html("");
			duplicationCheck2();
		}
	});
	
	
	function duplicationCheck1() {
		var id = $("#idSlot1").val();
		$.ajax({
			type: "POST",
			data: {"user_id":id},
			url: "IdDuplicationCheck",
			success: function(data) {
				if(data == "success") {
					$("#alertDiv1").css("color","green").html("사용 가능한 아이디입니다.");
					$("#joinSubmit1").removeAttr("disabled");
				} else if(data == "fail") {
					$("#alertDiv1").css("color","red").html("사용할 수 없는 아이디입니다.");
					$("#joinSubmit1").attr("disabled", "disabled");
				}
			}
		})
	}
	function duplicationCheck2() {
		var id2 = $("#idSlot2").val();
		$.ajax({
			type: "POST",
			data: {"user_id":id2},
			url: "IdDuplicationCheck",
			success: function(data) {
				if(data == "success") {
					$("#alertDiv2").css("color","green").html("사용 가능한 아이디입니다.");
					$("#joinSubmit2").removeAttr("disabled");
				} else if(data == "fail") {
					$("#alertDiv2").css("color","red").html("사용할 수 없는 아이디입니다.");
					$("#joinSubmit2").attr("disabled", "disabled");
				}
			}
		})
	}
	
});