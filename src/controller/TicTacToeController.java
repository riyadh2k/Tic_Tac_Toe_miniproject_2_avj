package controller;

import model.TicTacToeModel;
import model.TicTacToeModel.Difficulty;
import model.TicTacToeModel.Player;
import view.TicTacToeView;
import javax.swing.JButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeController implements ActionListener {
    private final TicTacToeModel model;
    private final TicTacToeView view;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) e.getSource();
            model.setDifficulty((Difficulty) comboBox.getSelectedItem());
        } else if (e.getSource() instanceof JButton) { // Check if the source is an instance of JButton
            JButton clickedButton = (JButton) e.getSource(); // Cast it to JButton
            Point point = view.getButtonPosition(clickedButton); // Now use the correct JButton type here
            makeMove(point.x, point.y);
        }
    }


    public void makeMove(int x, int y) {
        if (model.getBoard()[x][y] == Player.NONE) {
            model.getBoard()[x][y] = model.getCurrentPlayer();
            view.updateBoard();
            if (checkWin(x, y)) {
                view.showWinMessage(model.getCurrentPlayer());
                model.resetBoard();
                view.resetButtons();
                return;
            } else if (isBoardFull()) {
                view.showDrawMessage();
                model.resetBoard();
                view.resetButtons();
                return;
            }
            model.changePlayer();
            if (model.getCurrentPlayer() == Player.O) {
                computerPlay();
            }
        }
    }

    private void computerPlay() {
        switch (model.getDifficulty()) {
            case EASY:
                randomPlay();
                break;
            case MEDIUM:
                // Implement some medium-level logic or simply fall back to random play or hard level.
                // This can be a mix of trying to win and blocking the player.
                randomPlay();
                break;
            case HARD:
                if (!tryWin() && !blockPlayer()) {
                    randomPlay();
                }
                break;
        }
        view.updateBoard();
        if (checkWinForPlayer(Player.O)) {
            view.showWinMessage(Player.O);
            model.resetBoard();
            view.resetButtons();
        } else if (isBoardFull()) {
            view.showDrawMessage();
            model.resetBoard();
            view.resetButtons();
        } else {
            model.changePlayer();
        }
    }

    // Check for a winning condition after a move.
    private boolean checkWin(int x, int y) {
        // Horizontal check
        if (model.getBoard()[x][0] == model.getCurrentPlayer() &&
                model.getBoard()[x][1] == model.getCurrentPlayer() &&
                model.getBoard()[x][2] == model.getCurrentPlayer()) {
            return true;
        }
        // Vertical check
        if (model.getBoard()[0][y] == model.getCurrentPlayer() &&
                model.getBoard()[1][y] == model.getCurrentPlayer() &&
                model.getBoard()[2][y] == model.getCurrentPlayer()) {
            return true;
        }
        // Diagonal checks
        if (x == y && model.getBoard()[0][0] == model.getCurrentPlayer() &&
                model.getBoard()[1][1] == model.getCurrentPlayer() &&
                model.getBoard()[2][2] == model.getCurrentPlayer()) {
            return true;
        }
        if (x + y == 2 && model.getBoard()[0][2] == model.getCurrentPlayer() &&
                model.getBoard()[1][1] == model.getCurrentPlayer() &&
                model.getBoard()[2][0] == model.getCurrentPlayer()) {
            return true;
        }
        return false;
    }

    private boolean checkWinForPlayer(Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model.getBoard()[i][j] == player) {
                    if (checkWin(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model.getBoard()[i][j] == Player.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    // Computer tries to win.
    private boolean tryWin() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model.getBoard()[i][j] == Player.NONE) {
                    model.getBoard()[i][j] = Player.O;
                    if (checkWin(i, j)) {
                        return true;
                    }
                    model.getBoard()[i][j] = Player.NONE;
                }
            }
        }
        return false;
    }

    // Computer tries to block player from winning.
    private boolean blockPlayer() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model.getBoard()[i][j] == Player.NONE) {
                    model.getBoard()[i][j] = Player.X;
                    if (checkWin(i, j)) {
                        model.getBoard()[i][j] = Player.O;
                        return true;
                    }
                    model.getBoard()[i][j] = Player.NONE;
                }
            }
        }
        return false;
    }

    private void randomPlay() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (model.getBoard()[x][y] != Player.NONE);
        model.getBoard()[x][y] = Player.O;
    }
    public TicTacToeModel getModel() {
        return model;
    }

}
