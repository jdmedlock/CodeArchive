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
package com.relaxedcomplexity.classcomposition.inheritance;

import com.eclipsesource.json.JsonObject;

/**
 * Computer defines the attributes of a particular computer.
 * 
 * @author Jim Medlock
 *
 */
public class Computer {
  
  private static Display    display = null;
  private static Processor  processor = null;
  private static Memory     memory = null;
  private static IntStorage intStorage = null;
  private static ExtStorage extStorage = null;
  private static JsonObject jsonAttrs = null;
  
  /**
   * Class constructor accepting parameters describing the attributes of
   * the computer this class represents.
   * 
   * @param currJsonComputer Model object describing the computer
   */
  public Computer(JsonObject currJsonComputer) {
    this.setModel(currJsonComputer);
	
    display = new Display(currJsonComputer);
    processor = new Processor(currJsonComputer);
    memory = new Memory(currJsonComputer);
    intStorage = new IntStorage(currJsonComputer);
    extStorage = new ExtStorage(currJsonComputer);
  }

  /**
   * Retrieve the JSON object containing the attributes for this computer
   * 
   * @return Attributes of this computer
   */
  public static JsonObject getModel() {
    return jsonAttrs;
  }

  public static void setModel(JsonObject model) {
    Computer.jsonAttrs = model;
  }
}
