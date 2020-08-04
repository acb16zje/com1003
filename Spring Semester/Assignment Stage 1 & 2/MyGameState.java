package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.IllegalColumnException;
import assignment2017.codeprovided.IllegalRowException;
import java.util.Arrays;

/**
 * Provide implementation of the gameplay
 *
 * @author Zer Jun Eng
 */
public class MyGameState extends Connect4GameState {
    protected int[][] board;
    protected int currentTurn;

    /**
     * The MyGameState constructor
     */
    public MyGameState() {
        board = new int[NUM_ROWS][NUM_COLS];
    }

    /**
     * Starts the game. Initialises every grid position in the board to EMPTY
     * and sets the current turn to RED
     */
    @Override
    public void startGame() {
        for (final int[] row : board) {
            Arrays.fill(row, EMPTY);
        }

        currentTurn = RED;
    }

    /**
     * Drops a counter into a slot on the board.  The colour of the counter depends
     * on whose turn it is
     *
     * @param col the column in which to drop the counter, in the range 0-6
     * @throws ColumnFullException if the column denoted by col is full (i.e. the move cannot be
     * played)
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     */
    @Override
    public void move(int col) throws ColumnFullException, IllegalColumnException {
        if (isColumnFull(col)) {
            throw new ColumnFullException(col);
        }

        if (col < 0 || col > NUM_COLS - 1) {
            throw new IllegalColumnException(col);
        }

        /* Move the colour into the correct row of the input column */
        int emptyRow = 0;

        /* Get the row that is empty for the input column */
        while (getCounterAt(col, emptyRow) != EMPTY) {
            emptyRow++;
        }

        /* Replace EMPTY with the current turn colour*/
        board[emptyRow][col] = currentTurn;
        if (whoseTurn() == RED) {
            currentTurn = YELLOW;
        } else if (whoseTurn() == YELLOW) {
            currentTurn = RED;
        }
    }

    /**
     * Returns whose turn is it
     *
     * @return the constant RED if it is red's turn, else YELLOW
     */
    @Override
    public int whoseTurn() {
        return currentTurn;
    }

    /**
     * Returns a constant denoting the status of the slot at the position denoted by the
     * col and row parameters
     *
     * @param col the column of the position being queried (in the range 0-6)
     * @param row the row of the position being queried (in the range 0-5)
     * @return the EMPTY constant if the slot is empty, the RED constant if the slot is filled by a
     * red counter, the YELLOW constant if is yellow
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     * @throws IllegalRowException if row is not in the range 0-5 (i.e. an invalid row)
     */
    @Override
    public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {
        if (col < 0 || col > NUM_COLS - 1) {
            throw new IllegalColumnException(col);
        }

        if (row < 0 || row > NUM_ROWS - 1) {
            throw new IllegalRowException(row);
        }

        switch (board[row][col]) {
            case RED:
                return RED;
            case YELLOW:
                return YELLOW;
            default:
                return EMPTY;
        }
    }

    /**
     * Returns whether the board is full and the game has ended in a tie
     *
     * @return true if the board is full, else false
     */
    @Override
    public boolean isBoardFull() {
        int columnFullCounter = 0;

        /* Count the numbers of full column */
        for (int j = 0; j < board[NUM_ROWS - 1].length; j++) {
            if (board[NUM_ROWS - 1][j] != EMPTY) {
                columnFullCounter++;
            }
        }

        /* Check if all column in the last row is empty */
        return columnFullCounter == board[NUM_ROWS - 1].length;
    }

    /**
     * Returns whether the column denoted by the col parameter is full or not
     *
     * @param col the column being queried (in the range 0-6)
     * @return true if the column denoted by col is full of counters, else false
     * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
     */
    @Override
    public boolean isColumnFull(int col) throws IllegalColumnException {
        if (col < 0 || col > NUM_COLS - 1) {
            throw new IllegalColumnException(col);
        }

        /* Check if the column in the last row is empty */
        return board[NUM_ROWS - 1][col] != EMPTY;
    }

    /**
     * Indicates whether the game has been won, and by whom
     *
     * @return the constant EMPTY if there is no winner yet, else the constant RED if the red player
     * has four in a row, or the YELLOW constant if it is yellow that has won
     */
    @Override
    public int getWinner() {
        if (checkVertical() == RED || checkVertical() == YELLOW) {
            return checkVertical();
        }

        if (checkHorizontal() == RED || checkHorizontal() == YELLOW) {
            return checkHorizontal();
        }

        if (checkDiagonal() == RED || checkDiagonal() == YELLOW) {
            return checkDiagonal();
        } else {
            return EMPTY;
        }
    }

    /**
     * Checks vertical for four in a row of RED or YELLOW
     *
     * @return the constant RED if the red player has four in a row, or the YELLOW constant
     * if it is yellow that has four in a row, else return the constant EMPTY
     */
    public int checkVertical() {
        for (int i = 0; i <= NUM_ROWS - NUM_IN_A_ROW_TO_WIN; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != EMPTY
                    && board[i][j] == board[i + 1][j]
                    && board[i][j] == board[i + 2][j]
                    && board[i][j] == board[i + 3][j]) {
                    return board[i][j];
                }
            }
        }

        return EMPTY;
    }

    /**
     * Checks horizontal for four in a row of RED or YELLOW
     *
     * @return the constant RED if the red player has four in a row, or the YELLOW constant
     * if it is yellow that has four in a row, else return the constant EMPTY
     */
    public int checkHorizontal() {
        for (final int[] boardRow : board) {
            for (int j = 0; j <= NUM_COLS - NUM_IN_A_ROW_TO_WIN; j++) {
                if (boardRow[j] != EMPTY
                    && boardRow[j] == boardRow[j + 1]
                    && boardRow[j] == boardRow[j + 2]
                    && boardRow[j] == boardRow[j + 3]) {
                    return boardRow[j];
                }
            }
        }

        return EMPTY;
    }

    /**
     * Checks diagonal for four in a row of RED or YELLOW
     *
     * @return the constant RED if the red player has four in a row, or the YELLOW constant
     * if it is yellow that has four in a row, else return the constant EMPTY
     */
    public int checkDiagonal() {
        for (int i = 0; i <= NUM_ROWS - NUM_IN_A_ROW_TO_WIN; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j] != EMPTY) {
                    /* Backslash diagonal */
                    if (j <= NUM_COLS - NUM_IN_A_ROW_TO_WIN
                        && board[i][j] == board[i + 1][j + 1]
                        && board[i][j] == board[i + 2][j + 2]
                        && board[i][j] == board[i + 3][j + 3]) {
                        return board[i][j];
                    }

                    /* Forwardslash diagonal */
                    if (j >= NUM_COLS - NUM_IN_A_ROW_TO_WIN
                        && board[i][j] == board[i + 1][j - 1]
                        && board[i][j] == board[i + 2][j - 2]
                        && board[i][j] == board[i + 3][j - 3]) {
                        return board[i][j];
                    }
                }
            }
        }

        return EMPTY;
    }

    /**
     * Indicates whether the current game has finished
     *
     * @return true if there is a winner or the board is full
     */
    @Override
    public boolean gameOver() {
        return isBoardFull() || getWinner() == RED || getWinner() == YELLOW;
    }

    /**
     * Deep copies the current Connect4GameState instance into another object
     *
     * @return the new Connect4GameState instance
     */
    @Override
    public Connect4GameState copy() {
        final MyGameState gameStateCopy = new MyGameState();

        /* Deep copies the board */
        for (int i = 0; i < NUM_ROWS; i++) {
            gameStateCopy.board[i] = Arrays.copyOf(board[i], board[i].length);
        }

        /* Copies the instance variable */
        gameStateCopy.currentTurn = currentTurn;

        return gameStateCopy;
    }
}
