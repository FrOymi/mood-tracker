import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class MoodTracker {
    private static String setTimeOfDay(int hours) {
        final String MORNING = "morning";
        final String AFTERNOON = "afternoon";
        final String EVENING = "evening";
        final String NIGHT = "night";

        String timeOfDay;

        if (hours >= 5 && hours <= 11) {timeOfDay = MORNING;}
        else if (hours >= 12 && hours <= 17) {timeOfDay = AFTERNOON;}
        else if (hours >= 18 && hours <= 21) {timeOfDay = EVENING;}
        else {timeOfDay = NIGHT;}

        return timeOfDay;
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Scanner sc = new Scanner(System.in);
        ArrayList<Mood> moods = new ArrayList<>();


        boolean appIsRunning = true;
        while(appIsRunning) {
            String timeOfDay = setTimeOfDay(LocalTime.now().getHour());
            System.out.println(timeOfDay.equals("night") ? "Hi there!" : "Good ".concat(timeOfDay).concat("!"));

            // Main Menu
            mainLoop:
            while(true) {
                System.out.print("1. Add mood\n" +
                        "2. Delete mood\n" +
                        "3. Search for moods\n" +
                        "4. Get all moods\n" +
                        "5. Write the moods to a file\n" +
                        "0. Exit\n" +
                        " >>> ");
                try {
                    int usrAction_mainMenu = Integer.parseInt(sc.nextLine());
                    if (usrAction_mainMenu < 0 || usrAction_mainMenu > 5) {
                        System.out.println("Select an existing menu item!");
                        continue mainLoop;
                    }

                    switch (usrAction_mainMenu) {
                        case 1 -> {
                            System.out.print("Mood: ");
                            String moodName = sc.nextLine();
                            Mood newMood = new Mood(moodName);

                            // Add Mood Menu
                            addMenuLoop:
                            while(true) {
                                System.out.println("\n" + newMood + "\n");
                                System.out.print("1. Save mood\n" +
                                        "2. Edit name\n" +
                                        "3. Edit date\n" +
                                        "4. Edit time\n" +
                                        "5. Edit notes\n" +
                                        "0. Back to main menu\n" +
                                        " >>> ");
                                try {
                                    int usrAction_addMenu = Integer.parseInt(sc.nextLine());
                                    if (usrAction_addMenu < 0 || usrAction_addMenu > 5) {
                                        System.out.println("Select an existing menu item!");
                                        continue addMenuLoop;
                                    }

                                    switch (usrAction_addMenu) {
                                        case 1 -> {
                                            if (moods.contains(newMood)) {
                                                System.out.println("You've already added an emotion at this time!\n");
                                                continue addMenuLoop;
                                            }
                                            moods.add(newMood);
                                            System.out.println("The emotion has been successfully saved!\n");
                                            break addMenuLoop;
                                        }
                                        case 2 -> {
                                            System.out.print("Name: ");
                                            newMood.setName(sc.nextLine());
                                        }
                                        case 3 -> {
                                            System.out.println("-Press Enter to keep the default (current) value-");
                                            System.out.print("Date (dd.MM.yyyy): ");
                                            newMood.setDate(LocalDate.parse(sc.nextLine(), formatter));
                                        }
                                        case 4 -> {
                                            System.out.println("-Press Enter to keep the default (current) value-");
                                            System.out.print("Time (HH:mm): ");
                                            newMood.setTime(LocalTime.parse(sc.nextLine()));
                                        }
                                        case 5 -> {
                                            System.out.print("Notes: ");
                                            newMood.setNotes(sc.nextLine());
                                        }
                                        case 0 -> {
                                            System.out.println();
                                            break addMenuLoop;
                                        }
                                        default -> System.out.println("Not a valid input!");
                                    }
                                }
                                catch (NumberFormatException e) {
                                    System.out.println("Enter a number!");
                                }
                            }
                        }
                        case 2 -> {
                            //TODO
                        }
                        case 3 -> {
                            //TODO
                        }
                        case 4 -> {
                            if (moods.isEmpty()) {
                                System.out.println("The list of moods is empty\n");
                                continue mainLoop;
                            }
                            System.out.println();
                            for(Mood mood: moods) {
                                System.out.println(mood);
                            }
                            System.out.println();
                        }
                        case 5 -> {
                            //TODO
                        }
                        case 0 -> {break mainLoop;}
                        default -> System.out.println("Not a valid input!");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Enter a number!");
                }
            }

            System.out.println("Close application? [ y / n ] ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                appIsRunning = false;
                sc.close();
                System.out.printf("Have a great %s!\nSee you soon!", timeOfDay);
            }
        }
    }
}
