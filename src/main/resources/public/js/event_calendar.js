let calendar = null;

function editEvent(event) {
    $('#event-modal input[name="event-index"]').val(event ? event.id : '');
    $('#event-modal input[name="event-name"]').val(event ? event.nombre : '');
    $('#event-modal input[name="event-location"]').val(event ? event.lugar : '');
    $('#event-modal input[name="event-start-date"]').datepicker('update', event ? event.startDate : '');
    $('#event-modal input[name="event-end-date"]').datepicker('update', event ? event.endDate : '');
    $('#event-modal').modal();
}

function ocultarAllPopovers() {
   $('.popover .popover-add-event, .popover .popover-update-event, .popover .popover-close').off('click');
   $('[data-toggle="popover"]').popover('hide').removeAttr("data-toggle");
}


$(function() {
    $(window).click(function(e){ 
      if(! $(e.target).closest('.popover').length) { 
        ocultarAllPopovers(); 
      } 
    });

    calendar = new Calendar('#calendar', { 
        enableRangeSelection: true,
        selectRange: function(e) {
            if(e.startDate != e.endDate) {
              editEvent({ startDate: e.startDate, endDate: e.endDate });
            }
        },
        clickDay: function(e) {
            if(e.events.length > 0) {
                ocultarAllPopovers();
                var content = '';

                for(var i in e.events) {
                  let event = e.events[i];
                    content += '<div id="pop-'+event.id+'" class="event-tooltip-content popover-update-event" style="background-color:'+ e.events[i].color +';">'
                                    + '<div class="event-name">' + e.events[i].name + '</div>'
                                    + '<div class="event-location">' + e.events[i].location + '</div>'
                                + '</div>';
                }

                content += '<span class="popover-add-event">+<span>';
            
                $(e.element).popover({ 
                    placement: 'top',
                    trigger: 'manual',
                    container: 'body',
                    title: '<div class="text-center">'+e.date.toLocaleDateString("ES", {day:"2-digit", month:"2-digit"})+'</div><span class="popover-close">&times;</span>',
                    html:true,
                    content: content
                });


                $(e.element).attr("data-toggle","popover");
                $(e.element).popover('show');


                let popover = $('.popover');
                popover.find('.popover-add-event').click(function() {
                  ocultarAllPopovers(); 
                  editEvent({ startDate: e.date, endDate: e.date }); 
                });

                popover.find('.popover-close').click(function() {
                  ocultarAllPopovers();
                });

                for(var i in e.events) {
                  let event = e.events[i];
                  let eventElement = popover.find("#pop-"+event.id);
                  eventElement.css("background-color", event.color);

                  eventElement.click(function(e) {
                    e.stopPropagation();
                    ocultarAllPopovers();
                    // window.location.href='/event/id';
                  });
                }
            }
            else {
              ocultarAllPopovers();
              editEvent({ startDate: e.date, endDate: e.date });
            }
        },
        dataSource: eventosCalendario
    });
    
    $('#guardar').click(function() {
        $("#nuevo-event-form").submit();
    });
});