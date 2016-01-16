/**
 * 
 */
package com.relaxedcomplexity.client;

import com.relaxedcomplexity.singleton.AppAnchorSingleton;

/**
 * @author jimmedlock
 *
 */
public class SingletonClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppAnchorSingleton singletonInstance = null;
		
		// The addresses of the singletonInstance object should be the same
		// for all three of the following if the AppAnchorSingleton class is
		// performing as designed
		singletonInstance = AppAnchorSingleton.getInstance();
		System.out.println("Singleton #1: "+singletonInstance);
		singletonInstance = AppAnchorSingleton.getInstance();
		System.out.println("Singleton #2: "+singletonInstance);
		singletonInstance = AppAnchorSingleton.getInstance();
		System.out.println("Singleton #3: "+singletonInstance);
	}

}
