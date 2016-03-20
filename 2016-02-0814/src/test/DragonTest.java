package test;

import impl.Dragon;
import impl.FireBallsStroker;
import impl.Knight;
import interfaces.Hero;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DragonTest {

    private static Weapon weapon;
    private static Hero hero;
    private static Dragon dragon;

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
        hero = mock(Knight.class);
        dragon = (Dragon) context.getBean("dragonRalph");
    }


    @Test
    public void dragonShouldBeGoodInitialized() {
//        dragon = new Dragon("Ralph");
        dragon = (Dragon) new ClassPathXmlApplicationContext("spring-config.xml").getBean("dragonRalph");
        Assert.assertTrue(dragon.getName().equals("Ralph") &&
                (outContent.toString().contains("i am dragon the " + dragon.getName() + " and I'll kill you\n")));
    }

    @Test
    public void dragonWeaponShouldBeOk() {
        dragon.setWeapon(weapon);
        Assert.assertEquals(weapon, dragon.getWeapon());
    }

    @Test
    public void dragonOpponentShouldBeOk() {
        dragon.setOpponent(hero);
        Assert.assertEquals(hero, dragon.getHero());
    }

    @Test
    public void dragonCanSurrender() {
        Assert.assertEquals(dragon.surrender(),
                "You won! The Princess is yours now");
    }

    @Test
    public void dragonCanMakeAnAttack() {
        dragon.setWeapon(weapon);
        Assert.assertNotNull(dragon.makeAttack());
    }

    @Test
    public void dragonCanRegenerate() {
        dragon.regenerate();
        Assert.assertTrue(outContent.toString().contains("The dragon is renerating!"));
    }

    @Test
    public void dragonShouldUpdateWeaponOnlyOnce() {
//        Dragon dragon = new Dragon("Moa");
        when(weapon.isBroken()).thenReturn(true);
        dragon.setWeapon(weapon);
        dragon.updateWeapon(weapon);
        dragon.updateWeapon(weapon);
        Assert.assertTrue(outContent.toString().contains("You won! The Princess is yours now"));
    }

    @Test
    public void impossibleToDoShouldWorkWhenNoLives() {
//        Dragon dragon = new Dragon("Moa");
        when(weapon.isBroken()).thenReturn(true);
        dragon.updateWeapon(weapon);
        dragon.impossibleToDo();
        Assert.assertTrue(outContent.toString().contains("the Ralph lives level is over"));
    }

    @Test
    public void getLivesShouldWorkOk() {
        dragon = (Dragon) context.getBean("dragonRalph");
        Assert.assertEquals(dragon.getLives(), 0);
    }

}
