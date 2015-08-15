package com.tsystems.javaschool;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rudolph on 14.08.15.
 */
public class ReportPdfMapper {

    private ReportData report;

    public ReportPdfMapper(ReportData report) {
        this.report = report;
    }

    public byte[] createPdfFile() throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        this.createPdfContent(document);
        document.close();

        return baos.toByteArray();
    }

    private void createPdfContent(Document document) throws DocumentException {
        document.add(new Paragraph("Hello World!"));
    }
}
