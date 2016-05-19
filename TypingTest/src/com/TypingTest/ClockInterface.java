package com.TypingTest;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
/**
 * Clock Interface which can be a common interface to any timer/clock class
 * @author Ajay
 *
 */
public interface ClockInterface extends ActionListener {
	public void start();
	public void stop();
	public void reset();
	public void registerLabel(JLabel label);
}
