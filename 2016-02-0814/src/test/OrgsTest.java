package test;

import impl.Dragon;
import impl.Orgs;
import interfaces.Monster;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

public class OrgsTest {

    private static Monster monster;
    private static Orgs orgs;

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
        monster = mock(Dragon.class);
        orgs = (Orgs) context.getBean(Orgs.class);
    }

    @Test
    public void orgsShouldBeGoodCreated() {
        Assert.assertTrue(orgs.isReady() &&
                orgs.getMonster() == context.getBean("dragon") &&
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
