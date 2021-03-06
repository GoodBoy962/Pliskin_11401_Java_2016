package com.pliskin.repository;

import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByFio(String fio);

    Admin findOneByCredentials(Credentials credentials);
}
