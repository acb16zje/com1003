package assignment2017;

import static assignment2017.Connect4.HUMAN;
import static assignment2017.codeprovided.Connect4GameState.NUM_COLS;
import static assignment2017.codeprovided.Connect4GameState.RED;
import static assignment2017.codeprovided.Connect4GameState.YELLOW;

import assignment2017.codeprovided.Connect4GameState;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * A class that produce a GUI for the Connect4 gameplay
 *
 * @author Zer Jun Eng
 */
public class Connect4GameFrame extends JFrame {

    private final Connect4GameState gameState;
    private final Connect4 game;
    private final int gameMode;
    private boolean hasPlayer1Moved;
    private JLabel gameStatus;
    private JButton[] button;

    /**
     * The Connect4GameFrame constructor
     *
     * @param gameState The Connect4 game state
     * @param game The current Connect4 gameplay
     */
    public Connect4GameFrame(final Connect4GameState gameState,
                             final Connect4 game,
                             final int gameMode) {
        this.gameState = gameState;
        this.game = game;
        this.gameMode = gameMode;

        final Container contentPane = getContentPane();

        /* Create a game status panel and add it to the top of the frame */
        final JPanel topPanel = createStatusPanel();
        contentPane.add(topPanel, BorderLayout.PAGE_START);

        /* Create a game board and add it to the center of the frame */
        final Connect4GraphicalDisplay gameBoard = new Connect4GraphicalDisplay(gameState);
        contentPane.add(gameBoard, BorderLayout.CENTER);

        /* Create a row of buttons and add it to the bottom of the frame */
        final JPanel buttonsPanel = createInputPanel();
        contentPane.add(buttonsPanel, BorderLayout.PAGE_END);

        resetDefaultSetting();
        setFrameProperties();
    }

    /**
     * Sets the frame properties
     */
    private void setFrameProperties() {
        /* Screen size constants */
        final int WIDTH = 605;
        final int HEIGHT = 630;

        /* Set title, size, and location */
        final Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Connect 4");
        setSize(WIDTH, HEIGHT);
        setLocation(new Point((screenDimensions.width - WIDTH) / 2,
            (screenDimensions.height - HEIGHT) / 2)
        );

        /* Basic properties */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates a panel including game status, restart and return to main menu
     *
     * @return The top panel
     */
    private JPanel createStatusPanel() {
        final JPanel gameStatusPanel = new JPanel();
        gameStatusPanel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 0, 0, 0),
            new TitledBorder("Game Status"))
        );
        gameStatus = new JLabel();
        gameStatus.setFont(new Font("Papyrus", Font.BOLD, 18));
        gameStatusPanel.add(gameStatus);

        final JButton mainMenu = new JButton("Main Menu");
        final JButton restart = new JButton("Restart");
        mainMenu.addActionListener(new TopButtonHandler());
        restart.addActionListener(new TopButtonHandler());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(mainMenu);
        buttonPanel.add(restart);

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 2));
        topPanel.add(gameStatusPanel);
        topPanel.add(buttonPanel);

        return topPanel;
    }

    /**
     * Creates a row of buttons as the input for the column
     *
     * @return The JPanel containing row of input buttons
     */
    private JPanel createInputPanel() {
        final JPanel rowOfButtons = new JPanel(new FlowLayout());
        button = new JButton[NUM_COLS];

        for (int i = 0; i < NUM_COLS; i++) {
            button[i] = new JButton(String.valueOf(i));

            button[i].setPreferredSize(new Dimension(80, 25));
            button[i].addActionListener(new MoveButtonHandler());
            rowOfButtons.add(button[i]);
        }

        return rowOfButtons;
    }

    /**
     * Resets the game status and buttons to default
     */
    private void resetDefaultSetting() {
        /* Initial game status setting */
        if (gameMode == HUMAN) {
            gameStatus.setText("Player 1's Turn (Red)");
        } else {
            gameStatus.setText("Your Turn");
        }

        for (int i = 0; i < NUM_COLS; i++) {
            button[i].setEnabled(true);
        }
    }

    /**
     * Invoked when the restart or main menu button is clicked
     */
    private class TopButtonHandler implements ActionListener {

        /**
         * Invoked when an action occurs
         *
         * @param e Action event object
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Restart".equals(e.getActionCommand())) {
                game.guiStartGame(gameMode);
                resetDefaultSetting();
            } else {
                dispose();
                new Connect4GameModeFrame(game);

            }
        }
    }

    /**
     * Invoked when a column button is clicked
     */
    private class MoveButtonHandler implements ActionListener {
        /**
         * Update the game status if the game mode is Human vs Human
         */
        private void updateGameStatus() {
            hasPlayer1Moved = !hasPlayer1Moved;
            if (hasPlayer1Moved) {
                gameStatus.setText("Player 2's Turn (Yellow)");
            } else {
                gameStatus.setText("Player 1's Turn (Red)");
            }
        }

        /**
         * Red makes a move if yellow did not win after his move
         */
        private void redMove() {
            if (gameMode != HUMAN) {
                game.redMove();
            }

            /* Disable button for column that is full */
            for (int i = 0; i < NUM_COLS; i++) {
                if (gameState.isColumnFull(i)) {
                    button[i].setEnabled(false);
                }
            }
        }

        /**
         * Disables all button and show the winner when game is over
         */
        private void gameOver() {
            for (int i = 0; i < NUM_COLS; i++) {
                button[i].setEnabled(false);
            }

            if (gameState.getWinner() == RED) {
                gameStatus.setText("Red wins!");
            } else if (gameState.getWinner() == YELLOW) {
                gameStatus.setText("Yellow wins!");
            } else {
                gameStatus.setText("It's a tie!");
            }
        }

        /**
         * Place the move on the clicked column
         *
         * @param e Action event object
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            game.yellowMove(Integer.parseInt(e.getActionCommand()));

            if (gameMode == HUMAN) {
                updateGameStatus();
            }

            /* If yellow didn't win the game after his move, then red moves */
            if (!gameState.gameOver()) {
                redMove();
            }

            if (gameState.gameOver()) {
                gameOver();
            }
        }
    }
}
