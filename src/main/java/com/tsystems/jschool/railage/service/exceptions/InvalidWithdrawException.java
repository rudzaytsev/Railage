package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 10.08.15.
 */
public class InvalidWithdrawException extends Exception {

    private String message;

    public InvalidWithdrawException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
