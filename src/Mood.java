import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Mood {
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.MIDNIGHT;
    private String name;
    private String notes = "Empty";

    // Constructors
    public Mood(String name) {
        setName(name);
    }
    public Mood(String name, LocalDate date) {
        setName(name);
        setDate(date);
    }
    public Mood(String name, LocalDate date, LocalTime time) {
        setName(name);
        setDate(date);
        setTime(time);
    }
    public Mood(String name, String notes) {
        setName(name);
        setNotes(notes);
        if (this.notes.isBlank()) {this.notes = "Empty";}
    }
    public Mood(String name, LocalDate date, String notes) {
        setName(name);
        setDate(date);
        setNotes(notes);
        if (this.notes.isBlank()) {this.notes = "Empty";}
    }
    public Mood(String name, LocalDate date, LocalTime time, String notes) {
        setName(name);
        setDate(date);
        setTime(time);
        setNotes(notes);
        if (this.notes.isBlank()) {this.notes = "Empty";}
    }

    // Getters
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setName(String name) { this.name = name; }
    public void setNotes(String notes) { this.notes = notes; }

    // Setters
    public LocalDate getDate() { return this.date; }
    public LocalTime getTime() { return this.time; }
    public String getName() { return this.name; }
    public String getNotes() { return this.notes; }

    public String toString() {
        String str = "Name: " + name + " - Date: " + date +" - Time: " + time;
        if (!this.notes.equals("Empty")) {
            str += " - Notes: " + notes;
        }
        return str;
    }

    public boolean equalsName(String name) {return this.name.equalsIgnoreCase(name);}
    public boolean equalsDate(LocalDate date) {return this.date.isEqual(date);}
    public boolean equalsTime(LocalTime time) {return this.time.equals(time);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mood secondMood = (Mood) o;
        return getName().equalsIgnoreCase(secondMood.getName()) && getDate().isEqual(secondMood.getDate()) && getTime().equals(secondMood.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }
}
