package ui.tui;

import objects.*;
import services.*;

class MenuPrinter {
    static void printMainMenu() {
        System.out.println("""
                1. Add mood
                2. Delete mood
                3. Search for moods
                4. Get all moods
                0. Exit
                 >>>\s""");
    }

    static void printEditMenu(Mood mood) {
        System.out.println(mood);
        System.out.println("""
                1. Save mood
                2. Edit name
                3. Edit date
                4. Edit time
                5. Edit notes
                0. Back to main menu
                 >>>\s""");
    }

    static void printGreeting(Time actualTime) {
        DateTimeManagement.syncTime(actualTime);
        System.out.println(actualTime.getTimeOfDay().equals("Night") ? "Hi there!" : "Good ".concat(actualTime.getTimeOfDay()).concat("!"));
    }

    static void printFarewell(Time actualTime) {
        DateTimeManagement.syncTime(actualTime);
        System.out.printf("Have a great %s!\nSee you soon!", actualTime.getTimeOfDay());
    }
}
