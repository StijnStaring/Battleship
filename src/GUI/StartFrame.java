package GUI;
import javax.swing.*;
import java.awt.*;

public class StartFrame {

    public JFrame frame;
    final public static int initialRows = 8;

    public StartFrame(){
        frame = new JFrame();
        frame.setTitle("Battleship: limited edition - seclection screen");
//        frame.setPreferredSize(new Dimension(800,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(800,400));
        panel2.setLayout(new GridLayout(5,3,50,50));
        panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton chooseShip = new JButton("Choose Ship Placement");
        JButton chooseScoringSytem = new JButton("Choose Scoring System");
        JLabel chooseBoardsize = new JLabel("Choose board size: Rows & columns:");
        JPanel grayPanel1 = new JPanel();
        JPanel grayPanel2 = new JPanel();
        JPanel grayPanel3 = new JPanel();
        JPanel grayPanel4 = new JPanel();
        JPanel grayPanel5 = new JPanel();
        JPanel grayPanel6 = new JPanel();
        SpinnerModel rowSpinnerModel = new SpinnerNumberModel(initialRows, initialRows + 0, initialRows + 5,  1);
        SpinnerModel columnSpinnerModel = new SpinnerNumberModel(initialRows, initialRows + 0, initialRows + 5,  1);
        JSpinner rowSpinner = new JSpinner(rowSpinnerModel);
        JSpinner columnSpinner = new JSpinner(columnSpinnerModel);
        JSpinner.NumberEditor rowEditor = new JSpinner.NumberEditor(rowSpinner,"#" );
        JSpinner.NumberEditor columnEditor = new JSpinner.NumberEditor(columnSpinner,"#" );
        rowSpinner.setEditor( rowEditor );
        columnSpinner.setEditor( columnEditor );
        JButton startGame = new JButton("Start Game");
        JButton rules = new JButton("Rules");
        JButton highScore = new JButton("High Score");
        JButton exit = new JButton("Exit");


//        subpanelrow.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel2.add(chooseShip);
        panel2.add(chooseScoringSytem);
        panel2.add(chooseBoardsize);
        panel2.add(grayPanel1);
        panel2.add(grayPanel2);
        panel2.add(rowSpinner);
        panel2.add(grayPanel3);
        panel2.add(grayPanel4);
        panel2.add(columnSpinner);
        panel2.add(grayPanel5);
        panel2.add(startGame);
        panel2.add(grayPanel6);
        panel2.add(rules);
        panel2.add(highScore);
        panel2.add(exit);


        frame.setLayout(new BorderLayout());
        frame.add(panel1,BorderLayout.PAGE_START);
        frame.add(panel2,BorderLayout.PAGE_END);



//        JPanel grayPanel = new JPanel();



        frame.pack();
        frame.setVisible(true);







    }

    public static void main(String[] args) {
        new StartFrame();
    }





}
