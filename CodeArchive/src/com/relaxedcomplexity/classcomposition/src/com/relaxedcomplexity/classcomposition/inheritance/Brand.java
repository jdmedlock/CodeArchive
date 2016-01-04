/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * @author Jim Medlock
 *
 */
public enum Brand {
	ACER("Acer"),
	APPLE("Apple"),
	DELL("Dell"),
	HP("Hewett Packard"),
	LENOVO("Lenovo"),
	SONY("Sony");
	
	private String	brandName = null;
	
	/*
	 * Constructor: Create a brand and associated brand name
	 * 
	 * @param brandName Brand name
	 */
	private Brand(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBrandName() {
		return brandName;
	}
}
