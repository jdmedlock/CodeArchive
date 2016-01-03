/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
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
  
  public Computer() {
    display = new Display();
    processor = new Processor();
    memory = new Memory();
    intStorage = new IntStorage();
    extStorage = new ExtStorage();
  }
}
