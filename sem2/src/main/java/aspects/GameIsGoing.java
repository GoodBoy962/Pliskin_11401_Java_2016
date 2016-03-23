package aspects;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by aleksandrpliskin on 22.03.16.
 * 011
 */
public class GameIsGoing implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (methodInvocation.getMethod().getName().equals("getAddress")) {
            System.out.println("here is url of server");
        }
        methodInvocation.proceed();
        System.out.println("method " + methodInvocation.getMethod().getName() + " ended his work");
        return null;
    }
}

