package com.pliskin.repository;

import com.pliskin.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    Specialization findByName(String name);

}
