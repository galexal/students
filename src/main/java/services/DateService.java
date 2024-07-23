package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateService {
    public static String convertDateForDb(String date) {
        String dateForDb;
        try {
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date formattedDate = format.parse(date);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateForDb = formatter.format(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка форматирования даты!");
        }
        return dateForDb;
    }
}
