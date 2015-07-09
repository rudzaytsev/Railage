package com.tsystems.jschool.railage.service.exceptions;

/**
 * Created by rudolph on 09.07.15.
 */
public class TimeTableConflictException extends Exception {

    private String errorMsg;

    public TimeTableConflictException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
