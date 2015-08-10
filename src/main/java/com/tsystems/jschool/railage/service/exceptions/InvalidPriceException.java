package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 10.08.15.
 */
public class InvalidPriceException extends Exception {

    private String message;

    public InvalidPriceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
