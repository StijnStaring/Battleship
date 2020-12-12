package sessionEleven.ex1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
/*www .j a  v a2  s.  co m*/
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
    public static Component getButtonLayout(int num) {
        JPanel p = new JPanel(new BorderLayout(3, 3));

        p.add(new JLabel("Label " + num), BorderLayout.NORTH);

        JPanel b = new JPanel(new GridLayout(1, 0, 25, 5));
        for (int ii = 1; ii < 4; ii++) {
            b.add(new JButton("Button " + ii));
        }
        p.add(b, BorderLayout.CENTER);

        return p;
    }

    public static void main(String[] args) {
        JPanel gui = new JPanel(new GridLayout(0, 1, 3, 15));
        for (int ii = 1; ii < 4; ii++) {
            gui.add(getButtonLayout(ii));
        }
        JOptionPane.showMessageDialog(null, gui);
    }
}
