package eu.grigoriev.utils.general;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static final String READABLE = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO8601_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static Logger logger = Logger.getLogger(DateTimeUtils.class);

    public static String getCurrentDateTime() {
        return dateToISO8601Format(new Date());
    }

    public static String fromReadableToISO8601(String readableDateTime) {
        try {
            Date date = new SimpleDateFormat(READABLE).parse(readableDateTime);
            return dateToISO8601Format(date);
        } catch (ParseException e) {
            return readableDateTime;
        }
    }

    public static String fromISO8601toReadable(String iso8601) {
        try {
            Date date = new SimpleDateFormat(ISO8601_UTC).parse(iso8601);
            return dateToReadableFormat(date);
        } catch (ParseException e) {
            return iso8601;
        }
    }

    public static String dateToISO8601Format(Date date) {
        return DateFormatUtils.format(date, ISO8601_UTC);
    }

    public static String dateToReadableFormat(Date date) {
        return DateFormatUtils.format(date, READABLE);
    }
}
