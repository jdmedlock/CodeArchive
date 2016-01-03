/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * @author Jim Medlock
 *
 */
public enum Model {
  IMAC(Brand.APPLE,"iMac"),
  MACBOOKAIR(Brand.APPLE,"MacBook Air"),
  MACBOOKPRO(Brand.APPLE,"MacBook Pro"),
  IPAD3(Brand.APPLE,"iPad 3"),
  SIXPLUS(Brand.APPLE,"iPhone 6 Plus");

  private String modelName = null;
  private Brand  brand = null;

  private Model(Brand brand, String modelName) {
	  this.brand = brand;
	  this.modelName  = modelName;
  }
  
  public String getBrandName() {
	  return brand.getBrandName();
  }
  
  public String getModelName() {
	  return modelName;
  }
	
}
