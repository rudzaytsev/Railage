package com.tsystems.javaschool;

import javax.inject.Named;
import java.util.Date;


/**
 * Created by rudolph on 12.08.15.
 */
@Named
public class ReportPeriod {

    Date startDate;
    Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}