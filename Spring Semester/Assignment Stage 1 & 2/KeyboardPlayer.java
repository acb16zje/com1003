package assignment2017;

import static assignment2017.Connect4GameModeFrame.gameModeLists;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;
import java.util.Scanner;

/**
 * A class that select the game mode and
 * makes a move based on user input
 *
 * @author Zer Jun Eng
 */
public class KeyboardPlayer extends Connect4Player {

    private int gameMode;

    /**
     * Reads the input from the keyboard player
     *
     * @return The integer input value
     * @throws InvalidInputException if the input is not a number (i.e. strings, symbols, decimals)
     */
    private int readInput() throws InvalidInputException {
        final Scanner input = new Scanner(System.in);

        if (input.hasNextInt()) {
            return input.nextInt();
        } else {
            throw new InvalidInputException(input.next());
        }
    }

    /**
     * Enables the keyboard player to select three different game modes
     */
    public void setGameMode() {
        System.out.println("\n-------Welcome to Connect 4-------");

        for (int i = 0; i < gameModeLists.length; i++) {
            System.out.println("\t" + i + ". " + gameModeLists[i]);
        }

        System.out.print("Please select a game mode: ");

        try {
            gameMode = readInput();
        } catch (InvalidInputException e) {
            setGameMode();
        }
    }

    /**
     * Return the game mode selected by the keyboard player
     * @return The selected game mode
     */
    public int getGameMode() {
        return gameMode;
    }

    /**
     * Decides which column in which to move, and then calls move on the gameState instance
     * in order to make the move.  The method is free to query gameState (i.e. through the
     * getCounterAt method in order to decide which move to make)
     *
     * @param gameState the current Connect4 game state
     */
    @Override
    public void makeMove(Connect4GameState gameState) {
        int column;

        System.out.println("Please enter a column number," + " 0 to 6 followed by return.");

        /* Try to make a valid move */
        try {
            column = readInput();
            gameState.move(column);
        } catch (ColumnFullException | IllegalColumnException | InvalidInputException e) {
            makeMove(gameState);
        }
    }

    /**
     * Drops the counter to the column clicked on the GUI panel
     *
     * @param gameState The current Connect4 game state
     * @param column The column to drop the counter
     */
    public void makeMove(final Connect4GameState gameState, final int column) {
        try {
            gameState.move(column);
        } catch (ColumnFullException e) {
            System.out.println(column + " is full, the input button is now disabled.");
        } catch (IllegalColumnException e) {
            System.out.println(column + " is not a valid input, please check the GUI button");
        }
    }

}
