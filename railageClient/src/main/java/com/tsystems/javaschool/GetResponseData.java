package com.tsystems.javaschool;

/**
 * Created by rudolph on 13.08.15.
 */
public class GetResponseData {

    String responseForCommand;
    String message;
    ReportDTO data;

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

    public ReportDTO getData() {
        return data;
    }

    public void setData(ReportDTO data) {
        this.data = data;
    }
}
