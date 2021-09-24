package com.su.primeavto.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils {
    private static final String DATE_FORMAT = "dd MMM, yyyy";

    public static long stringToMillis(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());

        return formatter.format(date);
    }


    public static long getHourInMillis(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getHourInMillis(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMinutesInMillis(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String millisToDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());

    }
}
