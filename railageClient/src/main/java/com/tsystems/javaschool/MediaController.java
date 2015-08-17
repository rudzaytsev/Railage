package com.tsystems.javaschool;

import com.itextpdf.text.DocumentException;
import com.tsystems.javaschool.utils.FacesUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by rudolph on 14.08.15.
 */
@Named
@ViewScoped
public class MediaController implements Serializable {

    private StreamedContent mediaPdfReport;

    private boolean isError;

    private String errorMsg;

    private static org.apache.log4j.Logger logger =
            org.apache.log4j.Logger.getLogger(MediaController.class);

    @PostConstruct
    public void postConstruct() throws DocumentException {

       logger.debug("Invoked postConstruct()");
       byte[] reportAsBytes = (byte[]) FacesUtils.getSessionMapValue(FacesUtils.REPORT_DATA);
       this.isError = (Boolean) FacesUtils.getSessionMapValue(FacesUtils.IS_ERROR);
       this.errorMsg =  (String) FacesUtils.getSessionMapValue(FacesUtils.ERROR_MSG);
       if(reportAsBytes == null){
           return;
       }
       InputStream stream = new ByteArrayInputStream(reportAsBytes);
       mediaPdfReport = new DefaultStreamedContent(stream,"application/pdf");
    }



    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public StreamedContent getMediaPdfReport() {
        return mediaPdfReport;
    }

    public void setMediaPdfReport(StreamedContent mediaPdfReport) {
        this.mediaPdfReport = mediaPdfReport;
    }
}
