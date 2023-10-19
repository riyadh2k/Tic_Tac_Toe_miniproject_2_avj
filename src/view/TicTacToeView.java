package view;

import controller.TicTacToeController;
import model.TicTacToeModel;
import model.TicTacToeModel.Difficulty;
import model.TicTacToeModel.Player;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TicTacToeView {
    private final JFrame frame;
    private final JButton[][] buttons;
    private TicTacToeController controller;
    private final JComboBox<Difficulty> difficultyComboBox;
    private final Map<Point, JButton> buttonPoints;

    public TicTacToeView() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 350);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        buttonPoints = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                boardPanel.add(buttons[i][j]);
                buttonPoints.put(new Point(i, j), buttons[i][j]);
            }
        }

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new FlowLayout());
        difficultyComboBox = new JComboBox<>(Difficulty.values());
        settingsPanel.add(new JLabel("Difficulty:"));
        settingsPanel.add(difficultyComboBox);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(settingsPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void setController(TicTacToeController controller) {
        this.controller = controller;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].addActionListener(controller);
            }
        }
        difficultyComboBox.addActionListener(controller);
    }

    public void updateBoard() {
        Player[][] board = controller.getModel().getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != Player.NONE) {
                    buttons[i][j].setText(board[i][j].toString());
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    public void showWinMessage(Player winner) {
        JOptionPane.showMessageDialog(frame, winner + " Wins!", "Victory", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showDrawMessage() {
        JOptionPane.showMessageDialog(frame, "It's a draw!", "Draw", JOptionPane.INFORMATION_MESSAGE);
    }

    public Point getButtonPosition(JButton source) {
        for (Map.Entry<Point, JButton> entry : buttonPoints.entrySet()) {
            if (entry.getValue() == source) {
                return entry.getKey();
            }
        }
        return null;
    }



    public void resetButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public TicTacToeModel getModel() {
        return controller.getModel();
    }
}
