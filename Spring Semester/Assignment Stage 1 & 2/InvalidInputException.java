package assignment2017;

/**
 * An exception class for invalid user input
 *
 * @author Zer Jun Eng
 */
public class InvalidInputException extends Exception {

    /**
     * An exception for invalid user input, eg. Strings, symbols
     *
     * @param s The user input
     */
    public InvalidInputException(final String input) {
        super("The input " + input + " is invalid");
    }
}
