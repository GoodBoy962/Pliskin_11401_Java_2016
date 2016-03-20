package test;

import impl.BoxingRing;
import impl.Gungster;
import impl.Orgs;
import impl.Wolverine;
import interfaces.Audience;
import interfaces.Hero;
import interfaces.Monster;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class BoxingRingTest {

    private static Audience audience;
    private static Monster monster;
    private static Hero hero;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static ApplicationContext context;


    @BeforeClass
    public static void initVariables() {
        audience = mock(Orgs.class);
        monster = mock(Gungster.class);
        hero = mock(Wolverine.class);
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
    public void goodStartShouldBe() {
        Assert.assertEquals(((BoxingRing) context.getBean("boxRing")).startBattle("start"), "Start");
    }

    @Test
    public void setAndGetAudienceWorksOk() {
//        BoxingRing boxingRing = new BoxingRing();
        BoxingRing boxingRing = (BoxingRing) context.getBean("boxRing");
        boxingRing.setAudience(audience);
        Assert.assertEquals(audience, boxingRing.getAudience());
    }

    @Test
    public void setAndGetFightersWorksOk() {
//        BoxingRing boxingRing = new BoxingRing();
        BoxingRing boxingRing = (BoxingRing) context.getBean("boxRing");
        boxingRing.setFighters(hero, monster);
        Assert.assertTrue(hero.equals(boxingRing.getHero()) && monster.equals(boxingRing.getMonster()));
    }

    @Test
    public void endBattleWorksGood() {
        Assert.assertEquals("End", ((BoxingRing) context.getBean("boxRing")).endBattle("end"));
    }

    @Test
    public void theLightsShouldTurningGood() {
//        BoxingRing boxingRing = new BoxingRing();
        BoxingRing boxingRing = (BoxingRing) context.getBean("boxRing");
        boxingRing.turnTheLights();
        boxingRing.turnTheLights();
        Assert.assertEquals("the lights were turned up\n" + "the lights were turned down\n", outContent.toString());
    }


}
