package test;

import impl.BattleField;
import impl.Dragon;
import impl.Knight;
import impl.People;
import interfaces.Audience;
import interfaces.Hero;
import interfaces.Monster;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class BattleFieldTest {

    private static Audience people;
    private static Hero hero;
    private static Monster monster;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void initObjects() {
        people = mock(People.class);
        hero = mock(Knight.class);
        monster = mock(Dragon.class);
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
    public void goodStartShouldBe() {
        Assert.assertEquals("Start", new BattleField().startBattle("start"));
    }

    @Test
    public void setAndGetAudienceWorksOk() {
        BattleField battleField = new BattleField();
        battleField.setAudience(people);
        Assert.assertEquals(people, battleField.getAudience());
    }

    @Test
    public void setAndGetFightersWorksOk() {
        BattleField battleField = new BattleField();
        battleField.setFighters(hero, monster);
        Assert.assertTrue(hero.equals(battleField.getHero()) && monster.equals(battleField.getMonster()));
    }

    @Test
    public void endBattleWorksGood() {
        Assert.assertEquals("End", new BattleField().endBattle("end"));
    }

    @Test
    public void isSunGivesRealSun() {
        Assert.assertNotNull(new BattleField().isSun());
    }

    @Test
    public void theySunShouldTurningGood() {
        BattleField battleField = new BattleField();
        battleField.turnTheSun();
        Assert.assertEquals("the sun is up\n", outContent.toString());
    }

}
