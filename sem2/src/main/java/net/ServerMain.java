package net;

import aspects.GameIsGoing;
import aspects.ServerStartedWorkingBefore;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by aleksandrpliskin on 22.03.16.
 * 008
 * 011
 */
public class ServerMain {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("server-config.xml");
        ServerSocket s = (ServerSocket) context.getBean("serverSocket");
        Server server = new Server(s);
        ProxyFactory pf = new ProxyFactory(server);
        pf.addAdvice(new ServerStartedWorkingBefore());
        pf.addAdvice(new GameIsGoing());
        server = (Server) pf.getProxy();
        server.getAddress();
        server.go();
    }

}
