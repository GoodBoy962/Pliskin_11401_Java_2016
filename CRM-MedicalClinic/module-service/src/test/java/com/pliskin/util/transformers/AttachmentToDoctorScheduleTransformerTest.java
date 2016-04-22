package com.pliskin.util.transformers;


import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class AttachmentToDoctorScheduleTransformerTest {

    private AttachmentToDoctorScheduleTransformer transformer;
    DateFormat formatter;

    @Before
    public void setUp() {
        transformer = new AttachmentToDoctorScheduleTransformer();
        transformer.timeTransformer = mock(StringToTimeTransformer.class);
        String time = "09:00:00/10:00:00";
        formatter = new SimpleDateFormat("HH:mm:ss");
        when(transformer.timeTransformer.apply(any(String.class))).thenReturn(time);
    }

    @Test
    public void transformShouldWorkGood() throws ParseException {
        DoctorSchedule doctorSchedule = transformer.apply("MONDAY-09:00 AM");
        Assert.assertEquals(doctorSchedule.getStartTime(), new Time(formatter.parse("09:00:00").getTime()));
        Assert.assertEquals(doctorSchedule.getEndTime(), new Time(formatter.parse("10:00:00").getTime()));
        Assert.assertEquals(doctorSchedule.getWeekDay(), WeekDay.MONDAY);
    }

}
