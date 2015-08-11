package com.tsystems.jschool.railage.view.controllers.rest.messages;

/**
 * Created by rudolph on 11.08.15.
 */
public class CreateReportMessage {

    private String command;

    private String fromDate;

    private String toDate;


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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
