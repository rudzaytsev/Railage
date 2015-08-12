package com.tsystems.jschool.railage.service;

import com.tsystems.jschool.railage.datasource.interfaces.ReportDao;
import com.tsystems.jschool.railage.datasource.interfaces.TicketDao;
import com.tsystems.jschool.railage.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by rudolph on 11.08.15.
 */
@Service
@Transactional(readOnly = true)
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private TicketDao ticketDao;


    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public Report createReport(String fromDateStr, String toDateStr) throws ParseException {
        Date fromDate = this.validateDate(fromDateStr);
        Date toDate = this.validateDate(toDateStr);

        Long totalTickets = ticketDao.countTotalTickets(fromDate, toDate);
        Long totalProfit = ticketDao.calcTotalProfit(fromDate,toDate);
        Report report = new Report(fromDate,toDate,totalTickets,totalProfit);
        return reportDao.merge(report);
    }

    private Date validateDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dateFormat.parse(dateStr); // for validation
        return Date.valueOf(dateStr);
    }

    public Report findReportById(Integer id){
        return reportDao.findById(id);
    }




}
