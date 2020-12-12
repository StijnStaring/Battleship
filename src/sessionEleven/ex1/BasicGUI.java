package sessionEleven.ex1;

import javax.swing.*;
import java.awt.*;

public class BasicGUI {
    public BasicGUI() {
        JFrame frame = new JFrame("First GUI example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,360);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(700,700));
        JLabel label = new JLabel("Hello Stijn");
        panel.add(label);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);

    }

}
