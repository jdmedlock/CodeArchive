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
 * Computer defines the attributes of a particular generic computer. This is 
 * used to compare and contrast using inheritance vs. composition to implement 
 * has-a and is-a relationships between objects.
 * <p>
 * This class encapsulates all the information that describes the attributes
 * and capabilities of a particular computer. This includes:
 * <ul>
 * <li>Display
 * <li>Memory
 * <li>Processor
 * <li>Internal & External storage
 * </ul>
 * <p>
 * It is expected that subclasses of Computer will be created to describe 
 * specific types of computers. For example, Laptop is a subclass of Computer
 * which defines attributes and methods unique to laptop computers.
 * 
 * @author Jim Medlock
 * @see Desktop
 * @see Laptop
 * @see Smartphone
 * @see Tablet
 *
 */
public class Computer {
  
  private String     name = null;
  private Display    display = null;
  private Processor  processor = null;
  private Memory     memory = null;
  private IntStorage intStorage = null;
  private ExtStorage extStorage = null;
  private JsonObject jsonAttrs = null;
  
  /**
   * Class constructor accepting parameters describing the attributes of
   * the computer this class represents.
   * 
   * @param currJsonComputer Model object describing the computer
   */
  public Computer(JsonObject currJsonComputer) {
    this.setModel(currJsonComputer);
    this.name = currJsonComputer.getString("name", null);

    display = new Display(currJsonComputer);
    processor = new Processor(currJsonComputer);
    memory = new Memory(currJsonComputer);
    intStorage = new IntStorage(currJsonComputer);
    extStorage = new ExtStorage(currJsonComputer);
  }

  /**
   * Retrieve the computer name
   * 
   * @return the name of the computer
   */
  public String getName() {
    return name;
  }

  /**
   * Update the computer name
   * 
   * @param name the computer name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieve the JSON object containing the attributes for this computer
   * 
   * @return Attributes of this computer
   */
  public JsonObject getModel() {
    return jsonAttrs;
  }

  /**
   * Update the JSON attribute model.
   * 
   * @param model JSON attribute model object
   */
  public void setModel(JsonObject model) {
    jsonAttrs = model;
  }
  
  /**
   * Print the attributes of this computer
   */
  public void print() {
    System.out.printf("\nComputer name: %s\n",name);
    System.out.printf("   %s\n",processor.toString());
    System.out.printf("   %s\n",memory.toString());
    System.out.printf("   %s\n",display.toString());
    System.out.printf("   %s\n",intStorage.toString());
    System.out.printf("   %s\n",extStorage.toString());
  }
  
}
