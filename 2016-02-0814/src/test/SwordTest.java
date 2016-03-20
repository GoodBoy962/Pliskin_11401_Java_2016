package test;

import impl.Sword;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SwordTest {

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
    public void swordShouldBeGoodInit() {
        Sword sword = (Sword) context.getBean("sword");
        Assert.assertTrue(sword.getLifetime() == 15 && sword.getRestAttacksBeforeReload() == 3);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = (Weapon) context.getBean("sword");
        weapon.tune();
        Assert.assertEquals("Sword is ready\nSword is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        Sword sword = (Sword) context.getBean("sword");
        sword.tune();
        Assert.assertEquals(true, sword.isSharpened());
    }

    @Test
    public void attackWithSwordShouldBeOk() {
        Sword sword = (Sword) context.getBean("sword");
        for (int i = 0; i < 30; i++) {
            sword.attack();
        }
        Assert.assertTrue(sword.getLifetime() == 0);
    }

}
