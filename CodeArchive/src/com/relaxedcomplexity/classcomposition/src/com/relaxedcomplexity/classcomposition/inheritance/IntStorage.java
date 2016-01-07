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

import java.util.StringJoiner;

import com.eclipsesource.json.JsonObject;

/**
 * IntStorage defines the attributes of the internal storage device attached to
 * a computer.
 * 
 * @author Jim Medlock
 */
public class IntStorage implements IStorage {
  
  private String  manufacturer;
  private String  model;
  private float   maxCapacity;
  private float   usableCapacity;
  private int     rpm;
  
  /**
   * Class constructor building a new instance from the "intstorage" attributes
   * stored in the JSON object parameter.
   * 
   * @param currJsonComputer
   */
  public IntStorage(JsonObject currJsonComputer) {
    JsonObject currJsonIntStorage = currJsonComputer.get("intstorage").asObject();
    String manufacturerValue = currJsonIntStorage.getString("manufacturer", null);
    String modelValue = currJsonIntStorage.getString("model", null);
    float maxCapacityValue = currJsonIntStorage.getFloat("maxcapacity", 0.0f);
    float usableCapacityValue = currJsonIntStorage.getFloat("usablecapacity", 0.0f);
    int rpmValue = currJsonIntStorage.getInt("rpm", 0);
 
    setManufacturer(manufacturerValue);
    setModel(modelValue);
    setMaxCapacity(maxCapacityValue);
    setUsableCapacity(usableCapacityValue);
    setRpm(rpmValue);
  }

  /**
   * Retrieve the manufacturer name
   * 
   * @return the manufacturer name
   * @see #setManufacturer(String)
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Update the manufacturer name
   * 
   * @param manufacturer the manufacturer name to set
   * @see #getManufacturer()
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Retrieve the model name
   * 
   * @return the model name
   * @see #setModel(String)
   */
  public String getModel() {
    return model;
  }

  /**
   * Update the mode name
   * 
   * @param model the model name to set
   * @see #getModel()
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Retrieve the maximum drive capacity
   * 
   * @return the maximum drive capacity (GB)
   * @see #setMaxCapacity(float)
   */
  public float getMaxCapacity() {
    return maxCapacity;
  }

  /**
   * Update the maximum drive capacity (GB)
   * 
   * @param maxCapacity the maximum drive capacity (GB) to set
   * @see #getMaxCapacity()
   */
  public void setMaxCapacity(float maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  /**
   * Retrieve the ussable drive capacity (GB)
   * 
   * @return the usable drive capacity (GB)
   * @see #setUsableCapacity(float)
   */
  public float getUsableCapacity() {
    return usableCapacity;
  }

  /**
   * Update the usable drive capacity (GB)
   * 
   * @param usableCapacity the usable drive capacity (GB) to set
   * @see #getUsableCapacity()
   */
  public void setUsableCapacity(float usableCapacity) {
    this.usableCapacity = usableCapacity;
  }

  /**
   * Retrieve the drives revolutions per minute (RPM).
   * <p>
   * This may be zero if the drive is non-rotating media like a thumbdrive.
   * 
   * @return the rpm
   * @see #setRpm(int)
   */
  public int getRpm() {
    return rpm;
  }

  /**
   * Update the drives number of revolutions per minute (RPM).
   * 
   * @param rpm the rpm to set
   * @see #getRpm()
   */
  public void setRpm(int rpm) {
    this.rpm = rpm;
  }

  /**
   * Create a string containing the attributes of this object. The attribute 
   * string generated has the format 
   * "Internal Storage:[<attr-name>:<attr-value,...]".
   */
  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "Internal Storage: [", "]");
    sj.add("manufacturer: "+getManufacturer())
      .add("model: "+getModel())
      .add("maxcapacity: "+Float.toString(getMaxCapacity()))
      .add("usablecapacity: "+Float.toString(getUsableCapacity()))
      .add("rpm: "+Integer.toString(getRpm()));
    return sj.toString();
  }

}
