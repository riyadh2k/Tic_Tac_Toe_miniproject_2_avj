package controller;

import model.TicTacToeModel;
import view.TicTacToeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {
    private TicTacToeModel model;
    private TicTacToeView view;

    public TicTacToeController() {}

    public void setModel(TicTacToeModel model) {
        this.model = model;
    }

    public void setView(TicTacToeView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] parts = e.getActionCommand().split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        if (model.getCell(x, y) == ' ') {
            view.setButtonText(x, y, "X");
            model.setCell(x, y, 'X');

            int boardEval = model.evaluate();

            if (boardEval == -10) {
                view.showMessage("Player wins!");
                resetGame();
                return;
            } else if (!model.isMovesLeft()) {
                view.showMessage("It's a draw!");
                resetGame();
                return;
            }

            Point computerMove = model.findBestMove();
            view.setButtonText(computerMove.x, computerMove.y, "O");
            model.setCell(computerMove.x, computerMove.y, 'O');

            boardEval = model.evaluate();

            if (boardEval == 10) {
                view.showMessage("Computer wins!");
                resetGame();
                return;
            } else if (!model.isMovesLeft()) {
                view.showMessage("It's a draw!");
                resetGame();
            }
        }
    }



    private void resetGame() {
        model = new TicTacToeModel();
        view.resetButtons();
    }
}
