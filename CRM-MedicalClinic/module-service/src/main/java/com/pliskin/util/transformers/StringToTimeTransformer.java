package com.pliskin.util.transformers;

import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Component
public class StringToTimeTransformer implements Function<String, String> {
    @Override
    public String apply(String s) {
        String result = "";
        String dayPart = s.substring(s.length() - 2, s.length());
        String time = s.substring(0, s.length() - 3);
        if (time.equals("12:00") && dayPart.equals("AM")) {
            result += (time + ":00/" + "13:00:00");
        } else if (dayPart.equals("PM")) {
            result += (Integer.parseInt(time.substring(0, 2)) + 12) + ":00:00/" + (Integer.parseInt(time.substring(0, 2)) + 13) + ":00:00";
        } else {
            result += time + ":00/" + (Integer.parseInt(time.substring(0, 2)) + 1) + ":00:00";
        }
        return result;
    }
}
