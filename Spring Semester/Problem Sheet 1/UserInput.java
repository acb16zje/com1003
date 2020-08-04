package ProblemSheet1;

import java.util.Scanner;

/**
 * A class for reading and scanning user input Created by Zer Jun Eng on 08/02/17.
 */

public class UserInput {

    // Static constants
    private static final int NULL = -1;

    /**
     * A method for reading the user input in main menu
     *
     * @param softDrink The product array
     * @return The user's selection in the menu
     */
    public static int getMenuInput(Scanner scan, ProductLine[] softDrink) {
        int menuSelection;
        boolean validInput = false;

        // Ask for user input on the initial menu
        do {
            System.out.print("Please make your selection: ");

            // Check for valid input
            while (!scan.hasNextInt()) {
                System.out.println("That is not a valid selection.");
                System.out.print("Please make a valid selection only: ");
                scan.next();
            }
            menuSelection = scan.nextInt();

            // If user's input is out of range
            if (menuSelection > softDrink.length - 1 || menuSelection < 0) {
                validInput = false;
                System.out.println("That input is out of range.");
            } else {
                // Check whether the chosen product is available
                if (softDrink[menuSelection].getAmount() > 0) {
                    validInput = true;
                } else if (softDrink[menuSelection].getAmount() == 0) {
                    System.out.println("Sorry, that product is currently not available.");
                    validInput = false;
                } else if (softDrink[menuSelection].getAmount() == NULL) {
                    break;
                }
            }
        } while (!validInput);

        return menuSelection;
    }

    /**
     * A method for reading the user input in paying system
     *
     * @param softDrink The product array
     * @param selectedProduct The product selected by the user
     * @return The money inserted by the user
     */
    public static int getPayingInput(Scanner scan, ProductLine[] softDrink, int selectedProduct) {
        int moneyToPay = softDrink[selectedProduct].getPrice();
        int moneyInserted;

        // Check for valid input
        while (!scan.hasNextInt()) {
            System.out.println("Only positve whole number of pence are accepted.");
            System.out.print("Please insert coins (" + moneyToPay + " pence remaining): ");
            scan.next();
        }
        moneyInserted = scan.nextInt();

        return moneyInserted;
    }

    /**
     * A method for reading the user input in paying system
     *
     * @return The user selection in the maintenance part
     */
    public static int getMaintenanceInput(Scanner scan) {
        // Constants for selections, variables
        final int SHUTDOWN = 0;
        final int REFILL = 1;

        System.out.println("\nInput other integers to return to main menu.");
        System.out.println("[" + SHUTDOWN + "] : Shutdown");
        System.out.println("[" + REFILL + "] : Refill\n");
        System.out.print("Please make your selection: ");

        // Check for valid input
        while (!scan.hasNextInt()) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("Refill (" + REFILL + ") or shutdown (" + SHUTDOWN + ")? ");
            scan.next();
        }
        int maintenanceSelection = scan.nextInt();

        return maintenanceSelection;
    }
}
