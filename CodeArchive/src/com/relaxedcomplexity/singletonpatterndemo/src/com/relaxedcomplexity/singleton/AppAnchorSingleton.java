/**
 * 
 */
package com.relaxedcomplexity.singleton;

/**
 * @author jimmedlock
 *
 */
public class AppAnchorSingleton {
	
	private static AppAnchorSingleton INSTANCE = null;
 
	/**
	 * A protected class constructor ensures that clients won't be able to 
	 * allocate more than one instance of this class. This makes sure that
	 * the instance allocated by the public AppAnchorSignature class will,
	 * in fact, be a singleton.
	 */
	protected AppAnchorSingleton() {
	}
 
	/**
	 * Create an new instance of this class if not previously allocated, or 
	 * retrieve the singleton instance of this class that was previously 
	 * created.
	 * 
	 * @return AppAnchorSingleton instance
	 */
	public static AppAnchorSingleton getInstance() {
		if (INSTANCE == null) {
			// Ensure that this operation is thread safe. However, in some
			// application this may be expensive since it single threads the 
			// examination of the instance variable holding the object 
			// reference.
			synchronized (AppAnchorSingleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new AppAnchorSingleton();
				}
			}
		}
		return INSTANCE;
	}
}
