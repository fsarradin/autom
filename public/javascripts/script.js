
$(document).ready(function () {

	function isStatus(name) {
		return ['todo', 'in_progress', 'done'].some(function (elem) {
			return elem == name;
		});
	}

	$(".task-column")
		.sortable({
			connectWith: '.task-column',
			handle: '.task-title',
			opacity: 0.60,
			
			receive: function(event, ui) {
				var cls = ui.attr('class').split(' ');
				cls.forEach(function (elem) {
					if (isStatus(elem)) {
						// TODO
					}
				});
			}
		});
	
});
