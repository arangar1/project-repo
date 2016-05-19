package com.TypingTest;

import java.awt.event.KeyEvent;
/**
 * Interface to the controller classes so that any controller is acceptable by the views
 * @author Ajay
 *
 */
public interface ControllerInterface {
	void onReset();
	void onAction(KeyEvent ke);
}
