package assignment2017;

import static assignment2017.codeprovided.Connect4GameState.EMPTY;
import static assignment2017.codeprovided.Connect4GameState.RED;
import static assignment2017.codeprovided.Connect4GameState.YELLOW;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 * A class that produce a graphical display of the current Connect4 game
 *
 * @author Zer Jun Eng
 */
public class Connect4GraphicalDisplay extends JPanel implements Connect4Displayable {

    private final int[][] board;

    /**
     * The Connect4GraphicalDisplay constructor for creating the game board
     *
     * @param gameState The current Connect4 game state
     */
    public Connect4GraphicalDisplay(final Connect4GameState gameState) {
        board = ((MyGameState) gameState).board;
        setBackground(new Color(77, 136, 255));
    }

    /**
     * Produce a graphical display of the current Connect4 game state
     */
    @Override
    public void displayBoard() {
        repaint();
    }

    /**
     * Draw the graphical board
     *
     * @param g The Graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        displayBoard();

        final Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final Color EMPTY_COLOR = new Color(230, 230, 230);
        final double WIDTH = 85;
        final double DIAMETER = 65;
        final double OFFSET = (WIDTH - DIAMETER + 10) / 2;

        /* Creates an ellipse for the counters */
        final Ellipse2D.Double circle = new Ellipse2D.Double();
        circle.width = DIAMETER;
        circle.height = DIAMETER;

        /* Draw the board upside down */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                circle.x = j * WIDTH + OFFSET;
                circle.y = (board.length - 1 - i) * WIDTH + OFFSET;
                switch (board[i][j]) {
                    case EMPTY:
                        graphics2D.setPaint(EMPTY_COLOR);
                        break;
                    case RED:
                        graphics2D.setPaint(Color.RED);
                        break;
                    case YELLOW:
                        graphics2D.setPaint(Color.YELLOW);
                        break;
                    default:
                        graphics2D.setPaint(EMPTY_COLOR);
                        break;
                }
                graphics2D.fill(circle);
            }
        }
    }
}
