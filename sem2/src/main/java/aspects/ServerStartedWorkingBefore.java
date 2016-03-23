package aspects;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
public class ServerStartedWorkingBefore implements MethodBeforeAdvice {

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Server started working");
    }
}
