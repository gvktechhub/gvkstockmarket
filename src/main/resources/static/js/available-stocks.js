$(function() {

	applyColorOnPandL();
	calculateTotalValue();

	$(document).delegate(
			'.updateCurrentPrice',
			'focusout',
			function() {
				var element = $(this);
				$.ajax({
					type : 'PATCH',
					url : contextPath
							+ 'stock-names/updateCurrentPriceByStockName',
					data : {
						stockNameId : element.parent().parent().find(
								'.stocknameid').val(),
						currentPrice : element.val()
					},
					success : function(data) {

					},
					error : function(e) {

					}
				});
			});
});

function applyColorOnPandL() {
	$('#available-stocks-table tbody tr').each(function() {
		var row = $(this);
		var element = row.find('.pandl');
		if (parseFloat(element.text()) < 0) {
			element.css({
				color : 'red'
			});
		} else {
			element.css({
				color : 'green'
			});
		}
	});
}

function calculateTotalValue() {
	var current_total_value = 0;
	$('#available-stocks-table tbody tr').each(function() {
		var row = $(this);
		var element = row.find('.current-value');
		console.log(current_total_value);
		current_total_value += parseFloat(element.text().split(",").join(""));
	});
	$('#current-total-amount').text(current_total_value);
}