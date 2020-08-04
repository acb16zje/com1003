package ProblemSheet1;

import java.util.Scanner;

/**
 * A class for the vending machine system Created by Zer Jun Eng on 08/02/17.
 */
public class VendingMachine {

    // Static constants, variables
    private static final int COKE_PRICE = 80;
    private static final int PEPSI_PRICE = 70;
    private static final int LILT_PRICE = 60;
    private static final int SPRITE_PRICE = 50;
    private static final int CAPACITY = 5;
    private static final int NULL = -1;
    private static final int NUMBER_OF_PRODUCTS = 5;

    /**
     * A method for displaying the main menu
     *
     * @param softDrink The product array
     */
    public static void mainMenu(Scanner scan, ProductLine[] softDrink) {
        // Print out the menu
        for (int i = 0; i < softDrink.length; i++) {
            System.out.println("[" + i + "] : " + softDrink[i]);
        }
        System.out.println();

        // Get user input
        int menuSelection = UserInput.getMenuInput(scan, softDrink);

        // Case for selected option
        switch (menuSelection) {
            case 0:
            case 1:
            case 2:
            case 3:
                payingSystem(scan, softDrink, menuSelection);
                break;
            case 4:
                systemMaintenance(scan, softDrink);
        }
    }

    /**
     * A method which check whether the user has inserted enough money
     *
     * @param selectedProduct The selected product
     */
    public static void payingSystem(Scanner scan, ProductLine[] softDrink, int selectedProduct) {
        int moneyToPay = softDrink[selectedProduct].getPrice();
        int totalMoneyInserted = 0;

        System.out
            .println("You have selected to purchase: " + softDrink[selectedProduct].getName());
        while (moneyToPay > 0) {
            System.out.print("Please insert coins (" + moneyToPay + " pence remaining): ");

            // Get user input
            int moneyInserted = UserInput.getPayingInput(scan, softDrink, selectedProduct);

            // Cancel the purchase
            if (moneyInserted == 0) {
                if (totalMoneyInserted == 0) {
                    System.out.println("Purchase cancelled. Returning to main menu.\n");
                } else {
                    System.out.println(
                        "Purchase cancelled. Returning to main menu. " + totalMoneyInserted +
                            " pence will be refunded.\n");
                }
                mainMenu(scan, softDrink);
                break;
            } else if (moneyInserted < 0) {
                System.out.println("Only positive whole number of pence are accepted.");
            } else {
                totalMoneyInserted += moneyInserted;
                moneyToPay -= moneyInserted;
            }
        }

        // Purchase successful
        if (moneyToPay == 0) {
            System.out.println("Thank you for your purchase!\n");
        } else if (moneyToPay < 0) {
            System.out.println(
                "Total change: " + Math.abs(moneyToPay) + " pence. Thank you for your purchase!\n");
        }
        softDrink[selectedProduct].sell();

        // Return to the main menu
        mainMenu(scan, softDrink);
    }

    /**
     * A method for system maintenance option
     *
     * @param softDrink The product array
     */
    public static void systemMaintenance(Scanner scan, ProductLine[] softDrink) {
        int fullStockProduct = 0;

        int maintenanceSelection = UserInput.getMaintenanceInput(scan);

        // Check is everything in full amount
        for (int i = 0; i < softDrink.length - 1; i++) {
            if (softDrink[i].getAmount() == CAPACITY) {
                fullStockProduct++;
            }
        }

        switch (maintenanceSelection) {
            // Refill
            case 1:
                if (fullStockProduct
                    == NUMBER_OF_PRODUCTS - 1) // System maintenance is not a product
                {
                    System.out.println("The storage tray is full, no need to refill.\n");
                } else {
                    for (int i = 0; i < softDrink.length - 1; i++) {
                        softDrink[i].refill(CAPACITY);
                    }
                    System.out.println("Products successfully refilled.\n");
                }
                mainMenu(scan, softDrink);
                break;
            // Shutdown
            case 0:
                System.out.println("Thank you. Please come again.\n");
                System.exit(0);
                break;
            default:
                System.out.println("Returning to main menu.\n");
                mainMenu(scan, softDrink);
        }
    }

    //The main method

    /**
     * The main method for the vending machine program
     *
     * @param args String[]	Command line arguments - not used
     */
    public static void main(String[] args) {
        // Objects creation
        ProductLine[] softDrink = new ProductLine[NUMBER_OF_PRODUCTS];
        softDrink[0] = new ProductLine("Coke", COKE_PRICE, CAPACITY);
        softDrink[1] = new ProductLine("Pepsi", PEPSI_PRICE, CAPACITY);
        softDrink[2] = new ProductLine("Lilt", LILT_PRICE, CAPACITY);
        softDrink[3] = new ProductLine("Sprite", SPRITE_PRICE, CAPACITY);
        softDrink[4] = new ProductLine("System maintenance", NULL, NULL);

        // Start of the program
        Scanner scan = new Scanner(System.in);
        // Display the main menu
        mainMenu(scan, softDrink);
        // End by closing the scanner
        scan.close();
    }
}
