package main;

import controller.TicTacToeController;
import model.TicTacToeModel;
import view.TicTacToeView;

public class Main {
    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        TicTacToeController controller = new TicTacToeController(model, view);
        view.setController(controller);
    }
}
