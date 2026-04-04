import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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

    private static ArrayList<Integer> searchMood(ArrayList<Mood> moods, DateTimeFormatter dateFormatter, DateTimeFormatter timeFormatter, Scanner sc) {
        ArrayList<Integer> indexes = new ArrayList<>();
        String usrInput = sc.nextLine();
        if (usrInput.isBlank()) {
            System.out.println();
            return indexes;
        }
        String moodName = "";
        try {
            LocalDate date = LocalDate.parse(usrInput, dateFormatter);
            for (int dateIdx = 0; dateIdx < moods.size(); dateIdx++) {
                if (moods.get(dateIdx).equalsDate(date)) {
                    indexes.add(dateIdx);
                }
            }
        }
        catch (DateTimeParseException e) {
            moodName = usrInput;
        }

        if (!moodName.isEmpty()) {
            for (int moodIdx = 0; moodIdx < moods.size(); moodIdx++) {
                if (moods.get(moodIdx).equalsName(moodName)) {
                    indexes.add(moodIdx);
                }
            }
        }

        return indexes;
    }

    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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
                        System.out.println("Select an existing menu item!\n");
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
                                                System.out.println("You've already added such a mood at this time!");
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
                                            try {
                                                newMood.setDate(LocalDate.parse(sc.nextLine(), dateFormatter));
                                            }
                                            catch (DateTimeParseException e) {
                                                newMood.setDate(LocalDate.parse(LocalDate.now().format(dateFormatter), dateFormatter));
                                                System.out.println("-Default value set-");
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("-Press Enter to keep the default (current) value-");
                                            System.out.print("Time (HH:mm): ");
                                            try {
                                                newMood.setTime(LocalTime.parse(sc.nextLine(), timeFormatter));
                                            }
                                            catch (DateTimeParseException e) {
                                                newMood.setTime(LocalTime.parse(LocalTime.now().format(timeFormatter), timeFormatter));
                                                System.out.println("-Default value set-");
                                            }
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
                            if (moods.isEmpty()) {
                                System.out.println("You haven't added a single mood yet!\n");
                                continue mainLoop;
                            }
                            ArrayList<Integer> indexes = new ArrayList<>();
                            System.out.println("-Enter for back-");
                            System.out.print("Date (dd.MM.yyyy) or Mood name: ");
                            String usrInput = sc.nextLine();
                            if (usrInput.isBlank()) {
                                System.out.println();
                                continue mainLoop;
                            }
                            String moodName = "";
                            try {
                                LocalDate date = LocalDate.parse(usrInput, dateFormatter);
                                for (int dateIdx = 0; dateIdx < moods.size(); dateIdx++) {
                                    if (moods.get(dateIdx).equalsDate(date)) {
                                        indexes.add(dateIdx);
                                    }
                                }
                            }
                            catch (DateTimeParseException e) {
                                moodName = usrInput;
                            }

                            if (!moodName.isEmpty()) {
                                try {
                                    System.out.print("Date (dd.MM.yyyy): ");
                                    LocalDate date = LocalDate.parse(sc.nextLine(), dateFormatter);
                                    System.out.print("Time (HH:mm): ");
                                    LocalTime time = LocalTime.parse(sc.nextLine(), timeFormatter);
                                    Mood testMood = new Mood(moodName, date, time);
                                    if (moods.contains(testMood)) {
                                        for (int moodIdx = 0; moodIdx < moods.size(); moodIdx++) {
                                            if (moods.get(moodIdx).equalsName(moodName) && moods.get(moodIdx).equalsDate(date) && moods.get(moodIdx).equalsTime(time)) {
                                                indexes.add(moodIdx);
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("-Mood not found-");
                                    }
                                }
                                catch (DateTimeParseException e) {
                                    System.out.println("-Incorrect input-");
                                    continue mainLoop;
                                }
                            }

                            if (indexes.isEmpty()) {
                                System.out.println("No moods were found on this date!\n");
                                continue mainLoop;
                            }
                            while(true) {
                                System.out.println();
                                for (int moodIdx = 0; moodIdx < indexes.size(); moodIdx++) {
                                    System.out.println("[" + moodIdx + "] " + moods.get(indexes.get(moodIdx)));
                                }
                                System.out.print("Select the mood index to delete (Enter for back): ");
                                try {
                                    String moodIdx = sc.nextLine();
                                    if (moodIdx.isBlank()) {
                                        System.out.println();
                                        continue mainLoop;
                                    }
                                    moods.remove(indexes.get(Integer.parseInt(moodIdx)).intValue());
                                    break;
                                }
                                catch (NumberFormatException e) {
                                    System.out.println("-You must enter a number-");
                                }
                                catch (IndexOutOfBoundsException e) {
                                    System.out.println("-Specify an existing index-");
                                }
                            }
                            System.out.println("The mood has been successfully deleted!\n");
                        }
                        case 3 -> {
                            if (moods.isEmpty()) {
                                System.out.println("The list of moods is empty\n");
                                continue mainLoop;
                            }
                            System.out.print("Mood name or date (dd.MM.yyyy): ");
                            ArrayList<Integer> indexes = searchMood(moods, dateFormatter, timeFormatter, sc);
                            if (indexes.isEmpty()) {
                                System.out.println("-Mood not found-");
                            }
                            for (int moodIdx: indexes) {
                                System.out.println(moods.get(moodIdx));
                            }
                            System.out.println();
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
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Enter a number!\n");
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
