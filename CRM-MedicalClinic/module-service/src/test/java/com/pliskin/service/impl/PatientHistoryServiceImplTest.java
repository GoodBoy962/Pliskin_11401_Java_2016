package com.pliskin.service.impl;

import com.pliskin.service.DoctorService;
import org.junit.BeforeClass;

import static org.mockito.Mockito.mock;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class PatientHistoryServiceImplTest {

    private static PatientHistoryServiceImpl patientHistoryService;

    @BeforeClass
    public static void setUp() {
        patientHistoryService = new PatientHistoryServiceImpl();
        patientHistoryService.doctorService = mock(DoctorService.class);
    }
}
