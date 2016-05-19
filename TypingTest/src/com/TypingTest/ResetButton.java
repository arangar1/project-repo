package com.TypingTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The button class which calls the controller on press. A separate class for button is to change button features
 * @author Ajay
 *
 */
public class ResetButton extends JButton implements ActionListener {
	
	ControllerInterface logic;

	/**
	 * @param str
	 */
	ResetButton(String str){
		super(str);
		addActionListener(this);
	}
	
	/**
	 * The listener is set which acts on button press
	 * @param logic
	 */
	void setListener(ControllerInterface logic){
		this.logic = logic;
	}

	/* On the button press the controllers method is called to take an action
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		logic.onReset();
	
	}
	
}
