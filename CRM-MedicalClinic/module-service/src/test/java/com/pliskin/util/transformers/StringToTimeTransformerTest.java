package com.pliskin.util.transformers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by aleksandrpliskin on 24.05.16.
 */
public class StringToTimeTransformerTest {

    private StringToTimeTransformer stringToTimeTransformer;

    @Before
    public void setUp() {
        stringToTimeTransformer = new StringToTimeTransformer();
    }

    @Test
    public void applyShouldTransformStringToTimeWithAm() {
        Assert.assertEquals(stringToTimeTransformer.apply("12:00 AM"), "12:00:00/13:00:00");
    }

    @Test
    public void applyShouldTransformStringToTimeWithPm() {
        Assert.assertEquals(stringToTimeTransformer.apply("05:00 PM"), "17:00:00/18:00:00");
    }

    @Test
    public void applyShouldTransformStringToTime() {
        Assert.assertEquals(stringToTimeTransformer.apply("10:00 AM"), "10:00:00/11:00:00");
    }

}
