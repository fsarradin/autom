
$(document).ready(function () {

	function isStatus(name) {
		return ['todo', 'in_progress', 'done'].some(function (elem) {
			return elem == name;
		});
	}
	
	function toStatusRepr(status) {
		return status.toUpperCase().replace('_', ' ');
	}

	$(".task-column")
		.sortable({
			connectWith: '.task-column',
			handle: '.task-title',
			opacity: 0.60,
			
			receive: function(event, ui) {
				var cls = ui.item.attr('class').split(' ');
				cls.forEach(function (elem) {
					if (isStatus(elem)) {
						ui.item.removeClass(elem)
					}
				});
				var thisCls = $(this).attr('class').split(' ');
				thisCls.forEach(function (elem) {
					if (isStatus(elem)) {
						ui.item.addClass(elem)
						var status = toStatusRepr(elem);
						ui.item.find('.status').text(status);
					}
				});
			}
		});
	
});
