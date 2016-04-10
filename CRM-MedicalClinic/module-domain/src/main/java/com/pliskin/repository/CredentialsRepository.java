package com.pliskin.repository;

import com.pliskin.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findOneByLogin(String login);

    Credentials findOneByEmail(String email);
}
