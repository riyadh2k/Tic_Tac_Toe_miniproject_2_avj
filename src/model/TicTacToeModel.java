package model;

import java.awt.*;

public class TicTacToeModel {
    private final char[][] board = new char[3][3];
    private boolean playerTurn = true;
    private String playerName;

    public TicTacToeModel() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Check rows, columns, diagonals for a win or a tie
    public int evaluate() {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'X') return 10;
                else if (board[i][0] == 'O') return -10;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'X') return 10;
                else if (board[0][i] == 'O') return -10;
            }
        }

        // Diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') return 10;
            else if (board[0][0] == 'O') return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') return 10;
            else if (board[0][2] == 'O') return -10;
        }

        return 0;
    }

    // Use MinMax to predict the computer's best move
    public Point findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        Point bestMove = new Point(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X'; // Try player's move
                    int moveVal = minMax(0, false);
                    board[i][j] = ' '; // Reset cell
                    if (moveVal > bestVal) {
                        bestMove.setLocation(i, j);
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minMax(int depth, boolean isMax) {
        int score = evaluate();

        // Maximizer has won
        if (score == 10) return score;

        // Minimizer has won
        if (score == -10) return score;

        // If there are no more moves and no winner
        if (isMovesLeft()) return 0;

        // Maximizing player
        int best;
        if (isMax) {
            best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        best = Math.max(best, minMax(depth + 1, false));
                        board[i][j] = ' ';
                    }
        }
        // Minimizing player
        else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        best = Math.min(best, minMax(depth + 1, true));
                        board[i][j] = ' ';
                    }
        }
        return best;
    }

    public boolean isMovesLeft() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public char getCell(int x, int y) {
        return board[x][y];
    }

    public void setCell(int x, int y, char value) {
        board[x][y] = value;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void togglePlayerTurn() {
        playerTurn = !playerTurn;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }
}

