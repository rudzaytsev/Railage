package com.tsystems.jschool.railage.view.controllers.rest;

import com.tsystems.jschool.railage.domain.Report;
import com.tsystems.jschool.railage.service.ReportService;
import com.tsystems.jschool.railage.view.controllers.rest.messages.CreateReportMessage;
import com.tsystems.jschool.railage.view.controllers.rest.messages.basic.OkResponseMessage;
import com.tsystems.jschool.railage.view.controllers.rest.messages.basic.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Created by rudolph on 11.08.15.
 */
@RestController
@RequestMapping(value = "/reports")
public class ReportsController {

    public static final String CREATE_REPORT_COMMAND = "createReport";

    public static final String GET_REPORT_COMMAND = "getReport";

    public static final Integer PORT = 8080;

    public static final String SITE_NAME = "railagesite";

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET,
                    produces = "application/json")
    public ResponseEntity<?> getResponse(@PathVariable Integer reportId){

        Report report = reportService.findReportById(reportId);
        if (report != null ){
            ResponseMessage<Report> message = constructOkMessageForReportGetting(report);
            return new ResponseEntity<ResponseMessage<Report>>(message, HttpStatus.OK);
        }
        else {
            ResponseMessage<String> errMsg = constructErrorMessage("Report not found",GET_REPORT_COMMAND);
            return new ResponseEntity(errMsg,HttpStatus.NOT_FOUND);
        }
    }

    private ResponseMessage<Report> constructOkMessageForReportGetting(Report report){
        ResponseMessage<Report> message = new OkResponseMessage<>();
        message.setResponseForCommand(GET_REPORT_COMMAND);
        message.setMessage("found report");
        message.setData(report);
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, headers = {"Content-Type=application/json"},
                    produces = "application/json")
    public ResponseEntity<ResponseMessage<String>> createReport(@RequestBody CreateReportMessage request){

        if(CREATE_REPORT_COMMAND.equals(request.getCommand())){
            Report report = null;
            try {
                report = reportService.createReport(
                                     request.getFromDate(),request.getToDate());
            } catch (ParseException e) {
                ResponseMessage<String> errMsg = constructErrorMessage("Invalid dates syntax",CREATE_REPORT_COMMAND);
                return new ResponseEntity<ResponseMessage<String>>(errMsg,HttpStatus.BAD_REQUEST);
            }
            ResponseMessage<String> message = constructOkMessageForReportCreation(report);
            return new ResponseEntity<ResponseMessage<String>>(message, HttpStatus.OK);
        }
        else {
            ResponseMessage<String> errMsg = constructErrorMessage("No command field",CREATE_REPORT_COMMAND);
            return new ResponseEntity<ResponseMessage<String>>(errMsg,HttpStatus.BAD_REQUEST);
        }

    }

    private String constructUrl(Report report){

        StringBuilder builder = new StringBuilder();
        return builder.append("http://")
                .append(SITE_NAME)
                .append(":")
                .append(PORT)
                .append("/railage/reports/")
                .append(report.getId())
                .toString();
    }

    private ResponseMessage<String> constructErrorMessage(String msgText,String command){

        ResponseMessage<String> message = new OkResponseMessage<>();
        message.setResponseForCommand(command);
        message.setMessage(msgText);
        message.setData(null);
        return message;
    }

    private ResponseMessage<String> constructOkMessageForReportCreation(Report report){

        ResponseMessage<String> message = new OkResponseMessage<>();
        message.setResponseForCommand(CREATE_REPORT_COMMAND);
        message.setMessage("created");
        message.setData(constructUrl(report));
        return message;
    }
}
