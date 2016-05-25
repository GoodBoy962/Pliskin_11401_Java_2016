package com.pliskin.util.transformers;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 24.05.16.
 */
public class PeriodAndDoctorToMapOfDateListOfDoctorSchedulesTransformerTest {

    private PeriodAndDoctorToMapOfDateListOfDoctorSchedulesTransformer transformer;
    private List<DoctorSchedule> doctorSchedules;

    @Before
    public void setUp() {
        transformer = new PeriodAndDoctorToMapOfDateListOfDoctorSchedulesTransformer();
        doctorSchedules = new ArrayList<>();
        transformer.doctorScheduleRepository = mock(DoctorScheduleRepository.class);
        transformer.patientHistoryRepository = mock(PatientHistoryRepository.class);
        when(transformer.patientHistoryRepository
                .findByDateAndDoctorSchedule(any(Date.class), any(DoctorSchedule.class)))
                .thenReturn(doctorSchedules);
        when(transformer.doctorScheduleRepository
                .findByDoctorAndWeekDay(any(Doctor.class), any(WeekDay.class)))
                .thenReturn(doctorSchedules);
    }

    @Test
    public void applyShouldReturnCore() {
        Map<Date, List<DoctorSchedule>> map = transformer.apply("w", new Doctor());
        Assert.assertTrue(map.containsValue(doctorSchedules));
    }

}
