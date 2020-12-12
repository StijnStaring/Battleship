package sessionEleven.ex1;

import javax.swing.*;
import java.awt.*;

public class BasicLayOutManager {

    public BasicLayOutManager(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(makePane1(Color.GRAY));
        panel.add(makePane1(Color.GRAY));
        panel.add(makePane1(Color.GRAY));
        panel.add(makePane1(Color.RED));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

//  Should state what will return --> this will be of type JPanel in this case. JPanel is an object of a class
    private static JPanel makePane1(Color color){
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(100,100));

        return panel;
    }

    public static void main(String[] args) {
        new BasicLayOutManager();
    }

}
