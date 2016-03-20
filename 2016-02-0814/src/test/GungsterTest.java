package test;

import impl.FireBallsStroker;
import impl.Gungster;
import impl.Wolverine;
import interfaces.Hero;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GungsterTest {

    private static Weapon weapon;
    private static Hero hero;
    private static Gungster gungster;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static ApplicationContext context;

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

    @BeforeClass
    public static void initVariables() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        weapon = mock(FireBallsStroker.class);
        when(weapon.attack()).thenReturn("Ok");
        hero = mock(Wolverine.class);
        gungster = context.getBean(Gungster.class);
    }


    @Test
    public void gungsterShouldBeGoodInitialized() {
//        gungster = new Gungster("Mica");
        gungster = context.getBean(Gungster.class);
        Assert.assertTrue(gungster.getName().equals("Mica"));
    }

    @Test
    public void gungsterWeaponShouldBeOk() {
        gungster.setWeapon(weapon);
        Assert.assertEquals(weapon, gungster.getWeapon());
    }

    @Test
    public void gungsterOpponentShouldBeOk() {
        gungster.setOpponent(hero);
        Assert.assertEquals(hero, gungster.getHero());
    }

    @Test
    public void gungsterCanSurrender() {
        Assert.assertEquals(gungster.surrender(),
                "AAAAAAA! run boy run");
    }

    @Test
    public void gungsterCanMakeAnAttack() {
        gungster.setWeapon(weapon);
        gungster.makeAttack();
        when(weapon.isBroken()).thenReturn(true);
        Assert.assertNotNull(gungster.makeAttack());
    }

    @Test
    public void getExtraLivesShouldWorkOk() {
        Assert.assertEquals(gungster.getExtraLives(), 0);
    }

}
