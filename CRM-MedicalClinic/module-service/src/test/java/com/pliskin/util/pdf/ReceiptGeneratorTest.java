package com.pliskin.util.pdf;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.Patient;
import com.pliskin.model.PatientHistory;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by aleksandrpliskin on 24.05.16.
 */
public class ReceiptGeneratorTest {

    private ReceiptGenerator receiptGenerator;
    private PatientHistory patientHistory;

    private final String folder = "/Users/aleksandrpliskin/Desktop/static/sem1/pdf/";

    @Before
    public void setUp() {
        receiptGenerator = new ReceiptGenerator();
        receiptGenerator.folder = folder;
        patientHistory = new PatientHistory();
        patientHistory.setCost(100);
        patientHistory.setDate(new Date());
        patientHistory.setDescription("description");
        patientHistory.setId(1L);
        patientHistory.setStatus(Boolean.FALSE);
        Doctor doctor = new Doctor();
        doctor.setFio("FIO");
        Patient patient = new Patient();
        patient.setFio("fio");
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDoctor(doctor);
        patientHistory.setDoctorSchedule(doctorSchedule);
        patientHistory.setPatient(patient);
    }

    @Test
    public void test() {
        receiptGenerator.apply(patientHistory);
    }

}
