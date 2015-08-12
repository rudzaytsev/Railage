package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 12.07.15.
 */
public class PassengerAlreadyBookedTicketOnRideException extends  Exception {

    private String errorMsg;

    public PassengerAlreadyBookedTicketOnRideException(String message) {
        super(message);
        this.errorMsg = message;
    }

    @Override
    public String getMessage() {
        return  errorMsg;
    }
}
