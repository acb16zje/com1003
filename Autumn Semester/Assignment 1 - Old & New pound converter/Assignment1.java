/* COM1003 Assignment 1
 * Zer Jun Eng (acb16zje)
 * Created on 16-Oct-2016.
 */

import sheffield.*;

public class Assignment1 {

    public static void main(String args[]) {
        // read the user's input
        EasyReader keyboard = new EasyReader();
        int pound = keyboard.readInt("How many pounds? ");
        int shilling = keyboard.readInt("How many shillings? ");
        int oldPence = keyboard.readInt("How many pence? ");

        // constants declaration
        final int POUND_TO_SHILLING = 20;
        final int POUND_TO_OLD_PENCE = 240;

        // convert the user input into new currency
        // prevent rounding error in addition by * 10000
        final int ERROR = 10000;
        int decimalPound = pound * ERROR;
        int decimalShilling = shilling * ERROR / POUND_TO_SHILLING;
        int decimalPence = oldPence * ERROR / POUND_TO_OLD_PENCE;
        double decimalCurrency = (double) (decimalPound + decimalShilling + decimalPence) / ERROR;

        // print the result to the screen in 2 decimal places
        EasyWriter screen = new EasyWriter();
        screen.print("That is ");
        screen.print(decimalCurrency, 2);
        screen.println(" in decimal currency");

        // read the money.txt file
        EasyReader fileInput = new EasyReader("money.txt");

        // extract the value from first line and convert it into double
        String getSentence = fileInput.readString();
        String firstValue = getSentence.substring(26, getSentence.length());
        double newMoney1 = Double.valueOf(firstValue.substring(0, firstValue.length()));

        // read the value in second line
        double newMoney2 = fileInput.readDouble();

        // separate the first(1) and second(2) value into pound
        int toOldPound1 = (int) newMoney1; // truncates and get the integer(pound)
        int toOldPound2 = (int) newMoney2;

        // amount of new pence left; prevent rounding error in subtraction
        final int R_ERROR = 100;
        int newPenceLeft1 = (int) Math.round((newMoney1 - toOldPound1) * R_ERROR);
        int newPenceLeft2 = (int) Math.round((newMoney2 - toOldPound2) * R_ERROR);

        // constants for conversion
        final double NEW_PENCE_TO_SHILLING = 1 / 5.0;
        final int SHILLING_TO_OLD_PENCE = 12;

        // separate the first(1) and second(2) value into shilling
        double fullShilling1 =
            newPenceLeft1 * NEW_PENCE_TO_SHILLING; // convert new pence to shilling
        int toOldShilling1 = (int) fullShilling1; // truncates and get the integer(shilling)

        double fullShilling2 = newPenceLeft2 * NEW_PENCE_TO_SHILLING;
        int toOldShilling2 = (int) fullShilling2;

        // separate the first(1) and second(2) value into old pence
        double leftOver1 =
            fullShilling1 - toOldShilling1; // leftOver is the decimals truncated above
        int toOldPence1 = (int) (leftOver1
            * SHILLING_TO_OLD_PENCE); // convert shilling to old pence

        double leftOver2 = fullShilling2 - toOldShilling2;
        int toOldPence2 = (int) (leftOver2 * SHILLING_TO_OLD_PENCE);

        // print the first value's result in Lsd form
        System.out.println(
            firstValue + " in old money is L" + toOldPound1 + "." + toOldShilling1 + "s."
                + toOldPence1 + "d"
        );

        // print a table
        System.out.println("   Old    L  s  d");
        screen.print(newMoney1, 2, 6);
        screen.print(toOldPound1, 5);
        screen.print(toOldShilling1, 3);
        screen.println(toOldPence1, 3);

        screen.print(newMoney2, 2, 6);
        screen.print(toOldPound2, 5);
        screen.print(toOldShilling2, 3);
        screen.println(toOldPence2, 3);
    }
}
