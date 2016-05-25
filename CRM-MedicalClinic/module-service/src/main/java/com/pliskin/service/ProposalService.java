package com.pliskin.service;

import com.pliskin.model.Proposal;

import java.util.List;

/**
 * Created by aleksandrpliskin on 20.04.16.
 */
public interface ProposalService {

    void createProposal(String fio, String email, String message);

    List<Proposal> getAll();
}
