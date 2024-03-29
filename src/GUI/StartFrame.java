package GUI;
import highScore.HighScore;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame {

    public JFrame frame;
    public JSpinner rowSpinner;
    public JSpinner columnSpinner;
    final public static int initialRows = 8;
    public boolean equalScore = true;
    HighScore highestScores;
    String path = ""; // The path for reading the txt file to load the placement of the ships

    public StartFrame(){
        frame = new JFrame();
        frame.setTitle("Battleship: limited edition - seclection screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

//      Defining panel 1
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(800,100));
        JLabel label1 = new JLabel("Welcome to Battleship",JLabel.CENTER);
        label1.setFont(new Font("Dialog",Font.BOLD, 18));
        label1.setPreferredSize(new Dimension(800,70));
        JLabel label2 = new JLabel("Select your preferences and get started: ", JLabel.CENTER);
        label2.setFont(new Font("Dialog",Font.PLAIN, 10));
        label2.setPreferredSize(new Dimension(800,30));
        panel1.add(label1,BorderLayout.PAGE_START);
        panel1.add(label2,BorderLayout.PAGE_END);

//      Defining panel 2
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(800,400));
        panel2.setLayout(new GridLayout(5,3,50,50));
        panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel grayPanel1 = new JPanel();
        JPanel grayPanel2 = new JPanel();
        JPanel grayPanel3 = new JPanel();
        JPanel grayPanel4 = new JPanel();
        JPanel grayPanel5 = new JPanel();
        JPanel grayPanel6 = new JPanel();

//      Setting up the spinners
        SpinnerModel rowSpinnerModel = new SpinnerNumberModel(initialRows, initialRows, initialRows + 5,  1);
        SpinnerModel columnSpinnerModel = new SpinnerNumberModel(initialRows, initialRows, initialRows + 5,  1);
        rowSpinner = new JSpinner(rowSpinnerModel);
        columnSpinner = new JSpinner(columnSpinnerModel);
        JSpinner.NumberEditor rowEditor = new JSpinner.NumberEditor(rowSpinner,"#" );
        JSpinner.NumberEditor columnEditor = new JSpinner.NumberEditor(columnSpinner,"#" );
        rowSpinner.setEditor( rowEditor );
        columnSpinner.setEditor( columnEditor );
        rowSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel subPanel1 = new JPanel();
        JLabel row = new JLabel("Rows");
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        subPanel1.setLayout(new BoxLayout(subPanel1, BoxLayout.Y_AXIS));
        subPanel1.add(row);
        subPanel1.add(rowSpinner);
        JPanel subPanel2 = new JPanel();
        JLabel column = new JLabel("Columns");
        column.setAlignmentX(Component.LEFT_ALIGNMENT );
        subPanel2.setLayout(new BoxLayout(subPanel2, BoxLayout.Y_AXIS));
        subPanel2.add(column);
        subPanel2.add(columnSpinner);

//      Buttons
        JButton startGame = new JButton("Start Game");
        JButton rules = new JButton("Rules");
        JButton highScore = new JButton("High Score");
        JButton exit = new JButton("Exit");
        JButton chooseShip = new JButton("Choose Ship Placement");
        JButton chooseScoringSystem = new JButton("Choose Scoring System");
        JLabel chooseBoardSize = new JLabel("Choose board size:");

//      Listeners
        startGame.addActionListener(new startGameListener());
        rules.addActionListener(new rulesListener());
        highScore.addActionListener(new highScoreListener());
        exit.addActionListener(new exitListener());
        chooseShip.addActionListener(new chooseShipListener());
        chooseScoringSystem.addActionListener(new chooseScoringSystemListener());


//      Adding components to the frame
        panel2.add(chooseShip);
        panel2.add(chooseScoringSystem);
        panel2.add(chooseBoardSize);
        panel2.add(grayPanel1);
        panel2.add(grayPanel2);
        panel2.add(subPanel1);
        panel2.add(grayPanel3);
        panel2.add(grayPanel4);
        panel2.add(subPanel2);
        panel2.add(grayPanel5);
        panel2.add(startGame);
        panel2.add(grayPanel6);
        panel2.add(rules);
        panel2.add(highScore);
        panel2.add(exit);

        frame.setLayout(new BorderLayout());
        frame.add(panel1,BorderLayout.PAGE_START);
        frame.add(panel2,BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);

//        Creating the High Score
        highestScores = new HighScore();


    }

//       Seperate classes are created to implement the listeners

//       Start the game
    private class startGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {

            new PlayBoard((Integer) rowSpinner.getValue(),(Integer) columnSpinner.getValue(),path,equalScore,highestScores);
            path = "";
        }
    }
//       Explain the rules
    private class rulesListener implements ActionListener {
        String rulesExplained = " Here should come the rules.";

        @Override
        public void actionPerformed(ActionEvent ev) {
            JTextArea textArea = new JTextArea(10, 10);
            textArea.setText(rulesExplained);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(frame,scrollPane);

        }
    }
//       Give the High Score
    private class highScoreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {

            JOptionPane.showMessageDialog(frame,highestScores.convertToString(),"High Score", JOptionPane.INFORMATION_MESSAGE);

        }
    }
//       quit the program
    private class exitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            frame.dispose();
        }
    }
//       Import the txt file that holds the placement of the different ships
    private class chooseShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {

            JFileChooser chooseFile = new JFileChooser();
            FileNameExtensionFilter filterOptions = new FileNameExtensionFilter("Text files", "txt");
            chooseFile.setFileFilter(filterOptions);
            int returnVal = chooseFile.showOpenDialog(frame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooseFile.getSelectedFile().getAbsolutePath();

            }
        }
    }
//       Make a choice of scoring system
    private class chooseScoringSystemListener implements ActionListener {

       @Override
        public void actionPerformed(ActionEvent ev) {
            String[] possibilities = {"Equal Score System", "Adjusted Score System"};
            String output = (String)JOptionPane.showInputDialog(frame,"Which scoring system would you like to use?","Kind of scoring system",JOptionPane.QUESTION_MESSAGE,null,possibilities,"Equal Score System");
            equalScore = output.equals("Equal Score System");
        }
    }

    public static void main(String[] args) {

//      Creates a seperate thread for changing the GUI (eventloop)
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new StartFrame();
            }
        });

    }
}
