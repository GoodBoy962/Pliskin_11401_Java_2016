package test;

import impl.People;
import impl.Wolverine;
import interfaces.Hero;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class PeopleTest {

    private static Hero hero;
    private static People people;

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
    public static void initObjects() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        hero = mock(Wolverine.class);
        people = context.getBean(People.class);
    }

    @Test
    public void peopleShouldBeGoodCreated() {
        Assert.assertTrue(people.isReady() &&
                people.getHero() == context.getBean(Wolverine.class) &&
                people.getNumber() == 1000 &&
                people.getWords().equals("UHUHU")
        );
    }

    @Test
    public void peopleShouldSay() {
        people.say("qwerty");
        Assert.assertEquals("qwerty\n", outContent.toString());
    }

    @Test
    public void peopleShouldSaySomethingNotUnderstandableWhileGoingAway() {
        people.goAway();
        Assert.assertTrue(outContent.toString().contains("Good"));
    }

}
