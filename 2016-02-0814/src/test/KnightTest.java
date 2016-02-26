package test;

import impl.Dragon;
import impl.Knight;
import impl.Sword;
import interfaces.Monster;
import interfaces.Weapon;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KnightTest {

    private static Weapon weapon;
    private static Knight knight;
    private static Monster monster;

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
        weapon = mock(Sword.class);
        when(weapon.attack()).thenReturn("Ok");
        knight = new Knight();
        monster = new Dragon("Drago");
    }


    @Test
    public void knightShouldBeGoodInitialized() {
        knight = new Knight();
        knight.setName("Arthur");
        Assert.assertTrue(knight.getName().equals("Arthur"));
    }

    @Test
    public void knightWeaponShouldBeOk() {
        knight.setWeapon(weapon);
        Assert.assertEquals(weapon, knight.getWeapon());
    }

    @Test
    public void knightOpponentShouldBeOk() {
        knight.setOpponent(monster);
        Assert.assertEquals(monster, knight.getMonster());
    }

    @Test
    public void knightCanSurrender() {
        Assert.assertEquals(knight.runaway(),
                "You won! I will ride away withoit princess");
    }

    @Test
    public void knightCanMakeAnAttack() {
        knight.setWeapon(weapon);
        knight.makeAttack();
        when(weapon.isBroken()).thenReturn(true);
        Assert.assertNotNull(knight.makeAttack());
    }

    @Test
    public void getExtraLivesShouldWorkOk() {
        Assert.assertEquals(knight.getExtraLives(), 0);
    }


}
