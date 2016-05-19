package com.TypingTest;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * The Main Frame what the user see's. It puts together all the other Panels to show the typing test interface
 * @author Ajay
 *
 */
public class MainFrame extends JFrame {
	
	private DisplayPanel display;
	private LogicController logic;
	private TypingTestTextArea sample;
	private RandomStringFactory strFactory;
	private ClockInterface clock;

	/**
	 * Constructor instantiates all the required objects 
	 */
	MainFrame(){
		super("Typing Test");		
		display = new DisplayPanel();
		logic = new LogicController();
		clock = new CounterClock();
		logic.registerDisplayPanelWithTimer(display, clock);
		strFactory = new RandomStringFactory();
		sample = new TypingTestTextArea(logic, strFactory);
		logic.registerSampleTextArea(sample);
		
		initComponents();
	}
	
	/**
	 *Initializes and puts together all the other panels required by the Main Frame
	 * Returns null 
	 */
	void initComponents(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		setLayout(new BorderLayout());
		add("Center",sample);
		add("East",display);
	    setSize(550,400);
	}
	
	public static void main(String[] args){
		
		MainFrame main = new MainFrame();
				
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
            	try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	new MainFrame().setVisible(true);
            }
        });
		
	}

}
