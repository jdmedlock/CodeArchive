/**
 * 
 */
package com.relaxedcomplexity.vehicle;

/**
 * @author jim.medlock
 *
 */
public class VehicleFactory {

  public Vehicle getVehicle (VehicleType vehicleType) 
      throws Exception {
    return vehicleType.getVehicle();
  }
}
