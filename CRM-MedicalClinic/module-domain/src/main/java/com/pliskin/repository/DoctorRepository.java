package com.pliskin.repository;

import com.pliskin.model.Credentials;
import com.pliskin.model.Doctor;
import com.pliskin.model.Office;
import com.pliskin.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByOffice(Office office);

    Doctor findOneByFio(String fio);

    Doctor findByCredentials(Credentials credentials);

    List<Doctor> findByOfficeAndSpecialization(Office office, Specialization specialization);
}
