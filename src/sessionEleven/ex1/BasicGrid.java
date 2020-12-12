package sessionEleven.ex1;

import javax.swing.*;
import java.awt.*;

public class BasicGrid {

    int amountOfPanes;

    public BasicGrid(int amountOfPanes){
        this.amountOfPanes = amountOfPanes;
        JFrame frame = new JFrame("Battleship: limited edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,500));
//        frame.getContentPane().setLayout(new GridLayout(8,8));

////      Panel 1 is the box above the grid:
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(400,100));
        panel1.setBackground(Color.WHITE);
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();
        JPanel panel14 = new JPanel();
        JPanel panel15 = new JPanel();



//      Panel 2 is the grid itself:
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(8,8));
        for (int i = 1; i <= amountOfPanes; i++){
          JButton button = new JButton();
          button.setBackground(Color.white);
          String e = String.valueOf(i);
          button.setActionCommand(e);
          button.setPreferredSize(new Dimension(50,50));

          BasicListener bl = new BasicListener(button);
          button.addActionListener(bl);
          frame.getContentPane().add(button);
        }

//        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
//        frame.getContentPane().add(panel1,Component.CENTER_ALIGNMENT);
//        frame.getContentPane().add(panel2);

        frame.pack();
        frame.setVisible(true);

    }

    private static JPanel makePane1(Color color){
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(100,100));

        return panel;
    }

    public static void main(String[] args) {
        new BasicGrid(64);
    }

}
