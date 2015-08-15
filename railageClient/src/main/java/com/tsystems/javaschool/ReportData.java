package com.tsystems.javaschool;

/**
 * Created by rudolph on 13.08.15.
 */
public class ReportData {

    private Integer id;

    private String fromDate;

    private String toDate;

    private Long soldTickets;

    private Long totalProfit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
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

    @Override
    public String toString() {
        return "ReportDTO{" +
                "id=" + id +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", soldTickets=" + soldTickets +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
