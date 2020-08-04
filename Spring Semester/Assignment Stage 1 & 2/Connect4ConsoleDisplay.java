package assignment2017;

import static assignment2017.codeprovided.Connect4GameState.EMPTY;
import static assignment2017.codeprovided.Connect4GameState.RED;
import static assignment2017.codeprovided.Connect4GameState.YELLOW;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;

/**
 * A class that produce a console display of the current Connect4 board
 *
 * @author Zer Jun Eng
 */
public class Connect4ConsoleDisplay implements Connect4Displayable {

    private final int[][] board;

    /**
     * The Connect4ConsoleDisplay constructor
     *
     * @param gameState The current Connect4 game state
     */
    public Connect4ConsoleDisplay(final Connect4GameState gameState) {
        board = ((MyGameState) gameState).board;
    }

    /**
     * Produce a console display of the current Connect4 game state
     */
    @Override
    public void displayBoard() {
        System.out.println();

        /* Prints the board upside down */
        for (int i = board.length - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case EMPTY:
                        System.out.print("   ");
                        break;
                    case RED:
                        System.out.print(" R ");
                        break;
                    case YELLOW:
                        System.out.print(" Y ");
                        break;
                    default:
                        System.out.println("   ");
                        break;
                }
            }
            System.out.println("|");
        }

        System.out.println(" ---------------------");
        System.out.println("  0  1  2  3  4  5  6");
    }

}
