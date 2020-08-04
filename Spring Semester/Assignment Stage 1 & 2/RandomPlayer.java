package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;


/**
 * A class that makes the move for the random computer player
 *
 * @author Zer Jun Eng
 */
public class RandomPlayer extends Connect4Player {

    /**
     * Decides which column in which to move, and then calls move
     * on the gameState instance in order to make the move.
     *
     * @param gameState the current Connect4 game state
     */
    @Override
    public void makeMove(final Connect4GameState gameState) {
        /* Random from 0 ~ 6 */
        final int column = (int) (Math.random() * 7);

        /* Try to make a available move */
        try {
            gameState.move(column);
            System.out.println("Computer dropped counter in column " + column);
        } catch (ColumnFullException | IllegalColumnException e) {
            makeMove(gameState);
        }
    }
}
