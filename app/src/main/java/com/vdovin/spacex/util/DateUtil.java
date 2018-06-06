package com.vdovin.spacex.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String convertTimestampToFormattedDate(Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd, YYYY");
        return formatter.format(date);
    }

}
