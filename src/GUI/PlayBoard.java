package GUI;

import sessionEleven.ex1.BasicListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PlayBoard {
    int amountRows;
    int amountColumns;
    private JFrame frame;

    public PlayBoard(int amountRows, int amountColumns){
        this.amountRows = amountRows;
        this.amountColumns = amountColumns;
        frame = new JFrame();
//        frame.setPreferredSize(new Dimension(800,1000));
        frame.setTitle("Battleship: limited edition");

        JPanel panel1 = new JPanel();

        panel1.setLayout(new GridLayout(2,5));
        panel1.setPreferredSize(new Dimension(800,100));
        panel1.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(amountRows,amountColumns,5,5));
        panel3.setPreferredSize(new Dimension(800,800));
        panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel p1Label = new JLabel("Player One Score:",JLabel.CENTER);
        JLabel p2Label = new JLabel("Player Two Score:",JLabel.CENTER);
        JLabel s1Label = new JLabel("1000",JLabel.CENTER);
        JLabel s2Label = new JLabel("1000",JLabel.CENTER);
        JLabel turn = new JLabel("Turn:",JLabel.CENTER);
        JLabel turnOf = new JLabel("Player 1",JLabel.CENTER);
        turnOf.setFont(new Font("Dialog",Font.BOLD,18));
        JButton highScore = new JButton("HighScore");
        JButton quitGame = new JButton("Quit Game");
        JPanel fill1 = new JPanel();
        JPanel fill2 = new JPanel();

        highScore.addActionListener(new HighScoreListener());
        quitGame.addActionListener(new QuitGameListener());

        for (int i = 0; i < amountRows*amountColumns; i++) {
            JButton button = new JButton();
            button.setBackground(Color.LIGHT_GRAY);
            button.setPreferredSize(new Dimension(10, 10));
//            button.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            String e = String.valueOf(i);
            button.setActionCommand(e);
            button.addActionListener(new BoardListener());
            panel3.add(button);
        }

        panel1.add(highScore);
        panel1.add(p1Label);
        panel1.add(turn);
        panel1.add(p2Label);
        panel1.add(quitGame);
        panel1.add(fill1);
        panel1.add(s1Label);
        panel1.add(turnOf);
        panel1.add(s2Label);
        panel1.add(fill2);

//        subPanel21.add(s1Label);
//        subPanel22.add(turnOf);
//        subPanel23.add(s2Label);
//        panel2.add(subPanel21);
//        panel2.add(subPanel22);
//        panel2.add(subPanel23);

        frame.setLayout(new BorderLayout());
        frame.add(panel1,BorderLayout.PAGE_START);
//        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel3,BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private class HighScoreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            JOptionPane.showMessageDialog(frame, "Not yet implemented!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class QuitGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            frame.dispose();
        }
    }

    private class BoardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            JOptionPane.showMessageDialog(frame, "You clicked me!","Random Button",JOptionPane.PLAIN_MESSAGE);
        }
    }



    public static void main (String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new PlayBoard(8,8);
            }
        });
    }



}
