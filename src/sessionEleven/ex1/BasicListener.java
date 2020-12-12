package sessionEleven.ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicListener implements ActionListener {

//  Have the default constructor
    public BasicListener(JButton  button){

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String action = ev.getActionCommand();
        if (action.equals(String.valueOf(1)))
            JOptionPane.showMessageDialog(null, "You clicked me!","HighScore",JOptionPane.PLAIN_MESSAGE);

    }
}
