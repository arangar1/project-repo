package com.TypingTest;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Controller class which acts as a mediator to the two views and takes care of all the actions required to respond to the user interaction
 * 
 * @author Ajay
 *
 */
public class LogicController implements ControllerInterface{
	private boolean isPressed = false;
	private boolean idDone = false;
	private boolean donetxtHilight = false;
	
	private int endIndex = 0;
	private int startIndex = 0;
	private int totalWords = 0;
	private int totalErrors = 0;
	private int changeLength = 0;
	
	private String userString;
	private String sampleString;
	
	private DisplayPanel displayPanel;	
	private TypingTestTextArea sample;	
	private ClockInterface timer;
	
	private static final String REGEX = "\\S+";
	
	/**
	 * Constructor
	 */
	public LogicController(){	
	}
	
	/**
	 * Controller registered with View1
	 * @param disp
	 * @param cl
	 */
	public void registerDisplayPanelWithTimer(DisplayPanel disp, ClockInterface cl){
		this.displayPanel = disp;
		this.timer = cl;
		displayPanel.setTimer(timer);
		displayPanel.registerButtonWithListener(this);
	}
	
	/**
	 * Controller registered with View2
	 * @param s
	 */
	public void registerSampleTextArea(TypingTestTextArea s){
		this.sample = s;
		this.sampleString = sample.getSampleString();
		donetxtHilight = true;
		highlightText();
	}
	
	/**
	 * Invoked when the user types something in the text area
	 * @param ke
	 */
	@Override
	public void onAction(KeyEvent ke) {
		
		if((ke.getKeyCode() >= 44 && ke.getKeyCode() <= 112 && idDone==false)|| ke.getKeyCode() == 32 || ke.getKeyCode() == 10){
			
			if(!isPressed){
				displayPanel.startTimer();
				isPressed = true;
			}			
			
			changeLength = changeLength +1;
			displayPanel.setCharCountLabel("                 "+Integer.toString(changeLength));
			
			if(changeLength == sampleString.length()){
				sample.appendToUsertext(Character.toString(ke.getKeyChar()));
				result();
			}
			
			if((ke.getKeyChar() == ' ' || ke.getKeyChar() == '\n') && donetxtHilight == true){
				highlightText();
			} else if(ke.getKeyChar() != ' ' && ke.getKeyChar() != '\n'){
				donetxtHilight = true;
			}
		} else if(ke.getKeyCode() == 8 && changeLength >0){
			changeLength = changeLength -1;
			displayPanel.setCharCountLabel("                 "+Integer.toString(changeLength));
		}
	
	}
	
	
	/**
	 * when the test is over this method compares the user string and the sample string calls hilight method of user to indicate the errors
	 */
	void stringMatcher(){
    	Pattern p = Pattern.compile(REGEX);
    	userString = sample.getUserText();
	    Matcher m = p.matcher(sampleString);
	    Matcher n = p.matcher(userString);
	    while(m.find() && n.find()) {
	        totalWords++;
	        
	        if(!userString.substring(n.start(), n.end()).equals(sampleString.substring(m.start(), m.end()))){
	     	   totalErrors++;
				sample.highlightUserText(n.start(), n.end());
	        }
	    }	       
	    while(n.find()){
	    	totalWords++;
	    	totalErrors++;
	    	sample.highlightUserText(n.start(), n.end());
	    }
    }

	/**
	 * Called when the test ends. this populates the results to be displayed on the DisplayPanel side view
	 */
	public void result(){ 
		
		displayPanel.onResult();
		sample.onResult();
		stringMatcher();
		System.out.println(totalWords +"    "+ totalErrors);
		idDone = true;
		if(totalWords != 0){
			displayPanel.setCharCountLabel("<html>"
					+ "Char Count - " + displayPanel.getCharCountLabelString() +"<br>"
					+ "Time - " +  displayPanel.getTimeLabelString() + " sec(s)" +"<br>"
					+ "Error % - " +  (totalErrors * 100) / totalWords +"<br>"
					+ "WPM - " + (totalWords * 60) / Integer.parseInt(displayPanel.getTimeLabelString()) );
		} else {
			displayPanel.setCharCountLabel("<html>Please type<br>valid chars</html>");
		}
	}
	
	/**
	 * Hilights the sample text as the user types the text
	 */
	public void highlightText(){
		
		if(endIndex < sampleString.length()){
			sample.setSampleTextCaretPosition(startIndex);
			while(++endIndex < sampleString.length()){
				if(sampleString.charAt(endIndex) == ' ' || sampleString.charAt(endIndex) == '\n' ){
					break;
				}		
			}			
			sample.highlightSampleText(startIndex, endIndex);
    		donetxtHilight = false;
			startIndex= endIndex+1;
		}
	}

	/**
	 * @return
	 */
	public String getSampleString() {
		return sampleString;
	}

	/**
	 * @param sampleString
	 */
	public void setSampleString(String sampleString) {
		this.sampleString = sampleString;
	}

	/**
	 * Invoked when the reset button is clicked. All components are reset to original state and causes new string to be generated
	 */
	@Override
	public void onReset(){
		isPressed = false;
		idDone = false;
		donetxtHilight = false;
		
		endIndex = 0;
		startIndex = 0;
		totalWords = 0;
		totalErrors = 0;
		changeLength = 0;
		
		userString ="";
		
		timer.reset();		
		displayPanel.onReset();		
		sample.onReset();
		
		displayPanel.setTimer(timer);
		displayPanel.registerButtonWithListener(this);
		sampleString = sample.getSampleString();
		
		donetxtHilight = true;
		highlightText();
		
		System.out.println("-----");
	}
}
