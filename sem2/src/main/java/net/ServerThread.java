package net;

import java.io.BufferedReader;
import java.io.PrintWriter;

class ServerThread extends Thread {

    private final int ERROR = 111;

    private PrintWriter pw1, pw2;
    private BufferedReader br1, br2;

    ServerThread(PrintWriter pw1, PrintWriter pw2, BufferedReader br1, BufferedReader br2) {
        this.br1 = br1;
        this.br2 = br2;
        this.pw1 = pw1;
        this.pw2 = pw2;
        System.out.println("Game created");
    }

    @Override
    public void run() {
//        pw1.print(200);
//        pw1.flush();
        while (true) {
            Integer str1 = null;
            try {
                str1 = br1.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (str1 == -1) {
                pw2.write(ERROR);
                pw2.flush();
                System.out.println("to second send error");
                break;
            }
            System.out.println(str1);
            pw2.write(str1);
            pw2.flush();
            Integer str2 = null;
            try {
                str2 = br2.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (str2 == -1) {
                pw1.write(ERROR);
                pw1.flush();
                System.out.println("to first send error");
                break;
            }
            System.out.println(str2);
            pw1.write(str2);
            pw1.flush();
            System.out.println("step done");
        }
    }

}