package sessionEleven.ex1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleSwingSize {


    public static void main(String[] args)
    {
        JFrame f = new JFrame("Swing Application");
        f.setSize(600,600);
        f.setLayout(null); // remove this line and try again

        JPanel p = new JPanel();

        //FlowLayout is the default layout manager for every JPanel
        p.setLayout(new BorderLayout());
        p.setSize(300,300);
        JLabel label1 = new JLabel("Label 1");
        p.add(label1, BorderLayout.LINE_START);
        JLabel label2 = new JLabel("Label 2");
        p.add(label2, BorderLayout.LINE_END);
        JLabel label3 = new JLabel("Label 3");
        p.add(label3, BorderLayout.PAGE_END);

        JButton button = new JButton("Button 1");
        button.addActionListener(new ActionListener() {

        public void actionPerformed (ActionEvent event){
         //f.setVisible(false); //this doesn’t stop the program
         f.dispose(); //System.exit(1); also works
        }  // end of method

        } // end of anon. inner class

        ); // end of addActionListener method

        p.add(button);
        f.getContentPane().add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.pack();  // This is removed when the setSize function is used
        f.setVisible(true);
    }






}
