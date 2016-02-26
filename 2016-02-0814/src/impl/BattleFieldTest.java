package impl;

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
        BoxingRing boxingRing = new BoxingRing();
        boxingRing.setFighters(hero, monster);
        Assert.assertTrue(hero.equals(boxingRing.getHero()) && monster.equals(boxingRing.getMonster()));
    }

    @Test
    public void endBattleWorksGood() {
        Assert.assertEquals("End", new BoxingRing().endBattle("end"));
    }

    @Test
    public void theSunShouldTurningGood() {
        BattleField battleField = new BattleField();
        battleField.turnTheSun();
        battleField.turnTheSun();
        Assert.assertEquals("the sun is up\n" + "the sun is down\n", outContent.toString());
    }

}
