
$(document).ready(function () {

	function isStatus(name) {
		return ['todo', 'in_progress', 'done'].some(function (elem) {
			return elem == name;
		});
	}
	
	function toStatusRepr(status) {
		return status.toUpperCase().replace('_', ' ');
	}

	function toStatusId(status) {
		return status.toUpperCase().replace(' ', '_');
	}

    function updateTask(taskId, statusId) {
        $.ajax({
            url: window.location + '/' + taskId,
            type: 'POST',
            data: "status=" + statusId,
        });
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
				var taskId = ui.item.find(".taskId").text();
				var status = ui.item.find(".status").text();
                updateTask(taskId, toStatusId(status));
			}
		});

	    $(".popup-wrapper").each(function () {
	        var popup = $(this).find(".popup");

	        $(this).find(".popup-mark").click(function () {
	            var display = popup.css("display");
                if (display == "none") {
                    popup.css("display", "block");
                } else {
                    popup.css("display", "none");
                }
	        });

	        popup.mouseleave(function () {
	            $(this).css('display', 'none');
	        });
	    });

});
