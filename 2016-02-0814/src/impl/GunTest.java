package impl;

import interfaces.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GunTest {

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
        Gun gun = new Gun();
        Assert.assertTrue(gun.getLifetime() == 20 && gun.getRestAttacksBeforeReload() == 5);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = new Gun();
        weapon.tune();
        Assert.assertEquals("gun is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        Gun gun = new Gun();
        gun.tune();
        Assert.assertEquals(true, gun.isLoaded());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
        Gun gun = new Gun();
        for (int i = 0; i < 30; i++) {
            gun.attack();
        }
        Assert.assertTrue(gun.getLifetime() == 0);
    }

}
