/**
 * 
 */
package com.relaxedcomplexity.devicecntl;

import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This program demonstrates how to control devices by controlling the 
 * pitch and volume of a sound on the local computer using the mouse. 
 * Scrolling up and down adjusts the volume while moving the mouse left 
 * and right adjusts the pitch of the sound. 
 * 
 * Since two discrete "things" must interact this is an interesting 
 * example of how to use design patterns to both simplify the code and 
 * make it easily maintainable and extensible.
 * 
 * @author Jim Medlock
 *
 */
public class DeviceCntl {
	
	private   static final Logger		logger = Logger.getLogger("com.relaxedcomplexity.devicecntl");
	protected static       MouseCntl	mouseCntl;
	protected static       GUICntl		guiCntl;
	
	/**
	 * Processing cycle plays the sound until the user stops the program
	 * exit indicator is enabled.
	 */
	public static void processCycle() {
		logger.entering(DeviceCntl.class.getSimpleName(),"processCycle");
		
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
            	//guiCntl.initGUI(mouseCntl);
                guiCntl.displayGUI();
		
                SoundPlayer soundPlayer = new SoundPlayer();
                //while (!mouseCntl.isExit()) {
                //}
            }
        });
		
		logger.exiting(DeviceCntl.class.getSimpleName(), null);
	}

	/**
	 * @param args An array of parameters to be used by the program. However,
	 * this are not necessary for this program and are ignored.
	 */
	public static void main(String[] args) {
		logger.entering(DeviceCntl.class.getSimpleName(),"main");
		logger.info("Starting processing...");
		mouseCntl = new MouseCntl();
		guiCntl = new GUICntl(mouseCntl);
		
		processCycle();
		
		logger.info("...Program terminating.");
		logger.exiting(DeviceCntl.class.getSimpleName(), null);
	}

}
