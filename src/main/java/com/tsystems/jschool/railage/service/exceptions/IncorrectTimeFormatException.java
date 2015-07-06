package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 06.07.15.
 */
public class IncorrectTimeFormatException extends Exception {

    private String errorMsg;

    public IncorrectTimeFormatException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
