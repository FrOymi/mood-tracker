package ui.tui;

import objects.*;

import java.util.Scanner;

public class Menu extends MenuPrinter {
    public static void runMenu() {
        Scanner sc = new Scanner(System.in);
        Time actualTime = new Time();
        int menuPage = 0;
        int usrAction = 0;

        printGreeting(actualTime);
        mainLoop:
        while(true) {
            try{
                printMenu(menuPage);
                usrAction = Integer.parseInt(sc.nextLine());
                if (usrAction > 4 || usrAction < 0) System.out.println("Select an existing menu item!");
            }
            catch (NumberFormatException e) {
                System.out.println("Enter the number!");
            }

            switch (usrAction) {
                case 1 -> {
                    //TODO: реализовать добаление настроений
                }
                case 2 -> {
                    //TODO: реализоать удаление настроений
                }
                case 3 -> {
                    //TODO: реализоать поиск настроений
                }
                case 4 -> {
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
