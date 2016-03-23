package board;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.mockito.Mockito.mock;

public class BoardTest {

    private static BufferedReader br;
    private static PrintWriter pw;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void initVariables() {
        br = mock(BufferedReader.class);
        pw = mock(PrintWriter.class);
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

    @Test(expected = Exception.class)
    public void boardIsCreatingNormally() throws IOException {
        Board board = new Board();
    }

}
