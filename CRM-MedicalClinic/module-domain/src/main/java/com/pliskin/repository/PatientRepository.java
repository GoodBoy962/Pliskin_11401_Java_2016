package com.pliskin.repository;

import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByCredentials(Credentials credentials);

}
