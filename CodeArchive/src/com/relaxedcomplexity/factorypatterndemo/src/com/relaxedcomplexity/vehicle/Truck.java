/**
 * 
 */
package com.relaxedcomplexity.vehicle;

/**
 * @author jim.medlock
 *
 */
public class Truck extends Vehicle {

  /**
   * 
   */
  public Truck() {
    System.out.println("Creating new Truck instance");
  }
  
  @Override
  public void startEngine() {
    System.out.println("...starting Truck engine...");
  }

}
