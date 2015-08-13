package com.tsystems.javaschool;

/**
 * Created by rudolph on 13.08.15.
 */
public class PostRequestData {

    private String command = "createReport";

    private String fromDate;

    private String toDate;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
