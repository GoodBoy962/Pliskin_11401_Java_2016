package com.pliskin.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pliskin.model.PatientHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 24.04.16.
 */
@Component
public class ReceiptGenerator implements Function<PatientHistory, Document> {

    @Value("${pdf.folder}")
    private String folder;

    private Font font;

    @Override
    public Document apply(PatientHistory patientHistory) {
        try {
            return create(patientHistory);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document create(PatientHistory patientHistory) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        document.addAuthor(patientHistory.getDoctorSchedule().getDoctor().getFio());
        document.addTitle("appointment number " + patientHistory.getId());
        BaseFont helvetica = BaseFont.createFont("fonts/arial.ttf", "cp1251", BaseFont.EMBEDDED);
        font = new Font(helvetica, 10, Font.NORMAL);
        PdfWriter.getInstance(document,
                new FileOutputStream(
                        folder + patientHistory.getId() + '_' + patientHistory.getPatient().getFio() + ".pdf")
        );
        document.open();

        Paragraph paragraph = new Paragraph(Element.ALIGN_RIGHT, "Приём номер " + patientHistory.getId(), font);
        PdfPTable table = new PdfPTable(3);
        addTableLine(table, "Доктор", patientHistory.getDoctorSchedule().getDoctor().getFio());
        addTableLine(table, "Пациент", patientHistory.getPatient().getFio());
        addTableLine(table, "Описание", patientHistory.getDescription());
        addTableLine(table, "Цена", String.valueOf(patientHistory.getCost()));
        addTableLine(table, "Дата", String.valueOf(dateFormat.format(patientHistory.getDate())));
        addTableLine(table, "Время", String.valueOf(patientHistory.getDoctorSchedule().getStartTime()));
        document.add(paragraph);
        addEmptyParagraph(document);
        document.add(table);
        Paragraph signature = new Paragraph("подпись", font);
        addEmptyParagraph(document);
        document.add(signature);
        document.close();
        return document;
    }

    private void addTableLine(PdfPTable table, String name, String info) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(name, font));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(info, font));
        cell.setColspan(2);
        table.addCell(cell);
    }

    private static void addEmptyParagraph(Document document) throws DocumentException {
        document.add(new Paragraph("            "));
    }
}
