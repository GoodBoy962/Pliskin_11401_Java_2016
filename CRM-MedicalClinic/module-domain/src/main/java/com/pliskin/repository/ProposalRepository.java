package com.pliskin.repository;

import com.pliskin.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrpliskin on 20.04.16.
 */
@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
