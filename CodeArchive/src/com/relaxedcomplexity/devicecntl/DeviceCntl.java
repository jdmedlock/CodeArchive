/**
 * 
 */
package com.relaxedcomplexity.devicecntl;

import java.util.logging.Logger;

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
 * @author jim.medlock
 *
 */
public class DeviceCntl {
	
	private static final Logger	logger = Logger.getLogger("com.relaxedcomplexity.devicecntl");
	
	/**
	 * Processing cycle plays the sound until the user stops the program
	 * exit indicator is enabled.
	 */
	public static void processCycle() {
		logger.entering(DeviceCntl.class.getSimpleName(),"processCycle");
		
		SoundPlayer soundPlayer = new SoundPlayer();
		while (soundPlayer.isExit()) {
			
		}
		
		logger.exiting(DeviceCntl.class.getSimpleName(), null);
	}

	/**
	 * @param args An array of parameters to be used by the program. However,
	 * this are not necessary for this program and are ignored.
	 */
	public static void main(String[] args) {
		logger.entering(DeviceCntl.class.getSimpleName(),"main");
		logger.info("Starting processing...");
		
		//processCycle();
		
		logger.info("...Program terminating.");
		logger.exiting(DeviceCntl.class.getSimpleName(), null);
	}

}
