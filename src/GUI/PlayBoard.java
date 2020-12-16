package GUI;

import highScore.HighScore;
import scoreSystem.Score;
import ships.Ship;
import startSituation.DefinedStart;
import startSituation.RandomStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayBoard {
    int amountRows;
    int amountColumns;
    final JFrame frame;
    public ArrayList<Ship> shipsOnBoard; // ArrayList of the ships that are placed on the board
    public String nowTurnOf = "Player 1";
    public Score scorePlayerOne;
    public Score scorePlayerTwo;
    JLabel turnOf;
    JLabel s1Label;
    JLabel s2Label;
    int AMOUNT_OF_SHIPS = 4;
    HighScore highestScores;

    public PlayBoard(int amountRows, int amountColumns, String path, boolean equalScore, HighScore highestScores){
        this.amountRows = amountRows;
        this.amountColumns = amountColumns;
        this.highestScores = highestScores;

//      Check if the user provided a file for the defined start, which defines the placement of the ships.
//      Otherwise, place the ships randomly on the board.
        if(path.equals("")){
            RandomStart randomStart = new RandomStart(this.amountRows,this.amountColumns);
            this.shipsOnBoard =  randomStart.shipsOnBoard;
        }else{
            DefinedStart ds = new DefinedStart(path);
            this.amountRows = ds.amountRows;
            this.amountColumns = ds.amountColumns;
            if(ds.feasible){
                this.shipsOnBoard = ds.shipsOnBoard;
            }else{
                RandomStart randomStart = new RandomStart(this.amountRows,this.amountColumns);
                this.shipsOnBoard =  randomStart.shipsOnBoard;
            }
        }

//      Make the board itself
        frame = new JFrame();
        frame.setSize(new Dimension(600,800));
        frame.setResizable(false);
        frame.setTitle("Battleship: limited edition");

//      Use one panel for the display and one panel for the grey buttons
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,5));
        panel1.setPreferredSize(new Dimension(600,100));
        panel1.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(this.amountRows,this.amountColumns,5,5));
        panel3.setPreferredSize(new Dimension(600,600));
        panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel p1Label = new JLabel("Player One Score:",JLabel.CENTER);
        JLabel p2Label = new JLabel("Player Two Score:",JLabel.CENTER);
        s1Label = new JLabel("0",JLabel.CENTER);
        s2Label = new JLabel("0",JLabel.CENTER);
        JLabel turn = new JLabel("Turn:",JLabel.CENTER);
        turnOf = new JLabel(nowTurnOf,JLabel.CENTER);
        turnOf.setFont(new Font("Dialog",Font.BOLD,18));
        JButton highScore = new JButton("HighScore");
        JButton quitGame = new JButton("Quit Game");
        JPanel fill1 = new JPanel();
        JPanel fill2 = new JPanel();

        highScore.addActionListener(new HighScoreListener());
        quitGame.addActionListener(new QuitGameListener());

//      Initialize the player scores.
//      Check which scoring system is used.
        if (equalScore){
            this.scorePlayerOne = new Score();
        }else{
            this.scorePlayerOne = new Score(11,16,21,26);
        }
        this.scorePlayerTwo = new Score();

        for (int i = 0; i < this.amountRows; i++) {
            for (int j = 0; j < this.amountColumns; j++) {
                BoardButton button = new BoardButton(i,j);
                panel3.add(button);
            }
        }

//      Add everything to the panels
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
        frame.add(panel3,BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//       Seperate classes are created to implement the listeners

    private class HighScoreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {

            JOptionPane.showMessageDialog(frame,highestScores.convertToString(),"High Score", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class QuitGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            highestScores.saveHighscore();
            frame.dispose();
        }
    }

//       This class represents the functionality of one of the playing buttons
    private class BoardButton extends JButton implements ActionListener {
        int value = 0;
        int row;
        int column;

        public BoardButton(int row, int column){
            this.row = row;
            this.column = column;
            this.setBackground(Color.LIGHT_GRAY);
            this.setPreferredSize(new Dimension(7, 7));
            this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            this.addActionListener(this); // add a listener to the individual button

        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            value++; // With the help of values it is checked if the button already was puched before
            if (value != 1) {
                JOptionPane.showMessageDialog(frame, "You already clicked me. \nDon't try to cheat!","Board Button",JOptionPane.WARNING_MESSAGE);

            }else{
                int[] shot =  {this.row,this.column}; // A shot are the coördinates of a tile that was clicked on
                String hitShip; // HitShip stores which ship has been hit
                // One case updates the score if necessary and changes the player that can shoot (click)
                switch (nowTurnOf){
                    case "Player 1" -> {
                        hitShip = scorePlayerOne.updateScore(shot,shipsOnBoard);
                        s1Label.setText(String.valueOf(scorePlayerOne.currentScore));
                        nowTurnOf = "Player 2";
                        turnOf.setText("Player 2");
                    }
                    case "Player 2" -> {
                        hitShip = scorePlayerTwo.updateScore(shot,shipsOnBoard);
                        s2Label.setText(String.valueOf(scorePlayerTwo.currentScore));
                        nowTurnOf = "Player 1";
                        turnOf.setText("Player 1");
                    }
                    default -> throw new IllegalArgumentException("Ambiguity who its turn it is!");

                }
                // Switch the color of the tile on the board
                switch (hitShip) {
                    case "Carrier" -> this.setBackground(Color.RED);
                    case "Battleship" -> this.setBackground(Color.GREEN);
                    case "Submarine" -> this.setBackground(Color.YELLOW);
                    case "Destroyer" -> this.setBackground(Color.WHITE);
                    default -> this.setBackground(Color.BLUE);
                }
            }
            // Do the check if there are still ships that are not completely damaged
            int count = 0;
            for(Ship testShip: shipsOnBoard){
                if(testShip.isSunk()){
                    count++;
                }
            }
            // If no ships are left, the game ends
            // It is checked who has one and the high score is updated with the score of the winner if he makes the top five of played games
            if (count == AMOUNT_OF_SHIPS){
                if(scorePlayerOne.currentScore > scorePlayerTwo.currentScore){
                    highestScores.updateHighScore(scorePlayerOne);
                    JOptionPane.showMessageDialog(frame,"The winner is: Player 1!","Game Over", JOptionPane.INFORMATION_MESSAGE);
                }else if(scorePlayerOne.currentScore < scorePlayerTwo.currentScore){
                    highestScores.updateHighScore(scorePlayerTwo);
                    JOptionPane.showMessageDialog(frame,"The winner is: Player 2!","Game Over", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    highestScores.updateHighScore(scorePlayerOne);
                    JOptionPane.showMessageDialog(frame,"Tie - Play another time to decide the real winner!","Game Over", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }
}
