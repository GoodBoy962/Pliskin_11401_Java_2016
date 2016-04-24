package com.pliskin.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class ServiceAspect {

    private static Logger log = Logger.getLogger(ServiceAspect.class);

    public void before() {
        log.info("\n==========>" + new Date() + "\n"
                + "==========================================================================================" + "\n");
    }

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start invoking "
                + joinPoint.getTarget().getClass().getSimpleName()
                + "."
                + joinPoint.getSignature().getName()
                + " with params "
                + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("End invoking method: " + (end - start) + "ms");
        return result;
    }

}
