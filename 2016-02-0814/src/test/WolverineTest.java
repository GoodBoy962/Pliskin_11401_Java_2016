package test;

import impl.Clutches;
import impl.Gun;
import impl.Gungster;
import impl.Wolverine;
import interfaces.Monster;
import interfaces.Weapon;
import org.junit.*;

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
        weapon = mock(Clutches.class);
        weapon1 = mock(Gun.class);
        when(weapon.attack()).thenReturn("Ok");
        when(weapon1.attack()).thenReturn("ey");
        wolverine = new Wolverine("Hugh");
        gungster = new Gungster("Mica");
        weapons = mock(LinkedList.class);
        when(weapons.get(0)).thenReturn(weapon);
        when(weapons.get(1)).thenReturn(weapon1);
        when(weapons.size()).thenReturn(2);
    }


    @Test
    public void wolverineShouldBeGoodInitialized() {
        wolverine = new Wolverine("Hugh");
        Assert.assertTrue(wolverine.getName().equals("Hugh") &&
                outContent.toString().equals("i am wolverine the " + wolverine.getName() + " and I'll kill you\n"));
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
        Wolverine dragon = new Wolverine("Alex");
        when(weapon.isBroken()).thenReturn(true);
        dragon.setWeapon(weapon);
        dragon.updateWeapon(weapon);
        dragon.updateWeapon(weapon);
        Assert.assertTrue(outContent.toString().contains("You won! But I'll be back"));

    }

    @Test
    public void impossibleToDoShouldWorkWhenNoLives() {
        Wolverine dragon = new Wolverine("Hugh");
        when(weapon.isBroken()).thenReturn(true);
        dragon.updateWeapon(weapon);
        dragon.impossibleToDo();
        Assert.assertTrue(outContent.toString().contains("the Hugh lives level is over"));

    }

    @Test
    public void getLivesShouldWorkOk() {
        Assert.assertEquals(wolverine.getLives(), 0);
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
