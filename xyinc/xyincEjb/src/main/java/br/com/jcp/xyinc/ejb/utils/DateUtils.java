package br.com.jcp.xyinc.ejb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * DateUtils
 * 
 * @author Juliano Parreira
 * @since 1.0
 * @version 1.0
 */
public abstract class DateUtils {
    
	private static final int DATE_TIME_GMT_INDEX = 3;
	private static final int MONTH_NAME_INDEX = 4;
	private static final int DATE_GMT_INDEX = 8;
	
    public static Date addDaysHours(Date date, int days, int hours)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        cal.add(Calendar.HOUR, hours); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public enum DateFormat {

        TIME("HH:mm", 0),
        DATE("dd/MM/yyyy", 1),
        DATE_TIME("yyyy-MM-dd HH:mm:ss", 2),
        DATE_TIME_GMT("yyyy-MM-dd HH:mm:ss.SSSZ", 3),
        MONTH_NAME("MMMM", 4),
        WEEK_DAY("EEEE", 5),
        DATE_ISO8601("yyyy/MM/dd", 6),
        TIMESTAMP("yyyyMMddHHmmss", 7),
        DATE_GMT("dd/MM/yyyy", 8),
        ;
        
        public final String format;
        public final int index;

        DateFormat(String format, int index) {
            this.format = format;
            this.index = index;
        }

        @Override
        public String toString() {
            return "DateFormat["+format+"]";
        }
    };
    
    private static SimpleDateFormat[] dateFormat;
    private static Locale currentLocale;
    
    static {
            dateFormat = new SimpleDateFormat[DateFormat.values().length];
            setup();
    }

    private static void setup() {
        currentLocale = new Locale("pt_BR");
        
        for(DateFormat value : DateFormat.values()) {
        	
        	if (value.index == MONTH_NAME_INDEX) {
        		dateFormat[value.index] = new SimpleDateFormat(value.format, currentLocale);
        	}
        	if (value.index == DATE_TIME_GMT_INDEX) {
        		dateFormat[value.index] = new SimpleDateFormat(value.format, currentLocale);
                dateFormat[value.index].setTimeZone(TimeZone.getTimeZone("GMT"));
        	}
        	if (value.index == DATE_GMT_INDEX) {
        		dateFormat[value.index] = new SimpleDateFormat(value.format, currentLocale);
                dateFormat[value.index].setTimeZone(TimeZone.getTimeZone("GMT"));
        	}
        	else {
        		dateFormat[value.index] = new SimpleDateFormat(value.format, currentLocale);
        	}
        }
    }

    public static Date parse(DateFormat dateformat, String strdate) {
        try {
			return dateFormat[dateformat.index].parse(strdate);
		} catch (ParseException e) {
			System.out.println("## DATE UTILS - PARSE: ["+strdate+"] - EXCEPTION: " + e.getMessage());
			return null;
		}
    }    
    
    public static String format(DateFormat dateformat, Date date) {
        return dateFormat[dateformat.index].format(date);
    }
    
    public static String format(DateFormat dateformat, long datetimeInMillis) {
        return dateFormat[dateformat.index].format(new Date(datetimeInMillis));
    }
}
