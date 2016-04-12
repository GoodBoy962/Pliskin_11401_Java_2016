package com.pliskin.repository;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    List<DoctorSchedule> findByDoctor(Doctor doctor);
}
