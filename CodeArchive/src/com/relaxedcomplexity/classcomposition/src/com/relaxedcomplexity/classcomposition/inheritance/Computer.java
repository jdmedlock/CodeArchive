/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

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
  private static Model      model = null;
  
  /**
   * Class constructor accepting parameters describing the attributes of
   * the computer this class represents.
   * 
   * @param model Model object describing the computer
   */
  public Computer(Model model) {
    this.model = model;
	
    display = new Display();
    processor = new Processor();
    memory = new Memory();
    intStorage = new IntStorage();
    extStorage = new ExtStorage();
  }
}
