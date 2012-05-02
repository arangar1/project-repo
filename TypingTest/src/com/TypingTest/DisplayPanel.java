package com.TypingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This view is the side panel which is updated with the statistical information through the controller
 * @author Ajay
 *
 */
public class DisplayPanel extends JPanel {
	
	private JLabel charCountLabel;
	private JLabel timeLabel;
	private ResetButton jButton;
	private ClockInterface timer;
	
	/**
	 * gets the string from the character count label
	 * @return
	 */
	public String getCharCountLabelString() {
		return charCountLabel.getText();
	}

	/**
	 * @param charCountLabel
	 */
	public void setCharCountLabel(String charCountLabel) {
		this.charCountLabel.setText(charCountLabel);
	}

	/**
	 * gets the string from the timer count label
	 * @return
	 */
	public String getTimeLabelString() {
		return timeLabel.getText();
	}

	/**
	 * @param timeLabel
	 */
	public void setTimeLabel(String timeLabel) {
		this.timeLabel.setText(timeLabel);
	}
	
	/**
	 * @param timer
	 */
	public void setTimer(ClockInterface timer){
		this.timer = timer;
		timer.registerLabel(timeLabel);
	}
	
	/**
	 * Constructor
	 * 
	 */
	DisplayPanel(){
		super();
		initComponents();
	}

	/**
	 * Initializes all the components required by the Display Panel
	 * Returns null
	 */
	void initComponents(){
 
		ImageIcon image = new ImageIcon("clock.jpg");
		timeLabel = new JLabel(image);
		timeLabel.setHorizontalTextPosition(JLabel.CENTER);
		timeLabel.setVerticalTextPosition(JLabel.CENTER);
		timeLabel.setOpaque(false);
		timeLabel.setFont(new Font("Boulder", Font.BOLD, 35));

		setPreferredSize(new Dimension(150,400));
		setLayout(new BorderLayout());

		jButton = new ResetButton("Reset");

		charCountLabel = new JLabel();
		charCountLabel.setText("           Count");
		charCountLabel.setFont(new Font("Boulder", Font.BOLD, 14));
		add("North",timeLabel);
		add("Center",charCountLabel);
		add("South",jButton);

		setBorder(BorderFactory.createTitledBorder("Status Info"));
		setBackground(Color.white);
		
	}
	
	/**
	 * registers the button with the listener (controller)
	 * @param l
	 */
	void registerButtonWithListener(ControllerInterface l){
		jButton.setListener(l);
	}
	
	/**
	 * called when the result is populated
	 */
	void onResult(){
		timer.stop();
		setFocusable(true);
	}
	
	/**
	 * starts the timer
	 */
	void startTimer(){
		timer.start();
	}
	
	/**
	 * Called when reset button is clicked
	 */
	void onReset(){
		remove(timeLabel);
		remove(charCountLabel);
		remove(jButton);
		initComponents();
	}

}
