package project.android.unithon.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by USER on 2017-07-30.
 */

public class TimeUtils {
    private static String TAG = "TimeUtils";
    private static String TIME_FORMAT = "a hh:mm";
    private static String DATE_FORMAT = "yyyy. MM. dd. E.";

    public static String getCurrentDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat iso8601Format = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH); // format type
        String formatDate = iso8601Format.format(date);

        Log.d(TAG, "getCurrentDate(), 결과 : " + formatDate);
        return formatDate;
    }

    public static String getCurrentTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat iso8601Format = new SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH); // format type
        String formatDate = iso8601Format.format(date);

        Log.d(TAG, "getCurrentTime(), 결과 : " + formatDate);
        return formatDate;
    }
}
