package ui.tui;

import objects.*;
import services.MoodManagement;

import java.util.Scanner;

public class Menu extends MenuPrinter {
    public static void runMenu() {
        Scanner sc = new Scanner(System.in);
        Time actualTime = new Time();
        int usrAction = 0;

        printGreeting(actualTime);
        mainLoop:
        while(true) {
            try{
                printMainMenu();
                usrAction = Integer.parseInt(sc.nextLine());
                if (usrAction > 4 || usrAction < 0) {
                    System.out.println("Select an existing menu item!");
                    continue mainLoop;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Enter the number!");
                continue mainLoop;
            }

            switch (usrAction) {
                case 1 -> {
                    //TODO: реализоать обработку исключений
                    System.out.print("Enter mood name: ");
                    Mood mood = MoodManagement.createMood(sc.nextLine());
                    boolean moodEditing = true;
                    while(moodEditing) {
                        try {
                            printEditMenu(mood);
                            usrAction = Integer.parseInt(sc.nextLine());
                            if (usrAction > 5 || usrAction < 0) System.out.println("Select an existing menu item!");
                            switch (usrAction) {
                                case 1 -> {
                                    if (MoodManagement.saveMood(mood)) {
                                        System.out.println("Mood successfully added!");
                                        moodEditing = false;
                                    }
                                    System.out.println("This mood already exists at this time!");
                                }
                                case 2 -> MoodManagement.changeMoodNameOrNotes(mood, sc.nextLine(), 0);
                                case 3 -> MoodManagement.changeMoodDate(mood, sc.nextLine());
                                case 4 -> MoodManagement.changeMoodTime(mood, sc.nextLine());
                                case 5 -> MoodManagement.changeMoodNameOrNotes(mood, sc.nextLine(), 1);
                                case 0 -> moodEditing = false;
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Enter the number!");
                        }
                    }
                }
                case 2 -> {
                    //TODO: реализоать удаление настроений
                }
                case 3 -> {
                    //TODO: реализоать поиск настроений
                }
                case 4 -> {
                    MoodManagement.displayAllMoods();
                    //TODO: реализоать вывод всех настроений
                }
                case 0 -> {
                    System.out.println("Close application? [ y / n ] ");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        printFarewell(actualTime);
                        break mainLoop;
                    }
                }
            }
        }
    }
}
