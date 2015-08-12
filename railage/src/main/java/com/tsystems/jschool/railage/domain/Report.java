package com.tsystems.jschool.railage.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by rudolph on 11.08.15.
 */
@Entity
@Table(name = "Reports")
public class Report extends DomainObject {


    private Date fromDate;

    private Date toDate;

    private Long soldTickets;

    private Long totalProfit;

    public Report(){
        //does nothing
    }

    public Report(Date fromDate, Date toDate, Long soldTickets, Long totalProfit) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.soldTickets = soldTickets;
        this.totalProfit = totalProfit;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Long soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Long getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Long totalProfit) {
        this.totalProfit = totalProfit;
    }
}
