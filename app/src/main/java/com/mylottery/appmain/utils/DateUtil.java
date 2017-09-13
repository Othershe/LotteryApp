package com.mylottery.appmain.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static String[] getDate(int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, (-30 * (num - 1) - (num - 1)));
        String end = sdf.format(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, -30);
        String start = sdf.format(calendar.getTime());

        return new String[]{start, end};
    }
}
