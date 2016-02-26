package impl;

import interfaces.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FireBallsStrokerTest {

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
        FireBallsStroker fireBallsStroker = new FireBallsStroker();
        Assert.assertTrue(fireBallsStroker.getLifetime() == 9 && fireBallsStroker.getRestAttacksBeforeReload() == 3);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = new FireBallsStroker();
        weapon.tune();
        Assert.assertEquals("Fire balls stroker is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        FireBallsStroker fireBallsStroker = new FireBallsStroker();
        fireBallsStroker.tune();
        Assert.assertEquals(true, fireBallsStroker.isLoaded());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
        FireBallsStroker fireBallsStroker = new FireBallsStroker();
        for (int i = 0; i < 30; i++) {
            fireBallsStroker.attack();
        }
        Assert.assertTrue(fireBallsStroker.getLifetime() == 0);
    }

}
