package test;

import impl.Clutches;
import impl.Gun;
import impl.Wolverine;
import interfaces.Monster;
import interfaces.Weapon;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WolverineTest {

    private static Weapon weapon, weapon1;
    private static Wolverine wolverine;
    private static Monster gungster;
    private static List<Weapon> weapons;

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
        weapon = mock(Clutches.class);
        weapon1 = mock(Gun.class);
        when(weapon.attack()).thenReturn("Ok");
        when(weapon1.attack()).thenReturn("ey");
        wolverine = (Wolverine) context.getBean("wolwerine");
//        gungster = new Gungster("Mica");
        weapons = mock(LinkedList.class);
        when(weapons.get(0)).thenReturn(weapon);
        when(weapons.get(1)).thenReturn(weapon1);
        when(weapons.size()).thenReturn(2);
    }


    @Test
    public void wolverineShouldBeGoodInitialized() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        wolverine = (Wolverine) context.getBean("wolwerine");
        Assert.assertTrue(wolverine.getName().equals("Hugh Jackman") &&
                outContent.toString().contains("i am wolverine the " + wolverine.getName() + " and I'll kill you\n")
        );
    }

    @Test
    public void defaultWolverineShouldBeGoodInitialized() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        wolverine = (Wolverine) context.getBean("wolverine1");
        Assert.assertTrue(outContent.toString().contains("i am wolverine the " + wolverine.getName() + " and I'll kill you\n")
        );
    }

    @Test
    public void wolverineWeaponShouldBeOk() {
        wolverine.setWeapon(weapon);
        Assert.assertEquals(weapon, wolverine.getWeapon());
    }

    @Test
    public void wolverineOpponentShouldBeOk() {
        wolverine.setOpponent(gungster);
        Assert.assertEquals(gungster, wolverine.getMonster());
    }

    @Test
    public void wolverineCanRunAway() {
        Assert.assertEquals(wolverine.runaway(),
                "I'd like to run away");
    }

    @Test
    public void wolverineCanMakeAnAttack() {
        wolverine.setWeapon(weapon);
        wolverine.makeAttack();
        when(weapon.isBroken()).thenReturn(true);
        wolverine.setWeapon(weapon1);
        wolverine.makeAttack();
        when(weapon1.isBroken()).thenReturn(true);
        wolverine.makeAttack();
        Assert.assertNotNull(wolverine.makeAttack());
    }

    @Test
    public void wolverineCanRegenerate() {
        wolverine.regenerate();
        Assert.assertEquals(outContent.toString(), ("The wolverine is regerating!\n"));
    }

    @Test
    public void wolverineShouldUpdateWeaponOnlyOnce() {
        wolverine = (Wolverine) context.getBean("wolwerine");
        when(weapon.isBroken()).thenReturn(true);
        wolverine.setWeapon(weapon);
        wolverine.updateWeapon(weapon);
        wolverine.updateWeapon(weapon);
        Assert.assertTrue(outContent.toString().contains("You won! But I'll be back"));

    }

    @Test
    public void impossibleToDoShouldWorkWhenNoLives() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        wolverine = (Wolverine) context.getBean("wolwerine");
        when(weapon.isBroken()).thenReturn(true);
        wolverine.updateWeapon(weapon);
        wolverine.impossibleToDo();
        Assert.assertTrue(outContent.toString().contains("the Hugh Jackman lives level is over"));

    }

    @Test
    public void getLivesShouldWorkOk() {
        Assert.assertEquals(wolverine.getLives(), 1);
    }

    @Test
    public void wolverineCanHaveLotsOfWeapons() {
        wolverine.setWeapons(weapons);
        Assert.assertTrue(weapon == wolverine.getWeapons().get(0) && weapon1 == wolverine.getWeapons().get(1));
    }

    @Test
    public void wolverineCanMakeSuperAttack() {
        wolverine.setWeapons(weapons);
        Assert.assertEquals(wolverine.makeSuperAttack(), "Okey");
    }

}
