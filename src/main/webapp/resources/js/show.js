/**
 * Created by rudolph on 11.07.15.
 */


function showBuyTicketForm(element){

    hiddenInput = document.getElementById('ride_id_for_ticket');

    idStartIndex = 11;
    rideId = element.id.slice(idStartIndex);

    hiddenInput.value = rideId;

    $.ajax({
        url: "/ajax/stationsbyride",
        type: 'POST',
        dataType: 'json',
        data: "{ \"request\" :  \"stationsByRide\"," +
        "\"rideId\": \"" + rideId + "\" }",
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {

            var insideHtml = '';
            var len = data.length;
            console.log(data);

            var html = '';
            var len = data.length;
            for ( var i = 0; i < len; i++) {
                if( i == 0) {
                    html += '<option selected value="' + data[i].stationId + '">'
                        + data[i].stationName + '</option>';
                }
                else {
                    html += '<option value="' + data[i].stationId + '">'
                        + data[i].stationName + '</option>';
                }
            }

            $('#boardingStationId').html(html);

        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });


    $('#buy_ticket_modal_div').modal('show');

    return false;
}

function showRouteStationListModalWindow(element){

    idStartIndex = 16;
    routeId = element.id.slice(idStartIndex);

    $.ajax({
        url: "/railage/ajax/stationsbyroute",
        type: 'POST',
        dataType: 'json',
        data: "{ \"request\" :  \"stationsByRoute\"," +
        "\"routeId\": \"" + routeId + "\" }",
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {

            var insideHtml = '';
            var len = data.length;
            console.log(data);

            var html = '';
            var len = data.length;
            for ( var i = 0; i < len; i++) {
                html += '<p> #' + (i+1) + ' ' + data[i].stationName + ' ';
            }

            $('#route_stations_list_content').html(html);

        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });

    $('#route_stations_list_modal_div').modal('show');


}
