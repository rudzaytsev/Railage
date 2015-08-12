package com.tsystems.javaschool;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by rudolph on 12.08.15.
 */
@Named
@RequestScoped
public class ReportsController {

    private ReportPeriod reportPeriod;

    public ReportPeriod getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(ReportPeriod reportPeriod) {
        this.reportPeriod = reportPeriod;
    }
}
