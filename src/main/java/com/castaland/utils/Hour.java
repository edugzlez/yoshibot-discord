package com.castaland.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Hour {

    public static String getHour(String timezone) {
        DateFormat inFormat = new SimpleDateFormat("HH:mm");
        inFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        Date purchaseDate = new Date();
        return inFormat.format(purchaseDate);
    }
}
