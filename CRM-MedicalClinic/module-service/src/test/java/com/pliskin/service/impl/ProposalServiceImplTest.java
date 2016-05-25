package com.pliskin.service.impl;

import com.pliskin.model.Proposal;
import com.pliskin.repository.ProposalRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class ProposalServiceImplTest {

    private static ProposalServiceImpl proposalService;
    private static List<Proposal> proposals;

    @BeforeClass
    public static void setUp() {
        proposalService = new ProposalServiceImpl();
        proposalService.proposalRepository = mock(ProposalRepository.class);
        when(proposalService.proposalRepository.save(any(Proposal.class))).thenAnswer(
                (Answer<Proposal>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (Proposal) args[0];
                });
        when(proposalService.proposalRepository.findAll()).thenReturn(proposals);
    }

    @Test
    public void createProposalShouldCreateCorrectProposal() {
        proposalService.createProposal("fio", "email", "message");
    }

    @Test
    public void getAllShouldReturnCorrectProposals() {
        Assert.assertEquals(proposals, proposalService.getAll());
    }

}
