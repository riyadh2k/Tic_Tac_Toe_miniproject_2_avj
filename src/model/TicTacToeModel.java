package model;

import java.awt.*;

public class TicTacToeModel {
    private final char[][] board = new char[3][3];
    private boolean playerTurn = true;

    public TicTacToeModel() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public int evaluate() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'X') {
                    return -10;
                } else if (board[row][0] == 'O') {
                    return 10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'X') {
                    return -10;
                } else if (board[0][col] == 'O') {
                    return 10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') {
                return -10;
            } else if (board[0][0] == 'O') {
                return 10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') {
                return -10;
            } else if (board[0][2] == 'O') {
                return 10;
            }
        }

        return 0;
    }

    public Point findBestMove() {
        int bestVal = -1000;
        Point bestMove = new Point(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveVal = minMax(0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove.setLocation(i, j);
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minMax(int depth, boolean isMax, int alpha, int beta) {
        int score = evaluate();

        if (score == 10) return score;
        if (score == -10) return score;
        if (!isMovesLeft()) return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        best = Math.max(best, minMax(depth + 1, false, alpha, beta));
                        board[i][j] = ' ';
                        alpha = Math.max(alpha, best);
                        if (beta <= alpha) break;
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        best = Math.min(best, minMax(depth + 1, true, alpha, beta));
                        board[i][j] = ' ';
                        beta = Math.min(beta, best);
                        if (beta <= alpha) break;
                    }
                }
            }
            return best;
        }
    }

    public boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return true;
            }
        }
        return false;
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
}
