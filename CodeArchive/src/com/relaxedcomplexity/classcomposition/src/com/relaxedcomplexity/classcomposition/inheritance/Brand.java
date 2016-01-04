/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * Brand defines the different manufacturers computers may be acquired from.
 * 
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
	
	/**
	 * Class constructor to create a brand and associated brand name
	 * 
	 * @param brandName Brand name
	 */
	private Brand(String brandName) {
		this.brandName = brandName;
	}
	
	/**
	 * Retrieve the brand name for a computer brand
	 * 
	 * @return Brand name
	 */
	public String getBrandName() {
		return brandName;
	}
}
