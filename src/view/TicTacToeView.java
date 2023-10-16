package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeView {
    private final JFrame frame;
    private final JButton[][] buttons = new JButton[3][3];

    public TicTacToeView(ActionListener actionListener) {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(actionListener);
                buttons[i][j].setActionCommand(i + "," + j);
                frame.add(buttons[i][j]);
            }
        }
        frame.setVisible(true);
    }

    public void setButtonText(int x, int y, String text) {
        buttons[x][y].setText(text);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void resetButtons() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
