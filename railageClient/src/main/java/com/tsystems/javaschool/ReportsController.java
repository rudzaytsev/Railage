package com.tsystems.javaschool;

import com.itextpdf.text.DocumentException;
import com.tsystems.javaschool.exceptions.ErrorResponseException;
import com.tsystems.javaschool.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.BeanValidator;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static final String BEANS_VALIDATION_AVAILABILITY_CACHE_KEY = "javax.faces.BEANS_VALIDATION_AVAILABLE";

    @PostConstruct
    private void init() {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove(BEANS_VALIDATION_AVAILABILITY_CACHE_KEY);
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
                .put(BeanValidator.VALIDATOR_FACTORY_KEY, Validation.buildDefaultValidatorFactory());
    }


    public String createAndRetrieveReport(){

        PostRequestData postRequestData = new PostRequestData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = reportPeriod.getStartDate();
        Date endDate = reportPeriod.getEndDate();
        FacesContext context = FacesContext.getCurrentInstance();
        if(startDate == null || endDate == null){
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("isError",true);
            session.setAttribute("errorMsg","Please select Start and End Date!");
            return "index";
        }
        if(startDate.after(endDate)){
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("isError",true);
            session.setAttribute("errorMsg","Start Date should happens before End Date!");
            return "index";
        }
        else {
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("isError",false);
            session.setAttribute("errorMsg","");
        }

        postRequestData.setFromDate(dateFormat.format(reportPeriod.getStartDate()));
        postRequestData.setToDate(dateFormat.format(reportPeriod.getEndDate()));
        Entity<PostRequestData> jsonReqEntity = Entity.json(postRequestData);


        Response response = ClientBuilder.newClient().target(CREATE_REPORT_URL).
                            request(MediaType.APPLICATION_JSON_TYPE).
                            header("Content-Type", "application/json").
                            buildPost(jsonReqEntity).
                            invoke();

        try {
            byte[] reportAsBytes = this.sendGetRequestForReportData(response);
            FacesUtils.setSessionMapValue(FacesUtils.REPORT_DATA,reportAsBytes);
            FacesUtils.setSessionMapValue(FacesUtils.IS_ERROR,false);
            FacesUtils.setSessionMapValue(FacesUtils.ERROR_MSG,"");
            return "report";
        }
        catch(DocumentException | IOException | ErrorResponseException e){
            FacesUtils.setSessionMapValue(FacesUtils.REPORT_DATA,null);
            FacesUtils.setSessionMapValue(FacesUtils.IS_ERROR,true);
            FacesUtils.setSessionMapValue(FacesUtils.ERROR_MSG,e.getMessage());
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("isError",true);
            session.setAttribute("errorMsg",e.getMessage());
            return "index";
        }
    }

    private byte[] sendGetRequestForReportData(Response response) throws DocumentException, IOException, ErrorResponseException {

        int status = response.getStatus();
        boolean okStatus = (status == 200 || status == 201);
        if(!okStatus){
            System.out.println("************[ERR]*************************");
            System.out.println(status);
            System.out.println(response.readEntity(String.class));
            System.out.println("*************************************");
            throw new ErrorResponseException("Error response occurred for POST. Error code = " + status);
        }
        System.out.println("************[200]*************************");

        PostResponseData servRespData = response.readEntity(PostResponseData.class);
        String url = servRespData.getData();
        System.out.println("url = " + url);
        ReportData reportData = this.sendGetRequest(url);
        ReportPdfMapper mapper = new ReportPdfMapper(reportData);
        byte[] reportAsBytes = mapper.createPdfFile();
        return reportAsBytes;

    }

    private ReportData sendGetRequest(String requestUrl) throws ErrorResponseException {

        Response response = ClientBuilder.newClient().
                target(requestUrl).request().buildGet().invoke();

        int status = response.getStatus();
        if(status != 200) {
            throw new ErrorResponseException("Error response occurred for GET. Error code = " + status);
        }
        GetResponseData responseData = response.readEntity(GetResponseData.class);
        ReportData reportData = responseData.getData();
        System.out.println("************[RESP ANSWER]***********************");
        System.out.println(reportData);
        System.out.println("***********************************");
        return reportData;

    }


}
