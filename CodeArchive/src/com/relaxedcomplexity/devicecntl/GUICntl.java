/**
 * 
 */
package com.relaxedcomplexity.devicecntl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Jim Medlock
 *
 */
public class GUICntl extends JPanel {

    private static 		 ContentArea	contents;
    private static 		 JTextArea		infoArea;
    		static final String 		NEWLINE = System.getProperty("line.separator");

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void displayGUI() {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("Device Control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        JComponent newContentPane = this;
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
         
    	//Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public void initGUI(MouseCntl mouseCntl) {
        contents = new ContentArea(Color.YELLOW);
        add(contents);
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 75));
        add(scrollPane);
        
    	//Register for mouse events on blankArea and the panel.
    	contents.addMouseListener(mouseCntl);
    	addMouseListener(mouseCntl);

        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    GUICntl(MouseCntl mouseCntl) {
        super(new GridLayout(0,1));
        initGUI(mouseCntl);
    }
}
