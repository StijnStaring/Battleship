package sessionEleven.ex1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SwingPreferredSize {

    private JFrame frame;
    private JPanel panel;

    private JLabel label1, label2, label3;
    private JButton button;

    public SwingPreferredSize(String title) {

        frame = new JFrame(title);
        //the pack() method takes into account the preferred size
        //by default, JFrame's default content pane will have a BorderLayout manager set on it
        frame.setPreferredSize(new Dimension(600,600));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,300));
        //FlowLayout is the default layout manager for every JPanel
        panel.setLayout(new BorderLayout());

        label1 = new JLabel("Label 1");
        panel.add(label1, BorderLayout.LINE_START);
        label2 = new JLabel("Label 2");
        panel.add(label2, BorderLayout.LINE_END);
        label3 = new JLabel("Label 3");
        panel.add(label3, BorderLayout.PAGE_END);

        button = new JButton("Button 1");
        button.addActionListener(new ActionListener() {
                                     public void actionPerformed (ActionEvent event){
                                         frame.dispose();
                                     }
                                 }
        );

        panel.add(button);
        //this is the same as: add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //the pack() method exists within JFrame but not within JPanel
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new SwingPreferredSize("Swing Application");
            }
        });

    }

}


