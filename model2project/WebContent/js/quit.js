$(function(){
	$("#quitButton").click(function(){
		var quitResult = confirm('정말 탈퇴하시겠습니까?');
		
		if(quitResult) {
			location.replace('quit.do');
		} else {
			
		}
	});
});