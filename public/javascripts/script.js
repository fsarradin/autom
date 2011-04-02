
$(document).ready(function () {
	$(".task").draggable({
		containment: "#task-container",
		grid: [10, 10],
		opacity: 0.70
	});
	
	function taskContainerOptionsForStatus(status) {
		return {
			accept: '.task',
			hoverClass: 'over-highlight',
			drop: function(event, ui) {
				var classes = ui.draggable.attr("class").split();
				for (cls in classes) {
					if (cls != "task") {
						ui.draggable.removeClass(cls);
					}
				}
				ui.draggable.addClass(status.toLowerCase());
				ui.draggable.find(".status").text(status);
			}
		};
	}
	
	$("#todo-container").droppable(taskContainerOptionsForStatus("TODO"));
	$("#in_progress-container").droppable(taskContainerOptionsForStatus("IN_PROGRESS"));
	$("#done-container").droppable(taskContainerOptionsForStatus("DONE"));
});
