package test;

import impl.Gun;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GunTest {

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
    public void gunShouldBeGoodInit() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        Gun gun = (Gun) context.getBean("gun");
        Assert.assertTrue(gun.getLifetime() == 20 && gun.getRestAttacksBeforeReload() == 5);
    }

    @Test
    public void tuneIsOk() {
        Weapon weapon = (Weapon) context.getBean("gun");
        weapon.tune();
        Assert.assertEquals("gun is ready\n", outContent.toString());
    }

    @Test
    public void tuneIsOk2() {
        Gun gun = (Gun) context.getBean("gun");
        gun.tune();
        Assert.assertEquals(true, gun.isLoaded());
    }

    @Test
    public void attackWithGunShouldBeOk() {
        Gun gun = (Gun) context.getBean("gun");
        for (int i = 0; i < 30; i++) {
            gun.attack();
        }
        Assert.assertTrue(gun.getLifetime() == 0);
    }

    @Test
    public void getRestAttacksBeforeReloadShouldWork() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        Gun gun = (Gun) context.getBean("gun");
        Assert.assertEquals(gun.getRestAttacksBeforeReload(), 5);
    }

}
