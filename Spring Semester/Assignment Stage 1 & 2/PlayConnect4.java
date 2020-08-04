package assignment2017;

import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * A class to start the Connect 4 game
 *
 * @author Zer Jun Eng
 */
public class PlayConnect4 {

    /**
     * The main method for Connect 4
     *
     * @param args "-gui" for GUI and "-nogui" for console
     */
    public static void main(final String[] args) {
        final Connect4GameState gameState = new MyGameState();

        final Connect4Player[] red = {
            new RandomPlayer(),
            new IntelligentPlayer()
        };

        final Connect4Player yellow = new KeyboardPlayer();

        if ("-gui".equals(args[0])) {
            Connect4GraphicalDisplay graphicalDisplay = new Connect4GraphicalDisplay(gameState);
            Connect4 game = new Connect4(gameState, red, yellow, graphicalDisplay);
            new Connect4GameModeFrame(game); // Opens the game mode frame
        } else if ("-nogui".equals(args[0])) {
            Connect4ConsoleDisplay consoleDisplay = new Connect4ConsoleDisplay(gameState);
            Connect4 game = new Connect4(gameState, red, yellow, consoleDisplay);
            game.playConsole();
        }
    }
}
