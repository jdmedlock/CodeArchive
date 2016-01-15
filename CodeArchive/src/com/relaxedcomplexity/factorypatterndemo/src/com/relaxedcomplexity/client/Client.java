/**
 * 
 */
package com.relaxedcomplexity.client;

import com.relaxedcomplexity.vehicle.Vehicle;
import com.relaxedcomplexity.vehicle.VehicleFactory;
import com.relaxedcomplexity.vehicle.VehicleType;

/**
 * Demonstration of how to implement the Factory Design Pattern. 
 * 
 * @author jim.medlock
 *
 */
public class Client {

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    VehicleFactory vehicleFactory = new VehicleFactory();

    Vehicle car = vehicleFactory.getVehicle(VehicleType.CAR);
    car.startEngine();
    Vehicle truck = vehicleFactory.getVehicle(VehicleType.TRUCK);
    truck.startEngine();
  }

}
