package test;

import impl.Clutches;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ClutchesTest {

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
    public void clutchesShouldBeGoodInit() {
//        Clutches clutches = new Clutches();
        Clutches clutches = (Clutches) context.getBean("clutches");
        Assert.assertTrue(clutches.getLifetime() == 20 && clutches.getRestAttacksBeforeReload() == 5);
    }

    @Test
    public void tuneIsOk() {
//        Weapon clutches = new Clutches();
        Weapon clutches = (Clutches) context.getBean("clutches");
        clutches.tune();
        Assert.assertEquals("Clutches are ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
//        Clutches clutches = new Clutches();
        Clutches clutches = (Clutches) context.getBean("clutches");
        clutches.tune();
        Assert.assertEquals(true, clutches.isSharpened());
    }

    @Test
    public void attackWithClutchesShouldBeOk() {
//        Clutches clutches = new Clutches();
        Clutches clutches = (Clutches) context.getBean("clutches");
        for (int i = 0; i < 30; i++) {
            clutches.attack();
        }
        Assert.assertTrue(clutches.getLifetime() == 0);
    }


}
