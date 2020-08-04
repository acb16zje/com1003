package assignment2017;

import static assignment2017.codeprovided.Connect4GameState.NUM_COLS;
import static assignment2017.codeprovided.Connect4GameState.NUM_IN_A_ROW_TO_WIN;
import static assignment2017.codeprovided.Connect4GameState.NUM_ROWS;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;


/**
 * A class that makes the move for the intelligent computer player
 *
 * @author Zer Jun Eng
 */
public class IntelligentPlayer extends Connect4Player {

    private MyGameState gameStateCopy;
    private int[][] boardCopy = new int[NUM_ROWS][NUM_COLS];
    private int optimalMove = 3; // "3" for first move only
    private boolean isFirstMove = true;
    private final int WIN_SCORE = Integer.MAX_VALUE / MAX_DEPTH;
    private static final int EMPTY = -1;
    private static final int RED = 0;
    private static final int YELLOW = 1;
    private static final int MAX_DEPTH = 8; // 4 moves for itself, 4 moves for opponent

    /**
     * Decides which column in which to move, and then calls move on the gameState instance
     * in order to make the move. The method is free to query gameState (i.e. through the
     * getCounterAt method in order to decide which move to make)
     *
     * @param gameState the current Connect4 game state
     */
    @Override
    public void makeMove(Connect4GameState gameState) {
        /* Copy the current game state and board to calculate the optimal move */
        gameStateCopy = (MyGameState) gameState;
        boardCopy = gameStateCopy.board;

        try {
            if (isFirstMove) {
                gameState.move(optimalMove);
                isFirstMove = false;
            } else {
                gameState.move(getOptimalMove());
            }
            System.out.println("Computer dropped counter in column " + optimalMove);
        } catch (ColumnFullException | IllegalColumnException e) {
            makeMove(gameState);
        }
    }

    /**
     * Make a move into the copy of the current game board
     *
     * @param gameStateCopy The copy of the current game state
     * @param column The column to drop the counter
     */
    private void makeMoveCopy(Connect4GameState gameStateCopy, int column) {
        try {
            gameStateCopy.move(column);
        } catch (ColumnFullException | IllegalColumnException e) {
            makeMove(gameStateCopy);
        }
    }

    /**
     * Undo a move made by makeMoveCopy in a specific column
     * and set the current turn back to the previous player
     *
     * @param column The column to undo the move
     */
    private void undoMakeMoveCopy(int column) {
        /* Set the current turn back to the previous colour */
        if (gameStateCopy.whoseTurn() == RED) {
            gameStateCopy.currentTurn = YELLOW;
        } else if (gameStateCopy.whoseTurn() == YELLOW) {
            gameStateCopy.currentTurn = RED;
        }

        /* Set the slots back to empty */
        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            if (boardCopy[i][column] != EMPTY) {
                boardCopy[i][column] = EMPTY;
                break;
            }
        }
    }

    /**
     * Calculate the score of a move based
     * on how close is it to win the game, ie. more consecutive
     * counters indicates higher score
     *
     * @param countersInARow Total number of counters in a row
     * @param movesLeftToWin The estimated number of moves left to win
     */
    private int calculateScore(int countersInARow, int movesLeftToWin) {
        int moveScore = NUM_IN_A_ROW_TO_WIN - movesLeftToWin;

        switch (countersInARow) {
            case 0:
                return 0;
            case 1:
                return moveScore;
            case 2:
                return 10 * moveScore;
            case 3:
                return 100 * moveScore;
            default:
                return 1000;
        }
    }

    /**
     * Calculates counter in a row in right direction and return the score
     *
     * @return The score for right direction
     */
    private int horizontalRight() {
        int countersInARow = 1;
        int emptySlots = 0;
        int totalScore = 0;

        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = 0; j <= NUM_COLS - NUM_IN_A_ROW_TO_WIN; j++) {
                if (boardCopy[i][j] == RED) {
                    for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                        if (boardCopy[i][j + k] == RED) {
                            countersInARow++;
                        } else if (boardCopy[i][j + k] == YELLOW) {
                            /* Resets the counters if a consecutive row is blocked by
                            a different colour */
                            countersInARow = 0;
                            emptySlots = 0;
                            break;
                        } else {
                            emptySlots++;
                        }
                    }

                    totalScore += calculateHorizontalRight(i, j, emptySlots, countersInARow);
                }
            }
        }

        return totalScore;
    }

    /**
     * Calculates the score in right direction
     *
     * @param row The current row
     * @param column The current column
     * @param emptySlots The number of empty slots
     * @param countersInARow Number of counters in a row in right direction
     * @return The score
     */
    private int calculateHorizontalRight(int row, int column, int emptySlots, int countersInARow) {
        int movesLeftToWin = 0;
        int totalScore = 0;

        /* Checks for total moves left to win, e.g.
         * R R EMPTY EMPTY
         * Y Y EMPTY EMPTY
         * requires at least 4 moves for R to win
         */
        if (emptySlots > 0) {
            for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                for (int r = row; r < NUM_ROWS; r++) {
                    /* More empty slots = more moves required to form a row*/
                    if (boardCopy[row][column + k] == EMPTY) {
                        movesLeftToWin++;
                    } else {
                        break;
                    }
                }
            }
        }

        if (movesLeftToWin != 0) {
            totalScore += calculateScore(countersInARow, movesLeftToWin);
        }

        return totalScore;
    }

    /**
     * Calculates counter in a row in left direction and return the score
     *
     * @return The score for left direction
     */
    private int horizontalLeft() {
        int countersInARow = 1;
        int emptySlots = 0;
        int totalScore = 0;

        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = NUM_COLS - NUM_IN_A_ROW_TO_WIN; j < NUM_COLS; j++) {
                if (boardCopy[i][j] == RED) {
                    for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                        if (boardCopy[i][j - k] == RED) {
                            countersInARow++;
                        } else if (boardCopy[i][j - k] == YELLOW) {
                                /* Resets the counters if a consecutive row is blocked by
                                a different colour */
                            countersInARow = 0;
                            emptySlots = 0;
                            break;
                        } else {
                            emptySlots++;
                        }
                    }

                    totalScore += calculateHorizontalLeft(i, j, emptySlots, countersInARow);
                }
            }
        }

        return totalScore;
    }

    /**
     * Calculates the score in left direction
     *
     * @param row The current row
     * @param column The current column
     * @param emptySlots The number of empty slots
     * @param countersInARow Number of counters in a row in left direction
     * @return The score
     */
    private int calculateHorizontalLeft(int row, int column, int emptySlots, int countersInARow) {
        int movesLeftToWin = 0;
        int totalScore = 0;

        /* Checks for total moves left to win, e.g.
         * EMPTY EMPTY R R
         * EMPTY EMPTY Y Y
         * requires at least 4 moves for R to win
         */
        if (emptySlots > 0) {
            for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                for (int r = row; r < NUM_ROWS; r++) {
                    if (boardCopy[r][column - k] == EMPTY) {
                        movesLeftToWin++;
                    } else {
                        break;
                    }
                }
            }
        }

        if (movesLeftToWin != 0) {
            totalScore += calculateScore(countersInARow, movesLeftToWin);
        }

        return totalScore;
    }

    /**
     * Calculates counter in a row in vertical direction and return the score
     *
     * @return The score for vertical direction
     */
    private int vertical() {
        int countersInARow = 1;
        int totalScore = 0;

        for (int i = 0; i <= NUM_ROWS - NUM_IN_A_ROW_TO_WIN; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (boardCopy[i][j] == RED) {
                    for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                        if (boardCopy[i + k][j] == RED) {
                            countersInARow++;
                        } else if (boardCopy[i + k][j] == YELLOW) {
                            /* Resets the counters if a consecutive row is blocked by
                             a different colour */
                            countersInARow = 0;
                            break;
                        }
                    }

                    totalScore += calculateVertical(i, j, countersInARow);
                }
            }
        }

        return totalScore;
    }

    /**
     * Calculates the score in vertical direction
     *
     * @param row The current row
     * @param column The current column
     * @param emptySlots The number of empty slots
     * @param countersInARow Number of counters in a row in vertical direction
     * @return The score
     */
    private int calculateVertical(int row, int column, int countersInARow) {
        int movesLeftToWin = 0;
        int totalScore = 0;

        /* Checks for total moves left to win, e.g.
         * EMPTY
         * EMPTY
         * EMPTY
         * R
         * requires at least 3 moves for R to win
         */
        if (countersInARow > 0) {
            for (int r = row + NUM_IN_A_ROW_TO_WIN - 1; r > row; r--) {
                if (boardCopy[r][column] == EMPTY) {
                    movesLeftToWin++;
                } else {
                    break;
                }
            }
        }

        if (movesLeftToWin != 0) {
            totalScore += calculateScore(countersInARow, movesLeftToWin);
        }

        return totalScore;
    }

    /**
     * Calculates counter in a row in forward slash direction and return the score
     *
     * @return The score for forward slash direction
     */
    private int forwardDiagonal() {
        int countersInARow = 1;
        int emptySlots = 0;
        int totalScore = 0;

        for (int i = 0; i <= NUM_ROWS - NUM_IN_A_ROW_TO_WIN; i++) {
            for (int j = NUM_COLS - NUM_IN_A_ROW_TO_WIN; j < NUM_COLS; j++) {
                if (boardCopy[i][j] == RED) {
                    for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                        if (boardCopy[i + k][j - k] == RED) {
                            countersInARow++;
                        } else if (boardCopy[i + k][j - k] == YELLOW) {
                            countersInARow = 0;
                            emptySlots = 0;
                        } else {
                            emptySlots++;
                        }
                    }

                    totalScore += calculateForwardDiagonal(i, j, emptySlots, countersInARow);
                }
            }
        }

        return totalScore;
    }

    /**
     * Calculates the score in forward slash direction
     *
     * @param row The current row
     * @param column The current column
     * @param emptySlots The number of empty slots
     * @param countersInARow Number of counters in a row in forward slash direction
     * @return The score
     */
    private int calculateForwardDiagonal(int row, int column, int emptySlots, int countersInARow) {
        int movesLeftToWin = 0;
        int totalScore = 0;

        /* Checks for total moves left to win, e.g.
         *           EMPTY
         *     EMPTY EMPTY
         *   R EMPTY EMPTY
         * R Y EMPTY   Y
         * requires at least 6 moves for R to win
         */
        if (emptySlots > 0) {
            for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                for (int r = row + k; r < NUM_ROWS; r++) {
                    if (boardCopy[r][column - k] == EMPTY) {
                        movesLeftToWin++;
                    } else {
                        break;
                    }
                }
            }
        }

        if (movesLeftToWin != 0) {
            totalScore += calculateScore(countersInARow, movesLeftToWin);
        }

        return totalScore;
    }

    /**
     * Calculates counter in a row in backslash direction and return the score
     *
     * @return The score for backslash direction
     */
    private int backwardDiagonal() {
        int countersInARow = 1;
        int emptySlots = 0;
        int totalScore = 0;

        for (int i = 0; i <= NUM_ROWS - NUM_IN_A_ROW_TO_WIN; i++) {
            for (int j = 0; j <= NUM_COLS - NUM_IN_A_ROW_TO_WIN; j++) {
                if (boardCopy[i][j] == RED) {
                    for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                        if (boardCopy[i + k][j + k] == RED) {
                            countersInARow++;
                        } else if (boardCopy[i + k][j + k] == YELLOW) {
                            countersInARow = 0;
                            emptySlots = 0;
                        } else {
                            emptySlots++;
                        }
                    }

                    totalScore += calculateBackwardDiagonal(i, j, emptySlots, countersInARow);

                }
            }
        }

        return totalScore;
    }

    /**
     * Calculates the score in backslash direction
     *
     * @param row The current row
     * @param column The current column
     * @param emptySlots The number of empty slots
     * @param countersInARow Number of counters in a row in backslash direction
     * @return The score
     */
    private int calculateBackwardDiagonal(int row, int column, int emptySlots, int countersInARow) {
        int movesLeftToWin = 0;
        int totalScore = 0;

        /* Checks for total moves left to win, e.g.
         * EMPTY
         * EMPTY EMPTY
         * EMPTY EMPTY R
         *   Y   EMPTY Y R
         * requires at least 6 moves for R to win
         */
        if (emptySlots > 0) {
            for (int k = 1; k < NUM_IN_A_ROW_TO_WIN; k++) {
                for (int r = row + k; r < NUM_ROWS; r++) {
                    if (boardCopy[r][column + k] == EMPTY) {
                        movesLeftToWin++;
                    } else {
                        break;
                    }
                }
            }
        }

        if (movesLeftToWin != 0) {
            totalScore += calculateScore(countersInARow, movesLeftToWin);
        }

        return totalScore;
    }

    /**
     * Calculate the score of IntelligentPlayer based
     * on how close is it to win the game, ie. more consecutive
     * counters indicates higher score
     *
     * @return The score of IntelligentPlayer in current game state
     */
    private int calculateBoardScore() {
        int totalScore = 0;

        totalScore += horizontalRight();
        totalScore += horizontalLeft();
        totalScore += vertical();
        totalScore += forwardDiagonal();
        totalScore += backwardDiagonal();

        return totalScore;
    }

    /**
     * Checks preconditions before calling the minimax algorithm
     *
     * @param depth The moves ahead of the current game state
     * @param whoseTurn Whose turn is it
     * @param alpha Maximum score of the red player
     * @param beta Minimum score of the yellow player
     */
    private int preCheck(int depth, int whoseTurn, int alpha, int beta) {
        final int OPPONENT_WIN_SCORE = Integer.MIN_VALUE / MAX_DEPTH;

        if (beta <= alpha) {
            if (whoseTurn == RED) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        /* Win assured move, highest score */
        if (gameStateCopy.getWinner() == RED) {
            return WIN_SCORE;
        }

        /* Opponent win assured move, lowest score */
        if (gameStateCopy.getWinner() == YELLOW) {
            return OPPONENT_WIN_SCORE;
        }

        /* Game is draw */
        if (gameStateCopy.isBoardFull()) {
            return 0;
        }

        /* Stops recursive call after reaching max depth */
        if (depth == MAX_DEPTH) {
            return calculateBoardScore();
        }

        return Integer.MAX_VALUE / 2;
    }

    /**
     * Recursive calls itself to calculate the optimal move
     * for the IntelligentPlayer (minimax & alpha-beta pruning)
     *
     * @param depth The moves ahead of the current game state
     * @param whoseTurn Whose turn is it
     * @param alpha Maximum score of the red player
     * @param beta Minimum score of the yellow player
     */
    public int calculateOptimalMove(int depth, int whoseTurn, int alpha, int beta) {
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        int tempAlpha = alpha;
        int tempBeta = beta;

        if (preCheck(depth, whoseTurn, alpha, beta) != Integer.MAX_VALUE / 2) {
            return preCheck(depth, whoseTurn, alpha, beta);
        }

        /* Make a move in each column and find the best move */
        for (int column = 0; column < NUM_COLS; column++) {
            if (!gameStateCopy.isColumnFull(column)) {
                int currentScore;
                if (whoseTurn == RED) {
                    makeMoveCopy(gameStateCopy, column);
                    currentScore = calculateOptimalMove(depth + 1, YELLOW, tempAlpha, tempBeta);
                    if (depth == 0 && currentScore > maxScore) {
                        optimalMove = column;
                    } else if (depth == 0 && currentScore >= WIN_SCORE) {
                        undoMakeMoveCopy(column);
                        break;
                    }
                    maxScore = Math.max(currentScore, maxScore);
                    tempAlpha = Math.max(currentScore, tempAlpha);
                } else {
                    makeMoveCopy(gameStateCopy, column);
                    currentScore = calculateOptimalMove(depth + 1, RED, tempAlpha, tempBeta);
                    minScore = Math.min(currentScore, minScore);
                    tempBeta = Math.min(currentScore, tempBeta);
                }
                undoMakeMoveCopy(column);
                if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) {
                    break;
                }
            }
        }

        return whoseTurn == RED ? maxScore : minScore;
    }

    /**
     * Return the optimal move calculated by calculateOptimalMove
     *
     * @return The optimal move calculated
     */
    public int getOptimalMove() {
        calculateOptimalMove(0, RED, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return optimalMove;
    }

    /**
     * Reset the optimal move to center column
     */
    public void resetOptimalMove() {
        isFirstMove = true;
        optimalMove = 3;
    }
}
