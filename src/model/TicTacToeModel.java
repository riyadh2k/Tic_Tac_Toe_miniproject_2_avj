package model;

public class TicTacToeModel {
    public enum Player {
        X, O, NONE
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private Player currentPlayer;
    private final Player[][] board;
    private Difficulty difficulty;

    public TicTacToeModel() {
        this.board = new Player[3][3];
        resetBoard();
        this.difficulty = Difficulty.MEDIUM;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Player.NONE;
            }
        }
        currentPlayer = Player.X;
    }

    public Player[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
