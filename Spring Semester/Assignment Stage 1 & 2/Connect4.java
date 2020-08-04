package assignment2017;

import static assignment2017.codeprovided.Connect4GameState.RED;
import static assignment2017.codeprovided.Connect4GameState.YELLOW;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * A class that controls the gameplay of Connect 4
 *
 * @author Zer Jun Eng
 */
public class Connect4 {

    /* Game mode constants */
    public static final int RANDOM = 0;
    public static final int INTELLIGENT = 1;
    public static final int HUMAN = 2;

    /* Instance variables */
    private Connect4Player player1;
    private final Connect4GameState gameState;
    private final Connect4Player[] red;
    private final KeyboardPlayer player2;
    private final Connect4Displayable display;

    /**
     * The Connect4 constructor
     *
     * @param gameState The initial gameState state of Connect4
     * @param red The computer player
     * @param yellow The keyboard player
     * @param display The display which produce a console output
     */
    public Connect4(final Connect4GameState gameState,
                    final Connect4Player[] red,
                    final Connect4Player yellow,
                    final Connect4Displayable display) {
        this.gameState = gameState;
        this.red = red;
        player2 = (KeyboardPlayer) yellow;
        this.display = display;
    }

    /**
     * Basic settings for starting the game on console
     */
    public void consoleStartGame() {
        gameState.startGame();

        /* Let the keyboard player select the game mode */
        player2.setGameMode();

        /* Assign player 1 based on the selected game mode */
        switch (player2.getGameMode()) {
            case RANDOM:
                player1 = red[RANDOM];
                break;
            case INTELLIGENT:
                player1 = red[INTELLIGENT];
                break;
            case HUMAN:
                player1 = player2;
                break;
            default:
                break;
        }
    }

    /**
     * The method for Connect 4 console gameplay
     */
    public void playConsole() {
        consoleStartGame();

        boolean isRedTurn = true;

        while (!gameState.gameOver()) {
            display.displayBoard();

            if (isRedTurn) {
                player1.makeMove(gameState);
            } else {
                player2.makeMove(gameState);
            }
            isRedTurn = !isRedTurn;
        }

        /* Display the end-game board and prints out the winner */
        display.displayBoard();

        if (gameState.getWinner() == RED) {
            System.out.println("R wins");
        } else if (gameState.getWinner() == YELLOW) {
            System.out.println("Y wins");
        } else {
            System.out.println("It's a tie");
        }
    }

    /**
     * Basic settings for starting the game on console
     *
     * @param gameMode The selected game mode on the GUI panel
     */
    public void guiStartGame(final int gameMode) {
        gameState.startGame();

        /* Assign player 1 based on the selected game mode */
        switch (gameMode) {
            case RANDOM:
                player1 = red[RANDOM];
                redMove(); // Red always move first
                break;
            case INTELLIGENT:
                player1 = red[INTELLIGENT];
                ((IntelligentPlayer) player1).resetOptimalMove();
                redMove(); // Red always move first
                break;
            case HUMAN:
                player1 = player2;
                break;
            default:
                break;
        }
    }

    /**
     * The method for Connect 4 GUI gameplay
     * @param gameMode The selected game mode on the GUI panel
     */
    public void playGUI(final int gameMode) {
        /* Start the main game frame */
        new Connect4GameFrame(gameState, this, gameMode);

        guiStartGame(gameMode);
    }

    /**
     * Calls the makeMove of the red's player class and update the board
     */
    public void redMove() {
        player1.makeMove(gameState);
        display.displayBoard();
    }

    /**
     * Calls the makeMove method of the KeyboardPlayer class and update the board
     * @param column The column clicked on the GUI
     */
    public void yellowMove(final int column) {
        player2.makeMove(gameState, column);
        display.displayBoard();
    }
}
