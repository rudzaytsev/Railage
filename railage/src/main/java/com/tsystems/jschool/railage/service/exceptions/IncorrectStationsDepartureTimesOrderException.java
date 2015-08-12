package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 06.07.15.
 */
public class IncorrectStationsDepartureTimesOrderException extends Exception {

    private String errorMsg;

    public IncorrectStationsDepartureTimesOrderException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
