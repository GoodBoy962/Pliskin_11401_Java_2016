package com.pliskin;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Vector2DTest {

    private static final double EPS = 1e-9;

    private static Vector2D v;
    private static Vector2D vDefault;
    private static ApplicationContext context;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        v = (Vector2D) context.getBean("vector");
        vDefault = (Vector2D) context.getBean("defaultVector");
    }

    @Test
    public void defaultVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, vDefault.length(), EPS);
    }

    @Test
    public void vectorShouldHaveNotZeroLength() {
        Assert.assertFalse(v.length() == (double) 0);
    }

    @Test
    public void firstParameterShouldBeSavedInX() {
//        Vector2D v = new Vector2D(3, 4);
        Vector2D v = (Vector2D) context.getBean("vector");
        Assert.assertEquals(3, v.getX(), EPS);
    }

    @Test
    public void secondParameterShouldBeSavedInY() {
//        Vector2D v = new Vector2D(3, 4);
        Vector2D v = (Vector2D) context.getBean("vector");
        Assert.assertEquals(4, v.getY(), EPS);
    }

    @Test
    public void afterAddingVectorCoordinatesShouldBeGood() {
//        Vector2D v1 = new Vector2D(1, 1);
        Vector2D v1 = (Vector2D) context.getBean("vector1");
        v1.addVector(v);
        Assert.assertTrue(v1.getX() == 4 && v1.getY() == 5);
    }

}
