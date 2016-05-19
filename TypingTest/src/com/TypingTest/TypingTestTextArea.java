package com.TypingTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 * This view shows the sample text area and the user text area. All the changes are sent to the LogicController(Mediator) which makes the necessary changes/updates needed.
 * @author Ajay
 *
 */
public class TypingTestTextArea extends JPanel implements KeyListener {
	
	private final static Color  SAMPLE_TXT_COLOR = Color.LIGHT_GRAY;
	private final static Color  USER_TXT_COLOR = Color.RED;
	private Highlighter sampleHilight;
    private Highlighter.HighlightPainter sampletextPainter;
    
    private JTextArea jTextArea1; // related to 1 textbox
	private JScrollPane jScrollPane1; // related to 1 textbox
	
	private String sampleString;
	
	private JTextArea jTextArea2; // related to 2 textbox
	private JScrollPane jScrollPane2; // related to 2 textbox
	
	private Highlighter usertextHilight;	 
	private Highlighter.HighlightPainter usrtextPainter;
	
	private ControllerInterface logic;
	
	private RandomStringFactory strFactory;
	
	/**
	 * Constructor with String factory and controller as parameters
	 * @param logic
	 * @param r
	 */
	TypingTestTextArea(ControllerInterface logic, RandomStringFactory r){
		super();
		this.logic = logic;
		this.strFactory = r;
		initComponents();		
	}
	
	/**
	 *Initializes all the components required by the Typing Test Text Area
	 * Returns null 
	 */
	void initComponents(){
		sampleString = strFactory.generateString();
		jTextArea1 = new JTextArea();
        jTextArea1.setTransferHandler(null);
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		jTextArea1.setFont(new Font("Calibri",Font.PLAIN,19));
		jTextArea1.setEditable(false);
		jTextArea1.setFocusable(false);
		jTextArea1.setText(sampleString);
		
		sampleHilight = new DefaultHighlighter();
		sampletextPainter = new DefaultHighlighter.DefaultHighlightPainter(SAMPLE_TXT_COLOR);
        jTextArea1.setHighlighter(sampleHilight);
		
		jScrollPane1 = new JScrollPane(jTextArea1);
		jScrollPane1.setPreferredSize(new Dimension(280,150));
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setBorder(jScrollPane1.getBorder());
		
		// related to 2 textbox
		jTextArea2 = new JTextArea();
		jTextArea2.setTransferHandler(null); //disables copy paste
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);
		jTextArea2.setFont(new Font("Calibri",Font.PLAIN,19));
		jTextArea2.addKeyListener(this); //action listener
		
		usertextHilight = new DefaultHighlighter();
		usrtextPainter = new DefaultHighlighter.DefaultHighlightPainter(USER_TXT_COLOR);
        jTextArea2.setHighlighter(usertextHilight);
        
		jScrollPane2 = new JScrollPane(jTextArea2);
		jScrollPane2.setPreferredSize(new Dimension(280,150));
		jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane2.setBorder(jScrollPane2.getBorder());
		
		setLayout(new GridLayout(2,1));
		add(jScrollPane1);
		add(jScrollPane2);
		setPreferredSize(new Dimension(300,400));
			
	}

	/* Method from KeyListener which calls the controller method for further action
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
	
		logic.onAction(ke);
					
	}
	
	
	/**
	 * Highlights the sample text from the sample text area
	 * @param startIndex
	 * @param endIndex
	 */
	public void highlightSampleText(int startIndex, int endIndex){
		sampleHilight.removeAllHighlights();		
		try {
			sampleHilight.addHighlight(startIndex, endIndex, sampletextPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Highlights the user text from the user text area
	 * @param startIndex
	 * @param endIndex
	 */
	public void highlightUserText(int startIndex, int endIndex){
		
		try {
			usertextHilight.addHighlight(startIndex, endIndex, usrtextPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * appends to the sample text area
	 * @param append
	 */
	public void appendToSampletext(String append){
		jTextArea1.append(append);
;	}
	
	/**
	 * appends to the user text area
	 * @param append
	 */
	public void appendToUsertext(String append){
		jTextArea2.append(append);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ke) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent ke) { 
	}
	
	/**
	 * Called when the user text length is equal to the sample text length  this method is called which restricts further editing to the text area
	 */
	void onResult(){
		jTextArea2.setEditable(false);
		jTextArea2.setFocusable(false);
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
	 * @return
	 */
	public String getUserText(){
		return jTextArea2.getText();
	}
	
	/**
	 * Sets the caret position for user
	 * @param pos
	 */
	public void setSampleTextCaretPosition(int pos){
		jTextArea1.setCaretPosition(pos);
	}
	
	/**
	 * Sets the caret position for user
	 * @param pos
	 */
	public void setUserTextCaretPosition(int pos){
		jTextArea2.setCaretPosition(pos);
	}
	
	/**
	 * Resets the Typing test Text Area components
	 */
	void onReset(){
		jTextArea1.setText("");
		jTextArea2.setText("");
		remove(jScrollPane1);
		remove(jScrollPane2);
		initComponents();
	}
}
