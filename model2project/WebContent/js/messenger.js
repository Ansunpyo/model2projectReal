$(function(){
	$('.messagePopupWindow').on('click', function(e){
		e.preventDefault();
		$(this).parents().siblings('.messagePopup').fadeIn();
	});
	$('.messagePopup .close button').on('click', function(){
		$('.messagePopup').fadeOut();
	});
});