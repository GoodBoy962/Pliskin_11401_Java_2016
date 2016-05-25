package com.pliskin.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 24.04.16.
 */
public class DateUtils {

    public static Date getEndDate(Date sDate, String period) {
        Calendar resultDate = Calendar.getInstance();
        resultDate.setTime(sDate);
        switch (period) {
            case "w":
                resultDate.add(Calendar.WEEK_OF_MONTH, 2);
                break;
            case "m":
                resultDate.add(Calendar.MONTH, 1);
                break;
            default:
                resultDate.add(Calendar.MONTH, 2);
        }
        return resultDate.getTime();
    }

}
