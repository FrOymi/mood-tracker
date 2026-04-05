package objects;

import services.DateTimeManagement;

import java.time.LocalDate;
import java.time.LocalTime;

public class Time extends DateTimeManagement {
    private LocalDate date;
    private LocalTime time;
    private String timeOfDay;

    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getTimeOfDay() { return timeOfDay; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }

    public String toString() {
        return "date " + getDate().format(dateFormatter) + " - time " + getTime().format(timeFormatter) + " - time of day " + getTimeOfDay();
    }
}
