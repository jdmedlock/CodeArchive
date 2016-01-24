/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Relaxed Complexity, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 *******************************************************************************/
/**
 * 
 */
package com.relaxedcomplexity.classcomposition.composition;

import com.relaxedcomplexity.classcomposition.Brand;
import com.relaxedcomplexity.classcomposition.ComputerType;

/**
 * Model defines both the model and manufacturer of a computer. 
 * 
 * @author Jim Medlock
 */
public enum Model {
  IMAC(Brand.APPLE,"iMac",ComputerType.DESKTOP),
  MACBOOKAIR(Brand.APPLE,"MacBook Air",ComputerType.LAPTOP),
  MACBOOKPRO(Brand.APPLE,"MacBook Pro",ComputerType.LAPTOP),
  IPAD3(Brand.APPLE,"iPad 3",ComputerType.TABLET),
  SIXPLUS(Brand.APPLE,"iPhone 6 Plus",ComputerType.SMARTPHONE);

  private String       modelName = null;
  private Brand        brand = null;
  private ComputerType type = null;

  /**
   * Class constructor with parameters specifying the computer brand and its
   * model name.
   * 
   * @param brand
   * @param modelName
   */
  private Model(Brand brand, String modelName, ComputerType type) {
	  this.brand = brand;
	  this.modelName  = modelName;
	  this.type = type;
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
  
  /**
   * Retrieve the computer type
   * 
   * @return Computer type name
   */
  public String getComputerType() {
    return type.getType();
  }
	
}
