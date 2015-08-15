package com.tsystems.javaschool.utils;

import javax.faces.context.FacesContext;

/**
 * Created by rudolph on 14.08.15.
 */
public class FacesUtils {

    public static final String IS_ERROR = "isError";
    public static final String ERROR_MSG = "errorMsg";
    public static final String REPORT_DATA = "pdfReport";


    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

}
