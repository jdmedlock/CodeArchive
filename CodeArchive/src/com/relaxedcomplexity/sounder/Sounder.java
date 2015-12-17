/**
 * 
 */
package com.relaxedcomplexity.sounder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Stack;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Manage the user interface
 * 
 * Portions of the main, createGui, and GUICntl methods were copied from the following
 * which is copyrighted by Oracle:
 * 
 * 		https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
 * 		Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved. 
 * 
 * @author Jim Medlock
 *
 */
public class Sounder extends JPanel 
	implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static final Logger				logger = Logger.getLogger("com.relaxedcomplexity.devicecntl");
	private static final String 			NEWLINE = System.getProperty("line.separator");

	private static 		 ContentArea		contents = null;
    private static 		 JTextArea			infoArea = null;
    
    private static		 Stack<MouseEvent>	mousePositions = null;
    
    

	/**
	 * Processing cycle plays the sound until the user stops the program
	 * exit indicator is enabled.
	 *
	 * @param args An array of parameters to be used by the program. However,
	 * this are not necessary for this program and are ignored.
	 */
	public static void main(String[] args) {
		logger.entering(Sounder.class.getSimpleName(),"main");
		
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
		
		logger.exiting(Sounder.class.getSimpleName(), null);
	}

	/**
     * Create the user interface the user will interact with to this program.  
     * This method must be invoked from the event dispatch thread to ensure
     * thread safety.
     */
    private static void createGUI() {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("Device Control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        JComponent newContentPane = new Sounder();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
         
    	//Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * 
     */
    public Sounder() {
        super(new GridLayout(0,1));
        contents = new ContentArea(Color.CYAN);
        add(contents);
        
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 75));
        add(scrollPane);
        
    	//Register for mouse events on blankArea and the panel.
    	contents.addMouseListener(this);
    	addMouseListener(this);
    	contents.addMouseMotionListener(this);
    	addMouseMotionListener(this);

        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    public void addToInfoArea(String aText) {
    	infoArea.append(aText
                + "." + NEWLINE);
        infoArea.setCaretPosition(infoArea.getDocument().getLength());
    }

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseWheelEvt) {
		addToInfoArea("Mouse wheel was moved");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent mouseEvt) {
		addToInfoArea("Mouse was dragged");
	}

	/* Process a mouse move event
	 * 
	 * When the user moves the mouse compare its current position to its
	 * position captured when it was last moved to determine if it was
	 * moved horizontally (i.e. left,right) or vertically (i.e. up, down).
	 * 
	 * It is important to keep in mind that mouse movements may occur across
	 * two of the four axes. When calculating the direction this is taken
	 * into account and the movement with the direction with highest delta 
	 * from the prior position of the mouse is chosen as the current
	 * direction.
	 * 
	 * Prior to exiting one of four class variables will be updated to 
	 * indicate the direction of movement. Note that direction is mutually
	 * exclusive so only one direction will be indicated at any point in 
	 * time.
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent mouseEvt) {
		addToInfoArea("Mouse was moved");
		
		// Determine the current direction
		MouseEvent mouseLastPos = mousePositions.pop();
		
		// Save the current mouse position for use next time
		mousePositions.push(mouseEvt);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvt) {
		addToInfoArea("Mouse was clicked");
		if ((mouseEvt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			addToInfoArea("...left button");			
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent mouseEvt) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent mouseEvt) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent mouseEvt) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent mouseEvt) {
		// TODO Auto-generated method stub

	}
	
}
