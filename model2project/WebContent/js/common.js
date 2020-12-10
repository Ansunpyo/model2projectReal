$(function(){
	// menubar
	$('#header .nav > ul > li > ul').hide();
	$('#header .nav > ul > li > a ').on('mouseenter', function(){
		$(this).addClass('on')
			.next().stop().slideDown();
	});
	$('#header .nav > ul > li').on('mouseleave', function(){
		$(this).children('a').removeClass('on')
			.next().stop().slideUp(200);
	});

	// slider
	var $images = 2;
	var $now = 0;
	var $height = $('.slider ul li').height();
	setInterval(function(){
		if($now == $images) {
			$now = 0;
			$('.slider ul').animate({
				top: $now * -$height
			});
		}
		else {
			$now += 1; 
			$('.slider ul').animate({
				top: $now * -$height
			});
		}
	}, 2500);

	// tab
	$('#container .notice h4').on('click', function(){
		$(this)
			.addClass('on')
				.next()
					.show();
		$(this)
			.parent('div').siblings('div').children('h4')
			.removeClass('on')
				.next()
					.hide();
	});

	// popup
	$('.popupWindow').on('click', function(e){
		e.preventDefault();
		$('.popup').fadeIn();
	});
	$('.popup .close button').on('click', function(){
		$('.popup').fadeOut();
	});
});