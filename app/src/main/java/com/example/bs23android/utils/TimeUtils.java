package com.example.bs23android.utils;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatUpdateTime(Context context, String pushedAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime pushedAtDateTime = LocalDateTime.parse(pushedAt, formatter);

        long timeDifference = System.currentTimeMillis() - toMillis(pushedAtDateTime);

        return formatElapsedTime(context, timeDifference);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static long toMillis(LocalDateTime dateTime) {
        return dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private static String formatElapsedTime(Context context, long timeDifference) {
        return DateUtils.getRelativeTimeSpanString(timeDifference, System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
    }
}

