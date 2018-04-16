package com.example.esc_50005;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Otter on 16/4/2018.
 */

public class DateUtils {
    public static final List<Long> times = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1));
    public static final List<String> timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second");

    public static String toDuration(long date) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long duration = timestamp.getTime() - date;
        if (duration > 604800000) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.format(new Date(date));
        }

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < DateUtils.times.size(); i++) {
            Long current = DateUtils.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(DateUtils.timesString.get(i)).append(temp != 1 ? "s" : "").append(" ago");
                break;
            }
        }
        if ("".equals(res.toString()))
            return "0 seconds ago";
        else
            return res.toString();
    }
}