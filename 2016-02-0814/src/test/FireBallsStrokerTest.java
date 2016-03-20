package test;

import impl.FireBallsStroker;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FireBallsStrokerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static ApplicationContext context;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
    }


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
    public void fireBallsStrokerShouldBeGoodInit() {
        FireBallsStroker fireBallsStroker = context.getBean(FireBallsStroker.class);
        Assert.assertEquals(fireBallsStroker.getLifetime(), 9);
//                && fireBallsStroker.getRestAttacksBeforeReload() == 3);
        Assert.assertEquals(fireBallsStroker.getRestAttacksBeforeReload(), 3);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = context.getBean(FireBallsStroker.class);
        weapon.tune();
        Assert.assertEquals("Fire balls stroker is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        FireBallsStroker fireBallsStroker = context.getBean(FireBallsStroker.class);
        fireBallsStroker.tune();
        Assert.assertEquals(true, fireBallsStroker.isLoaded());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
        FireBallsStroker fireBallsStroker = context.getBean(FireBallsStroker.class);
        for (int i = 0; i < 30; i++) {
            fireBallsStroker.attack();
        }
        Assert.assertTrue(fireBallsStroker.getLifetime() == 0);
    }

}
