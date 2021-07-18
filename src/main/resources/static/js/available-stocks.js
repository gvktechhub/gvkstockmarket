$(function() {

	applyColor('#available-stocks-table tbody tr', '.pandl');
	calculateTotalValue();

	$(document).delegate(
			'.updateCurrentPrice',
			'focusout',
			function() {
				var element = $(this);
				var row = element.parent().parent();
				$.ajax({
					type : 'PATCH',
					url : contextPath
							+ 'stock-names/updateCurrentPriceByStockName',
					data : {
						stockNameId : row.find('.stocknameid').val(),
						currentPrice : element.val(),
						quantity: row.find('.quantity').text(),
						avgprice: row.find('.avgprice').text()
					},
					success : function(data) {

					},
					error : function(e) {

					}
				});
			});
});

function calculateTotalValue() {
	var current_total_value = 0;
	$('#available-stocks-table tbody tr').each(function() {
		var row = $(this);
		var element = row.find('.current-value');
		current_total_value += parseFloat(element.text().split(",").join(""));
	});
	$('#current-total-amount').text(current_total_value);
}