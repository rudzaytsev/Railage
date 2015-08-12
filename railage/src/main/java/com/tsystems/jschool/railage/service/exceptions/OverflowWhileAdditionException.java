package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 10.08.15.
 */
public class OverflowWhileAdditionException extends Exception {

    private String message;

    public OverflowWhileAdditionException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
