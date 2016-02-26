package impl;

import interfaces.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ClutchesTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void clutchesShouldBeGoodInit() {
        Clutches clutches = new Clutches();
        Assert.assertTrue(clutches.getLifetime() == 20 && clutches.getRestAttacksBeforeReload() == 5);
    }

    @Test
    public void tuneIsOk() {
        Weapon clutches = new Clutches();
        clutches.tune();
        Assert.assertEquals("Clutches are ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        Clutches clutches = new Clutches();
        clutches.tune();
        Assert.assertEquals(true, clutches.isSharpened());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
        Clutches clutches = new Clutches();
        for (int i = 0; i < 30; i++) {
            clutches.attack();
        }
        Assert.assertTrue(clutches.getLifetime() == 0);
    }


}
