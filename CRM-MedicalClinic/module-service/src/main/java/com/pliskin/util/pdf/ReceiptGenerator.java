package com.pliskin.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pliskin.model.PatientHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        try {
            PdfWriter.getInstance(document, new FileOutputStream(folder + patientHistory.getId() + '_' + patientHistory.getPatient().getFio() + ".pdf"));
            document.open();
            Paragraph paragraph = new Paragraph(patientHistory.getPatient().getFio());
            Paragraph paragraph1 = new Paragraph(String.valueOf(patientHistory.getPatient().getBirthDay()));
            Paragraph paragraph2 = new Paragraph(patientHistory.getDescription());
            Paragraph paragraph3 = new Paragraph(patientHistory.getDate().toString());
            Paragraph paragraph4 = new Paragraph(String.valueOf(patientHistory.getDoctorSchedule().getStartTime()));
            Paragraph paragraph5 = new Paragraph(patientHistory.getCost());
            document.add(paragraph);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph5);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
        return document;
    }

}
