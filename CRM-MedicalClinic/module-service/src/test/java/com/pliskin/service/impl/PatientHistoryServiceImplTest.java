package com.pliskin.service.impl;

import com.itextpdf.text.Document;
import com.pliskin.forms.AppointmentChangeForm;
import com.pliskin.model.*;
import com.pliskin.model.enums.WeekDay;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.PatientService;
import com.pliskin.util.pdf.ReceiptGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class PatientHistoryServiceImplTest {

    private static PatientHistoryServiceImpl patientHistoryService;
    private static Patient patient;
    private static Doctor doctor;
    private static DoctorSchedule doctorSchedule;
    private static Credentials credentials;
    private static List<PatientHistory> patientHistories;
    private static List<DoctorSchedule> doctorSchedules;
    private static PatientHistory patientHistory;
    private static AppointmentChangeForm form;
    private static Document document;

    @BeforeClass
    public static void setUp() {
        patientHistoryService = new PatientHistoryServiceImpl();
        patient = new Patient();
        doctor = new Doctor();
        doctorSchedule = new DoctorSchedule();
        credentials = new Credentials();
        patientHistories = new ArrayList<>();
        doctorSchedules = new ArrayList<>();
        patientHistory = new PatientHistory();
        document = new Document();
        form = new AppointmentChangeForm();
        form.setCost(100);
        form.setDescription("description");
        doctorSchedules.add(doctorSchedule);
        patientHistoryService.doctorService = mock(DoctorService.class);
        patientHistoryService.patientHistoryRepository = mock(PatientHistoryRepository.class);
        patientHistoryService.doctorScheduleRepository = mock(DoctorScheduleRepository.class);
        patientHistoryService.patientService = mock(PatientService.class);
        patientHistoryService.transformer = (Function<String, String>) mock(Function.class);
        patientHistoryService.receiptGenerator = mock(ReceiptGenerator.class);
        when(patientHistoryService.transformer.apply(anyString())).thenReturn("09:00:00/10:00:00");
        when(patientHistoryService.patientService.getPatient()).thenReturn(patient);
        when(patientHistoryService.doctorService.getDoctor(anyString())).thenReturn(doctor);
        when(patientHistoryService.doctorScheduleRepository
                .findByDoctorAndWeekDayAndStartTimeAndEndTime(
                        any(Doctor.class), any(WeekDay.class), any(Time.class), any(Time.class)
                )).thenReturn(doctorSchedule);
        when(patientHistoryService.patientHistoryRepository.save(any(PatientHistory.class))).thenAnswer(
                (Answer<PatientHistory>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (PatientHistory) args[0];
                });
        when(patientHistoryService.patientService.getPatient(credentials)).thenReturn(patient);
        when(patientHistoryService.patientHistoryRepository.findByPatient(patient)).thenReturn(patientHistories);
        when(patientHistoryService.doctorScheduleRepository.findByDoctor(doctor)).thenReturn(doctorSchedules);
        when(patientHistoryService.patientHistoryRepository.findByDoctorSchedule(any(DoctorSchedule.class))).thenReturn(patientHistories);
        when(patientHistoryService.patientHistoryRepository.findOne(1L)).thenReturn(patientHistory);
        when(patientHistoryService.patientHistoryRepository.findByPatient(patient)).thenReturn(patientHistories);
        when(patientHistoryService.patientService.getPatientById(1L)).thenReturn(patient);
        when(patientHistoryService.patientHistoryRepository.save(any(PatientHistory.class))).thenAnswer(
                (Answer<PatientHistory>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (PatientHistory) args[0];
                });
        when(patientHistoryService.receiptGenerator.apply(patientHistory)).thenReturn(document);
    }

    @Test
    public void createHistoryShouldWoCorrect() {
        patientHistoryService.createHistory("fio", "TUESDAY", "09:00:00", "21.04.2016");
    }

    @Test
    public void createHistoryShouldCatchExceptionWhenParamIncorrect() {
        patientHistoryService.createHistory("fio", "TUESDAY", "", "");
    }

    @Test
    public void getHistoriesShouldReturnCorrectHistories() {
        Assert.assertEquals(patientHistoryService.getHistories(credentials), patientHistories);
    }

    @Test
    public void getHistoriesByDoctorShouldReturnCorrectHistories() {
        Assert.assertEquals(patientHistoryService.getHistoriesByDoctor(doctor), patientHistories);
    }

    @Test
    public void getHistoryByIdShouldReturnCorrectHistory() {
        Assert.assertEquals(patientHistoryService.getHistoryById(1L), patientHistory);
    }

    @Test
    public void getHistoriesShouldWorkCorrect() {
        Assert.assertEquals(patientHistoryService.getHistories(1L), patientHistories);
    }

    @Test
    public void createHistoryFromCoolFormShouldWorkCorrect() {
        patientHistoryService.createHistoryFromCoolForm("fio/04-09-2016 TUESDAY 11:00:00");
    }

    @Test
    public void changeAppointmentShouldWorkCorrect() {
        Assert.assertEquals(patientHistoryService.changeAppointment(1L, form), document);
    }
}
