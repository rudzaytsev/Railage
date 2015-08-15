package com.tsystems.javaschool;

/**
 * Created by rudolph on 13.08.15.
 */
public class GetResponseData {

    String responseForCommand;
    String message;
    ReportData data;

    public String getResponseForCommand() {
        return responseForCommand;
    }

    public void setResponseForCommand(String responseForCommand) {
        this.responseForCommand = responseForCommand;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReportData getData() {
        return data;
    }

    public void setData(ReportData data) {
        this.data = data;
    }
}
