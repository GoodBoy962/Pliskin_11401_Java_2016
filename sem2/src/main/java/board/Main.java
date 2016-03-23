package board;

import java.io.*;
import java.net.Socket;


public class Main {

    public static void main(String[] args) throws IOException {
        int port = 3456;
        String host = "localhost";
        Socket s = new Socket(host, port);
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        Integer tern = br.read();
        System.out.println(tern);
        if (tern == 48) {
            tern = 0;
            boolean flag = true;
            while (flag) {
                Integer when = br.read();
                System.out.println(when);
                if (when == 48) {
                    flag = false;
                }
            }
        } else {
            tern = 1;
        }
        System.out.println(tern);
        System.out.println("game starts");
        Board board = new Board();
        board.goGame(pw, br, tern);
    }

}
