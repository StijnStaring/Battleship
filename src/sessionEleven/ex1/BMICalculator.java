package sessionEleven.ex1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BMICalculator {

    private double height, weight, BMI;

    private JFrame frame;

    private JLabel heightLabel, meterLabel, weightLabel, kgLabel;
    private JLabel BMI_label1, BMI_label2;

    private JTextField tf1, tf2, BMI_output;

    private JButton compute;

    private JPanel p1, p2, p3;

    public BMICalculator(String title) {

        frame = new JFrame();

        heightLabel = new JLabel("Height: ", JLabel.RIGHT);
        meterLabel = new JLabel (" m");
        weightLabel = new JLabel("Weight: ", JLabel.RIGHT);
        kgLabel = new JLabel (" kg");

        tf1 = new JTextField ("");
        tf2 = new JTextField ("");

        compute = new JButton ("Compute BMI");
        compute.addActionListener(new BMIButtonListener());

        BMI_label1 = new JLabel ("     BMI: ", JLabel.RIGHT);
        BMI_label2 = new JLabel ("");

        BMI_output = new JTextField ();

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        p1.add(heightLabel);
        p1.add(tf1);
        p1.add(meterLabel);
        p1.add(weightLabel);
        p1.add(tf2);
        p1.add(kgLabel);
        p1.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        p1.setLayout(new GridLayout(2,3));

        p2.add(compute);
        p2.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        p3.add(BMI_label1);
        p3.add(BMI_output);
        p3.add(BMI_label2);
        p3.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        p3.setLayout(new GridLayout (1,3));

        frame.setLayout(new GridLayout(3,1));
        frame.add(p1);
        frame.add(p2);
        frame.add(p3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class BMIButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            height = Float.valueOf(tf1.getText());
            weight = Float.valueOf(tf2.getText());

            if (weight > 0.0 && height > 0.0) {
                BMI = weight / (height * height);
                DecimalFormat df = new DecimalFormat ("0.##");
                BMI_output.setText("  " + df.format(BMI));
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid user input!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    public static void main (String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new BMICalculator("BMI Calculator");
            }
        });
    }

}
