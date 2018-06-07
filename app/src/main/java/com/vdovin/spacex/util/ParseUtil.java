package com.vdovin.spacex.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil {

    public static String getYoutubeId(String link) {
        String youtubeVideoId = "";
        Matcher m = Pattern.compile("[?&;]v=([^?&;]+)").matcher(link);
        if (m.find()) {
            youtubeVideoId = m.group(1);
        }
        return youtubeVideoId;
    }

}
