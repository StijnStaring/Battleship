package sessionEleven.ex1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class KeyBoard {

    private JFrame frame;
    private JTextArea text;
    private JPanel keyPanel, displayPanel;

    private final String SPACE = "Space";
    private final String ENTER = "Enter";

    private String[] firstSymbols = {"A", "Z", "E", "R", "T", "Y", "U", "I", "O", "P"};
    private String[] secondSymbols = {"Q", "S", "D", "F", "G", "H", "J", "K", "L", "M"};
    private String[] thirdSymbols = {"W", "X", "C", "V", "B", "N", SPACE, ENTER};

    //no need to create 20+ separate listener objects
    private CustomButtonListener listen = new CustomButtonListener();

    public KeyBoard(String title) {
        this.frame = new JFrame(title);

        this.text = new JTextArea(15, 50);

        this.displayPanel = new JPanel();
        this.displayPanel.setPreferredSize(new Dimension(500, 300));
        this.displayPanel.add(this.text);

        this.keyPanel = new JPanel();
        this.keyPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        //exercise: the code to put buttons onto the panel (and where to put them)
        //can certainly be made cleaner; look at all this code duplication
        //try to figure out a better way (that is still readable) to do this

        int counter = 0;
        for (String symbol : this.firstSymbols) {
            JButton button = new JButton(symbol);
            button.addActionListener(listen);
            c.gridx = counter;
            c.gridy = 0;
            this.keyPanel.add(button, c);
            counter++;
        }

        counter = 0;
        for (String symbol : this.secondSymbols) {
            JButton button = new JButton(symbol);
            button.addActionListener(listen);
            c.gridx = counter;
            c.gridy = 1;
            this.keyPanel.add(button, c);
            counter++;
        }

        counter = 0;
        for (int i = 0; i <= 5; i++) {
            JButton button = new JButton(this.thirdSymbols[i]);
            button.addActionListener(listen);
            c.gridx = counter;
            c.gridy = 2;
            this.keyPanel.add(button, c);
            counter++;
        }
        JButton button = new JButton(this.thirdSymbols[6]);
        button.addActionListener(listen);
        c.gridx = counter;
        c.gridy = 2;
        c.gridwidth = 2;
        this.keyPanel.add(button, c);
        counter += 2;

        button = new JButton(this.thirdSymbols[7]);
        button.addActionListener(listen);
        c.gridx = counter;
        c.gridy = 2;
        c.gridwidth = 2;
        this.keyPanel.add(button, c);

        frame.add(this.displayPanel, BorderLayout.PAGE_START);
        frame.add(this.keyPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private class CustomButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String action = ((JButton)e.getSource()).getText();
            if (action.equals(SPACE)) {
                text.append(" ");
            } else if (action.equals(ENTER)) {
                text.append("\n");
            } else {
                text.append(action);
            }

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new KeyBoard("My own custom keyboard");
            }
        });
    }

}
