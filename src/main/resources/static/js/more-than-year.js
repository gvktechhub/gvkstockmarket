$(function(){
	var tableElement = '#more-than-year-stocks-table tbody tr';
	var upcomingTableElement = '#upcoming-more-than-year-stocks-table tbody tr';
	
	applyColor(tableElement, '.profit_or_loss_amount');
	applyColor(tableElement, '.profit_or_loss_percent');
	calculateTotalsInMTYPage(tableElement, '.profit_or_loss_amount', 
			'#more-than-year-profit-amount', '#more-than-year-loss-amount');
	
	applyColor(upcomingTableElement, '.profit_or_loss_amount');
	applyColor(upcomingTableElement, '.profit_or_loss_percent');
	calculateTotalsInMTYPage(upcomingTableElement, '.profit_or_loss_amount', 
			'#upcoming-more-than-year-profit-amount', '#upcoming-more-than-year-loss-amount');
});

function calculateTotalsInMTYPage(tableElement, sourceElement, profitElement, lossElement) {
	var profit_total_value = 0;
	var loss_total_value = 0;
	$(tableElement).each(function() {
		var row = $(this);
		var element = row.find(sourceElement);
		 var element_value = parseFloat(element.text());
		 element_value > 0 ? profit_total_value += element_value : loss_total_value += element_value;
	});
	$(profitElement).text(profit_total_value);
	$(lossElement).text(loss_total_value);
}