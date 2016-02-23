import org.junit.*;

public class Vector2DTest {

    private static final double EPS = 1e-9;

    private static Vector2D v;
    private static Vector2D vDefault;

    @BeforeClass
    public static void beforeClass() {
        v = new Vector2D(3, 4);
        vDefault = new Vector2D();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("AfterClass");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }


    @Test
    public void defaultVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, vDefault.length(), EPS);
    }

    @Test
    public void vectorShouldHaveNotZeroLength() {
        Assert.assertFalse(v.length() == (double) 0);
    }

    @Test
    public void firstParameterShouldBeSavedInX() {
        Vector2D v = new Vector2D(3, 4);
        Assert.assertEquals(3, v.getX(), EPS);
    }

    @Test
    public void secondParameterShouldBeSavedInY() {
        Vector2D v = new Vector2D(3, 4);
        Assert.assertEquals(4, v.getY(), EPS);
    }

    @Test
    public void afterAddingVectorCoordinatesShouldBeGood() {
        Vector2D v1 = new Vector2D(1, 1);
        v1.addVector(v);
        Assert.assertTrue(v1.getX() == 4 && v1.getY() == 5);
    }

}
