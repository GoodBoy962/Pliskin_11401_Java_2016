package com.pliskin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 012
 */
@Aspect
public class noSQLInjections {

    private Pattern pattern = Pattern.compile(".*(;? ?(drop|update|select|insert) ?)*.*");

    @Around("execution(* *..*.execute(String))")
    public Object noSqlInjection(ProceedingJoinPoint jp) throws Throwable {
        String lang = (String) jp.getArgs()[0];
        Matcher matcher = pattern.matcher(lang);
        if (!matcher.find()) {
            System.out.println("injection found");
            return null;
        }
        return jp.proceed();
    }

}
