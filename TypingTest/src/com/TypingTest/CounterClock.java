package com.TypingTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

/**
 * Clock or timer class implementing the ClockInterface to include any type of time to the classes
 * @author Ajay
 *
 */
public class CounterClock implements ClockInterface {
    final int START_TIME = 0;
    int counter = START_TIME;
    javax.swing.Timer refreshTimer;
    JLabel countdownTimerField ;

    /* (non-Javadoc)
     * @see com.TypingTest.ClockInterface#registerLabel(javax.swing.JLabel)
     */
    @Override
    public void registerLabel(JLabel f){
    	refreshTimer = new javax.swing.Timer(1000, this);
        countdownTimerField = f;
        countdownTimerField.setText(Integer.toString(counter));
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
    	counter++;
        if (counter >= 0){
            countdownTimerField.setText(Integer.toString(counter));
        }
    }

    /* To start the timer
     * @see com.TypingTest.ClockInterface#start()
     */
    public void start(){
        refreshTimer.start();
    }
    /* To stop the timer
     * @see com.TypingTest.ClockInterface#stop()
     */
    public void stop(){
        refreshTimer.stop();
    }
    /* To reset the timer
     * @see com.TypingTest.ClockInterface#reset()
     */
    public void reset(){
    	refreshTimer.stop();
        counter = START_TIME;
        countdownTimerField.setText(Integer.toString(counter));
    }

}
