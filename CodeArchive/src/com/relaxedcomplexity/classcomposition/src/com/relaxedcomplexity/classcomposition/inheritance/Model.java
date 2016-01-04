/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * Model defines both the model and manufacturer of a computer. 
 * 
 * @author Jim Medlock
 */
public enum Model {
  IMAC(Brand.APPLE,"iMac"),
  MACBOOKAIR(Brand.APPLE,"MacBook Air"),
  MACBOOKPRO(Brand.APPLE,"MacBook Pro"),
  IPAD3(Brand.APPLE,"iPad 3"),
  SIXPLUS(Brand.APPLE,"iPhone 6 Plus");

  private String modelName = null;
  private Brand  brand = null;

  /**
   * Class constructor with parameters specifying the computer brand and its
   * model name.
   * 
   * @param brand
   * @param modelName
   */
  private Model(Brand brand, String modelName) {
	  this.brand = brand;
	  this.modelName  = modelName;
  }
  
  /**
   * Retrieve the brand name
   * 
   * @return Brand name
   */
  public String getBrandName() {
	  return brand.getBrandName();
  }
 
  /**
   * Retrieve the model name
   * 
   * @return Model name
   */
  public String getModelName() {
	  return modelName;
  }
	
}
