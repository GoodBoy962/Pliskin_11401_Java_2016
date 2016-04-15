package com.pliskin.repository;

import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    List<Office> findByMedicalClinic(MedicalClinic medicalClinic);

    Office findByAddress(String address);

    List<Office> findByCity(String city);

    Office findOneByCityAndAddress(String city, String address);
}
