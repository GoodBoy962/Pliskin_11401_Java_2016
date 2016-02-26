package test;

import impl.People;
import impl.Wolverine;
import interfaces.Hero;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class PeopleTest {

    private static Hero hero;
    private static People people;

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
    public static void initObjects() {
        hero = mock(Wolverine.class);
        people = new People("UHUHU", hero, 10000);
    }

    @Test
    public void orgsShouldBeGoodCreated() {
        Assert.assertTrue(people.isReady() &&
                people.getHero() == hero &&
                people.getNumber() == 10000 &&
                people.getWords().equals("UHUHU")
        );
    }

    @Test
    public void orgsShouldSay() {
        people.say("qwerty");
        Assert.assertEquals("qwerty\n", outContent.toString());
    }

    @Test
    public void orgsShouldSaySomethingNotUnderstandableWhileGoingAway() {
        people.goAway();
        Assert.assertTrue(outContent.toString().contains("Good"));
    }

}
