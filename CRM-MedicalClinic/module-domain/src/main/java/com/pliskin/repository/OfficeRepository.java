package com.pliskin.repository;

import com.pliskin.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
