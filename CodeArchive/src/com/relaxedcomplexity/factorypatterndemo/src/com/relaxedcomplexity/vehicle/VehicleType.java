/**
 * 
 */
package com.relaxedcomplexity.vehicle;

/**
 * VehicleType enumerations including creation of new object instances 
 * based on the type of vehicle. This provides an additional layer of 
 * abstraction to separate the details of how to create new objects away from
 * the objects that use them.
 *  
 * @author jim.medlock
 *
 */
public enum VehicleType {
  
  CAR("Car") {
    @Override
    public Vehicle getVehicle() {
      return new Car();
    }
  },
  TRUCK("Truck") {
    @Override
    public Vehicle getVehicle() {
      return new Car();
    }
  };
  
  private String className = null;
  private String packageName = null;
  private Object vehicleObject = null;
  
  /**
   * Class constructor with parameters specifying the vehicle type.
   * 
   * @param type Vehicle type
   * @throws ClassNotFoundException 
   */
  private VehicleType(String className) {
    this.className = className;
    this.packageName = this.getClass().getPackage().getName();
  }  
  
  /**
   * Retrieve the vehicle type
   * 
   * @return Vehicle class name
   */
  public String getClassName() {
    return className;
  }

  /**
   * Create and return a new Vehicle object of the proper type.
   * <p>
   * Although using reflection to create a new Vehicle object certainly 
   * works, it is not necessarily straightforward. While it may be useful in
   * some situations for the purposes of this example it is clearer to 
   * implement a getVehicle method associated with each individual ENUM.
   * 
   * @return Vehicle object 
   * @throws Exception
   */
  public abstract Vehicle getVehicle();
  
  /*
  public Vehicle getVehicle() 
      throws Exception {
    vehicleObject =  (Vehicle)Class
        .forName(packageName+"."+className)
        .getConstructor()
        .newInstance();
    switch (className) {
      case "Car":
        return (Car)vehicleObject;
      case "Truck":
        return (Truck)vehicleObject;
      case "ElectricCar":
        //return (ElectricCar)vehicleObject;
    }
    return null;
  }
  */

}
