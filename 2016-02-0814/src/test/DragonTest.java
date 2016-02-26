package test;

import impl.Dragon;
import impl.FireBallsStroker;
import impl.Knight;
import interfaces.Hero;
import interfaces.Weapon;
import org.junit.*;

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
        weapon = mock(FireBallsStroker.class);
        when(weapon.attack()).thenReturn("Ok");
        hero = mock(Knight.class);
        dragon = new Dragon("Ralph");
    }


    @Test
    public void dragonShouldBeGoodInitialized() {
        dragon = new Dragon("Ralph");
        Assert.assertTrue(dragon.getName().equals("Ralph") &&
                outContent.toString().equals("i am dragon the " + dragon.getName() + " and I'll kill you\n"));
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
        Assert.assertEquals(new Dragon("Mao").surrender(),
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
        Dragon dragon = new Dragon("Moa");
        when(weapon.isBroken()).thenReturn(true);
        dragon.setWeapon(weapon);
        dragon.updateWeapon(weapon);
        dragon.updateWeapon(weapon);
        Assert.assertTrue(outContent.toString().contains("You won! The Princess is yours now"));
    }

    @Test
    public void impossibleToDoShouldWorkWhenNoLives() {
        Dragon dragon = new Dragon("Moa");
        when(weapon.isBroken()).thenReturn(true);
        dragon.updateWeapon(weapon);
        dragon.impossibleToDo();
        Assert.assertTrue(outContent.toString().contains("the Moa lives level is over"));
    }

    @Test
    public void getLivesShouldWorkOk() {
        Assert.assertEquals(dragon.getLives(), 1);
    }

}
