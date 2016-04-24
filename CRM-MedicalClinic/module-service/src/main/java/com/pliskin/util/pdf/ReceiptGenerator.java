package com.pliskin.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pliskin.model.PatientHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by aleksandrpliskin on 24.04.16.
 */
@Component
public class ReceiptGenerator {

    @Value("${pdf.folder}")
    private String folder;

    public Document create(PatientHistory patientHistory) {
        Document document = new Document(PageSize.A4);
        document.addAuthor(patientHistory.getDoctorSchedule().getDoctor().getFio());
        document.addTitle("appointment number " + patientHistory.getId());
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(
                            folder + patientHistory.getId() + '_' + patientHistory.getPatient().getFio() + ".pdf")
            );
            document.open();

            Paragraph paragraph = new Paragraph("Приём номер " + patientHistory.getId());
            float[] columnWidths = {1.5f, 2f, 5f, 2f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(90f);

            insertCell(table, "Доктор", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Пациент", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Описание", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Цена", Element.ALIGN_RIGHT, 1, bfBold12);
            table.setHeaderRows(1);
            insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
            double orderTotal = 0;

            insertCell(table, patientHistory.getDoctorSchedule().getDoctor().getFio(), Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, patientHistory.getPatient().getFio(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, patientHistory.getDescription(), Element.ALIGN_LEFT, 1, bf12);
            orderTotal = patientHistory.getCost();
            insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);

            //merge the cells to create a footer for that section
            insertCell(table, "Дата", Element.ALIGN_RIGHT, 3, bfBold12);
            insertCell(table, timeFormatter.format(patientHistory.getDoctorSchedule().getStartTime()), Element.ALIGN_RIGHT, 1, bfBold12);

            document.add(paragraph);
            document.add(table);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
        return document;
    }


    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table.addCell(cell);
    }

}
