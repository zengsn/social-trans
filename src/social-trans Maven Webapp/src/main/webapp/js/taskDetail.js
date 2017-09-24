

$(function(){
		$('.para').each(function(){
			var text=$(this).text();
			var str = text.substring(0,120);
			$(this).html(str);
	});
});


$(function(){
	$('.context').each(function(){
		var text=$(this).text();
		var str = text.substring(0,400);
		$(this).html(str);
});
});


$(function(){
		$('.para123').each(function(){
			var text=$(this).text();
			var str = text.substring(0,500);
			$(this).html(str);
	});
});
