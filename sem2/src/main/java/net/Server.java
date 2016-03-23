package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

    private ArrayList<ServerThread> connections;

    private ServerSocket serverSocket;

    Server(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        connections = new ArrayList<>();
    }

    InetAddress getAddress() {
        return serverSocket.getInetAddress();
    }

    void go() throws IOException {
        while (true) {
            Socket client1 = serverSocket.accept();
            System.out.println(client1.getInetAddress());
            BufferedReader is1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter os1 = new PrintWriter(client1.getOutputStream(), true);
            os1.print(0);
            os1.flush();
            System.out.println("first is ready");
            Socket client2 = serverSocket.accept();
            System.out.println(client2.getInetAddress());
            BufferedReader is2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintWriter os2 = new PrintWriter(client2.getOutputStream(), true);
            os2.print(1);
            os2.flush();
            System.out.println("second is ready");
            os1.println(0);
            os1.flush();
            System.out.println("ready");
            ServerThread game = new ServerThread(os1, os2, is1, is2);
            connections.add(game);
            game.start();
        }
    }

}
