/**
 * 
 */
package com.relaxedcomplexity.vehicle;

/**
 * @author jim.medlock
 *
 */
public class Car extends Vehicle {

  /**
   * 
   */
  public Car() {
    System.out.println("Creating new Car instance");
  }
  
  @Override
  public void startEngine() {
    System.out.println("...starting Car engine...");
  }

}
