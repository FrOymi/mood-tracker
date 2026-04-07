package objects;

import services.DateTimeManagement;

import java.time.LocalDate;
import java.time.LocalTime;

public class Time extends DateTimeManagement {
    private LocalDate date;
    private LocalTime time;
    private String timeOfDay;

    public LocalDate getDate() { return LocalDate.parse(date.format(dateFormatter), dateFormatter); }
    public LocalTime getTime() { return LocalTime.parse(time.format(timeFormatter), timeFormatter); }
    public String getTimeOfDay() { return timeOfDay; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setDate(String date) { this.date = LocalDate.parse(date, dateFormatter); }

    public void setTime(LocalTime time) { this.time = time; }
    public void setTime(String time) { this.time = LocalTime.parse(time, timeFormatter); }

    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }

    public String toString() {
        return "date " + getDate() + " - time " + getTime() + " - time of day " + getTimeOfDay();
    }
}
