package com.pliskin.service;

import com.itextpdf.text.Document;
import com.pliskin.forms.AppointmentChangeForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Doctor;
import com.pliskin.model.PatientHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by aleksandrpliskin on 14.04.16.
 */
public interface PatientHistoryService {
    void createHistory(String fio, String wDay, String time, String date);

    List<PatientHistory> getHistories(Credentials credentials);

    void createHistoryFromCoolForm(String info);

    @Query("select PatientHistory pth where pth.doctorSchedule ")
    List<PatientHistory> getHistoriesByDoctor(@Param("doctor") Doctor doctor);

    PatientHistory getHistoryById(Long id);

    Document changeAppointment(Long id, AppointmentChangeForm form);

    List<PatientHistory> getHistories(Long id);
}
