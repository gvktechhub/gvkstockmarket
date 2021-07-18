$(function(){
	
});

function reload() {
  	location.reload();
}

function applyColor(tableElement, targetElement) {
	$(tableElement).each(function() {
		var row = $(this);
		var element = row.find(targetElement);
		row.find(targetElement).addClass(parseFloat(element.text()) < 0 ? 'highlate-text text-danger' : 'highlate-text text-success');
	});
}