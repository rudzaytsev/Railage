package com.tsystems.javaschool;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rudolph on 14.08.15.
 */
public class ReportPdfMapper {

    private static org.apache.log4j.Logger logger =
            org.apache.log4j.Logger.getLogger(ReportPdfMapper.class);

    private ReportData report;

    public ReportPdfMapper(ReportData report) {
        this.report = report;
    }

    public byte[] createPdfFile() throws DocumentException, IOException {

        logger.info("Invoke createPdfFile()");

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        this.createPdfContent(document);
        document.close();

        logger.info("Created report as pdf file in memory!");

        return baos.toByteArray();
    }

    private void createPdfContent(Document document) throws DocumentException {

        logger.debug("Invoke createPdfContent");
        //create header
        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 32,
                                   Font.BOLD | Font.UNDERLINE);

        Paragraph header = new Paragraph("Ticket Sales Report # " + report.getId(),headerFont);

        header.setAlignment(Element.ALIGN_CENTER);
        header.setIndentationLeft(20);
        header.setIndentationRight(20);
        header.setSpacingBefore(10);
        header.setSpacingAfter(35);
        document.add(header);

        //create table
        Font tableBodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 22);

        PdfPTable table = new PdfPTable(2); // 2 columns.
        Collection<PdfPCell> cells = new ArrayList<PdfPCell>();

        cells.add(new PdfPCell(new Paragraph("Start date",tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph(report.getFromDate(),tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph("End date",tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph(report.getToDate(),tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph("Sold Tickets",tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph(report.getSoldTickets() + "",tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph("Total Profit",tableBodyFont)));
        cells.add(new PdfPCell(new Paragraph(report.getTotalProfit() + " $",tableBodyFont)));

        for(PdfPCell currentCell : cells){
            currentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(currentCell);
        }
        document.add(table);

    }
}
