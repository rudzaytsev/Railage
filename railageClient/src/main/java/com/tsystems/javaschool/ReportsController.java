package com.tsystems.javaschool;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

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
        postRequestData.setFromDate(reportPeriod.getStartDate().toString());
        postRequestData.setToDate(reportPeriod.getEndDate().toString());
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
            System.out.println(res);
            System.out.println("*************************************");
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
}
