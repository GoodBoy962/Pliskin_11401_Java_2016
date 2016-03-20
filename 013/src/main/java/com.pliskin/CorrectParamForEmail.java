package com.pliskin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aleksandrpliskin on 20.03.16.
 */
@Aspect
public class CorrectParamForEmail {

    private Pattern pattern1 = Pattern.compile(".+@.+");
    private Pattern pattern2 = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    @Around("execution(* *..*.setEmail(*))")
    public Object isEmailCorrect(ProceedingJoinPoint jp) throws Throwable {
        String lang = (String) jp.getArgs()[0];
        String message = "give me good email";
        Matcher matcher2 = pattern2.matcher(lang);
        Matcher matcher1 = pattern1.matcher(lang);
        if (!matcher2.matches()) {
            if (!matcher1.matches()) {
                System.out.println(message);
                return null;
            } else {
                System.out.println("email is not good really, but okey");
                jp.proceed();
            }
        }
        return jp.proceed();
    }
}
