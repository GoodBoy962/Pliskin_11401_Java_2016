package com.pliskin.repository;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.Patient;
import com.pliskin.model.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {

    Object findByDateAndDoctorSchedule(Date date, DoctorSchedule doctorSchedule);

    List<PatientHistory> findByPatient(Patient patient);

    List<PatientHistory> findByDoctorSchedule(DoctorSchedule doctorSchedule);
}
