package services;

import objects.Time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeManagement {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static void syncTime(Time dateTime) {
        dateTime.setDate(LocalDate.now());
        dateTime.setTime(LocalTime.now());
        if (dateTime.getTime().getHour() >= 5 && dateTime.getTime().getHour() <= 11) dateTime.setTimeOfDay("Morning");
        if (dateTime.getTime().getHour() >= 12 && dateTime.getTime().getHour() <= 17) dateTime.setTimeOfDay("Afternoon");
        if (dateTime.getTime().getHour() > 18 && dateTime.getTime().getHour() <= 21) dateTime.setTimeOfDay("Evening");
        else dateTime.setTimeOfDay("Night");
    }
}

