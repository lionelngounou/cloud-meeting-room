if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function togglePrimaryRadio(checked, id){
	$('#'+id).attr('disabled', !checked);
	$('#'+id).attr('checked', false);		
}