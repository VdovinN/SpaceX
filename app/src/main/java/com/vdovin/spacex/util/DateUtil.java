package com.vdovin.spacex.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String convertTimestampToFormattedDate(Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd, yyyy", Locale.getDefault());
        return formatter.format(date);
    }

}
