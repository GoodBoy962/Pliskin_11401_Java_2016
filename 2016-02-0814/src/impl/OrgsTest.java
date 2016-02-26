package impl;

import interfaces.Monster;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class OrgsTest {

    private static Monster monster;
    private static Orgs orgs;

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
        monster = mock(Dragon.class);
        orgs = new Orgs("Orgs!!!!", monster, 100);
    }

    @Test
    public void orgsShouldBeGoodCreated() {
        Assert.assertTrue(orgs.isReady() &&
                orgs.getMonster() == monster &&
                orgs.getNumber() == 100 &&
                orgs.getWords().equals("Orgs!!!!")
        );
    }

    @Test
    public void orgsShouldSay() {
        orgs.say("qwerty");
        Assert.assertEquals("qwerty\n", outContent.toString());
    }

    @Test
    public void orgsShouldSaySomethingNotUnderstandableWhileGoingAway() {
        orgs.goAway();
        Assert.assertTrue(outContent.toString().contains("sev["));
    }

}
