package main;

import controller.TicTacToeController;
import model.TicTacToeModel;
import view.TicTacToeView;

public class Main {
    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeController controller = new TicTacToeController();
        controller.setModel(model);
        TicTacToeView view = new TicTacToeView(controller);
        controller.setView(view);
    }
}
