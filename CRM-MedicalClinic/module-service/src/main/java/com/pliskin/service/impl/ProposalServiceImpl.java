package com.pliskin.service.impl;

import com.pliskin.model.Proposal;
import com.pliskin.repository.ProposalRepository;
import com.pliskin.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrpliskin on 20.04.16.
 */
@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    ProposalRepository proposalRepository;

    @Secured("isAnonymous()")
    @Override
    public void createProposal(String fio, String email, String message) {
        Proposal proposal = new Proposal();
        proposal.setEmail(email);
        proposal.setFio(fio);
        proposal.setMessage(message);
    }

    @Secured("isAnonymous()")
    @Override
    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }
}
