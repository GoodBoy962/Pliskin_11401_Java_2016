package test;

import impl.Sword;
import interfaces.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SwordTest {

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
        Sword sword = new Sword();
        Assert.assertTrue(sword.getLifetime() == 15 && sword.getRestAttacksBeforeReload() == 3);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = new Sword();
        weapon.tune();
        Assert.assertEquals("Sword is ready\n" + "Sword is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        Sword sword = new Sword();
        sword.tune();
        Assert.assertEquals(true, sword.isSharpened());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
        Sword sword = new Sword();
        for (int i = 0; i < 30; i++) {
            sword.attack();
        }
        Assert.assertTrue(sword.getLifetime() == 0);
    }

}
