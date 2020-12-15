package GUI;

import ScoreSystem.Score;
import sessionEleven.ex1.BasicListener;
import ships.Ship;
import startSituation.RandomStart;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlayBoard {
    int amountRows;
    int amountColumns;
    final JFrame frame;
    public ArrayList<Ship> shipsOnBoard;
    public int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;

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

        //      Have to do the test for a random start
        RandomStart randomStart = new RandomStart(this.amountRows,this.amountColumns);
        this.shipsOnBoard =  randomStart.shipsOnBoard;

        //      Initialize the player scores --> have to do the check
//        Score playerOne = new Score();
//        this.scorePlayerOne = playerOne.currentScore;
//        Score playerTwo = new Score();
//        this.scorePlayerTwo = playerTwo.currentScore;

        
        for (int i = 0; i < amountRows; i++) {
            for (int j = 0; j < amountColumns; j++) {
                BoardButton button = new BoardButton(i,j);
                panel3.add(button);
            }
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

    private class BoardButton extends JButton implements ActionListener {
        int value = 0;
        int row;
        int column;

        public BoardButton(int row, int column){
            this.row = row;
            this.column = column;
            this.setBackground(Color.LIGHT_GRAY);
            this.setPreferredSize(new Dimension(10, 10));
            this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            this.addActionListener(this); // add a listener to the individual button

        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            value++;
            if (value == 1) {
                int [] triedShot = {this.row,this.column};
                for (Ship testShip : shipsOnBoard) {
                    if (testShip.checkShot(triedShot)) {
                        String nameShip = testShip.getName();
                        switch (nameShip) {
                            case "Carrier" -> {
                                this.setBackground(Color.RED);
                            }
                            case "Battleship" -> this.setBackground(Color.GREEN);
                            case "Submarine" -> this.setBackground(Color.YELLOW);
                            case "Destroyer" -> this.setBackground(Color.WHITE);
                            default -> this.setBackground(Color.BLUE);
                        }
                    }
                }
            }else{
                JOptionPane.showMessageDialog(frame, "You already clicked me. \nDon't try to cheat!","Board Button",JOptionPane.WARNING_MESSAGE);
            }
        }
    }


}
