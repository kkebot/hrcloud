package hr.web.service.utils;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    public static Date firstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     *
     * @param from a date
     * @param months can be a negative Integer
     * @return from + months
     */
    public static Date advanceMonths(Date from, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.add(Calendar.MONTH, months);

        return calendar.getTime();
    }



    public static Date advanceMonths(int months) {
        return advanceMonths(new Date(), months);
    }

}
