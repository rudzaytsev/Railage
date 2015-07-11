/**
 * Created by rudolph on 11.07.15.
 */


function showBuyTicketForm(element){


    hiddenInput = document.getElementById('ride_id_for_ticket');

    idStartIndex = 11;
    rideId = element.id.slice(idStartIndex);

    hiddenInput.value = rideId;
    $('#buy_ticket_modal_div').modal('show');

    return false;
}
