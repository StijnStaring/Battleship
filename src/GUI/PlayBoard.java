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
//        frame.setPreferredSize(new Dimension(800,900));
        frame.setTitle("Battleship: limited edition");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,5));
        panel1.setPreferredSize(new Dimension(800,50));
        JPanel panel2 = new JPanel();
        JPanel subPanel21 = new JPanel();
        subPanel21.setBorder(BorderFactory.createEmptyBorder(0,225,0,0));
        JPanel subPanel22 = new JPanel();
        subPanel22.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        JPanel subPanel23 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));
        panel2.setPreferredSize(new Dimension(800,50));
//        panel2.setBorder(BorderFactory.createEmptyBorder(0,150,0,0));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(amountRows,amountColumns));
        panel3.setPreferredSize(new Dimension(800,800));

        JLabel p1Label = new JLabel("Player one Score: ",JLabel.CENTER);
        JLabel p2Label = new JLabel("Player two Score: ",JLabel.CENTER);
        JLabel s1Label = new JLabel("1000",JLabel.RIGHT);
        JLabel s2Label = new JLabel("1000",JLabel.LEFT);
        JLabel turn = new JLabel("Turn",JLabel.CENTER);
        JLabel turnOf = new JLabel("Player 1");
        turnOf.setFont(new Font("Dialog",Font.BOLD,18));
        JButton highScore = new JButton("HighScore");
        JButton quitGame = new JButton("Quit Game");
        highScore.addActionListener(new HighScoreListener());
        quitGame.addActionListener(new QuitGameListener());

        for (int i = 0; i < amountRows*amountColumns; i++) {
            JButton button = new JButton();
            button.setBackground(Color.white);
            button.setPreferredSize(new Dimension(10, 10));
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
        subPanel21.add(s1Label);
        subPanel22.add(turnOf);
        subPanel23.add(s2Label);
        panel2.add(subPanel21);
        panel2.add(subPanel22);
        panel2.add(subPanel23);

        frame.setLayout(new BorderLayout());
        frame.add(panel1,BorderLayout.PAGE_START);
        frame.add(panel2,BorderLayout.CENTER);
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
