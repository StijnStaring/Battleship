package sessionEleven.TicTacToe;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
	ImageIcon X,O;
	byte value=0;
	/*
	0:nothing
	1:X
	2:O
	*/
	
	public XOButton(){
		X=new ImageIcon(this.getClass().getResource("X.png"));
		O=new ImageIcon(this.getClass().getResource("O.png"));
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		value++;
		value%=3;
		switch (value) {
			case 0 -> setIcon(null);
			case 1 -> setIcon(X);
			case 2 -> setIcon(O);
		}
	}
}