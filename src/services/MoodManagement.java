package services;

import objects.*;

import java.util.ArrayList;

public class MoodManagement {
    static ArrayList<Mood> moods = new ArrayList<>();
    static Time moodDateTime = new Time();

    public static Mood createMood(String moodName) {
        DateTimeManagement.syncTime(moodDateTime);
        return new Mood(moodName, moodDateTime.getDate(), moodDateTime.getTime());
    }

    public static boolean saveMood(Mood mood) {
        if (moods.contains(mood)) return false;
        moods.add(mood);
        return true;
    }

    public void delMood() {
        //TODO: реализоать удаление настроений
    }

    public  static void changeMoodNameOrNotes(Mood mood, String string, int mode) {
        if (mode == 0) mood.setName(string);
        if (mode == 1) mood.setNotes(string);
    }
    public  static void changeMoodDate(Mood mood, String date) {
        moodDateTime.setDate(date);
        mood.setDate(moodDateTime.getDate());
    }
    public  static void changeMoodTime(Mood mood, String time) {
        moodDateTime.setTime(time);
        mood.setTime(moodDateTime.getTime());
    }

    public static void displayAllMoods() {
        for (Mood mood: moods) {
            System.out.println(mood);
        }
    }
}
