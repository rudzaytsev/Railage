package com.tsystems.javaschool;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by rudolph on 12.08.15.
 */
@Named
@SessionScoped
public class ReportsController implements Serializable {

    public static final String CREATE_REPORT_URL = "http://railagesite:8080/railage/reports";


    private ReportPeriod reportPeriod = new ReportPeriod();

    public ReportPeriod getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(ReportPeriod reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String createAndRetrieveReport(){

        PostRequestData postRequestData = new PostRequestData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        postRequestData.setFromDate(dateFormat.format(reportPeriod.getStartDate()));
        postRequestData.setToDate(dateFormat.format(reportPeriod.getEndDate()));
        Entity<PostRequestData> jsonReqEntity = Entity.json(postRequestData);


        Response response = ClientBuilder.newClient().
                                target(CREATE_REPORT_URL).
                request(MediaType.APPLICATION_JSON_TYPE).
                header("Content-Type", "application/json").
                buildPost(jsonReqEntity).
                invoke();

        int status = response.getStatus();
        if(status == 200 || status == 201){
            Object res = response.getEntity();
            System.out.println("************[200]*************************");

            PostResponseData servRespData = response.readEntity(PostResponseData.class);
            String url = servRespData.getData();
            System.out.println("url = " + url);
            ReportDTO rep = this.sendGetRequest(url);
        }
        else {
            Object res2 = response.getEntity();
            System.out.println("************[ERR]*************************");
            System.out.println(status);
            System.out.println(response.readEntity(String.class));

            System.out.println("*************************************");
        }
        return "show";
    }

    private ReportDTO sendGetRequest(String requestUrl){

        Response response = ClientBuilder.newClient().
                target(requestUrl).request().buildGet().invoke();

        int status = response.getStatus();
        if(status == 200){
            GetResponseData responseData = response.readEntity(GetResponseData.class);
            ReportDTO reportDTO = responseData.getData();
            System.out.println("************[RESP ANSWER]***********************");
            System.out.println(reportDTO);
            System.out.println("***********************************");
            return reportDTO;
        }
        return null;
    }
}
