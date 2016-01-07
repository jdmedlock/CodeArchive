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
package com.relaxedcomplexity.classcomposition.inheritance;

import com.eclipsesource.json.JsonObject;

/**
 * Processor defines the attributes of the CPU installed on a particular
 * computer.
 * 
 * @author Jim Medlock
 *
 */
public class Processor implements IProcessor {
  
  private static float  clockSpeed = 0.0f;
  private static String manufacturer = null;
  private static String model = null;
  
  /**
   * Class constructor building a new instance from the "processor" attributes
   * stored in the JSON object parameter.
   * 
   * @param currJsonComputer
   */
  public Processor(JsonObject currJsonComputer) {
    JsonObject currJsonProcessor = currJsonComputer.get("processor").asObject();
    float clockSpeedValue = currJsonProcessor.getFloat("clockspeed", 0);
    String manufacturerValue = currJsonProcessor.getString("manufacturer", null);
    String modelValue = currJsonProcessor.getString("model", null);
    
    setClockSpeed(clockSpeedValue);
    setManufacturer(manufacturerValue);
    setModel(modelValue);
  }  

  
  /**
   * Retrieve the processors clock speed (GHz).
   * 
   * @return the clockSpeed
   */
  public static float getClockSpeed() {
    return clockSpeed;
  }

  /**
   * Update the clock speed (GHz) of the processor.
   * 
   * @param clockSpeed the clockSpeed to set
   */
  public static void setClockSpeed(float clockSpeed) {
    if (clockSpeed <= 0.0f) {
      throw new IllegalArgumentException("clockSpeed not > 0!");
    }
    
    Processor.clockSpeed = clockSpeed;
  }

  /**
   * Retrieve the manufacturer name of the processor.
   * 
   * @return the manufacturer name
   */
  public static String getManufacturer() {
    return manufacturer;
  }

  /**
   * Update the manufacturer name of the processor.
   * 
   * @param manufacturer the manufacturer name to set
   */
  public static void setManufacturer(String manufacturer) {
    if (manufacturer == null) {
      throw new IllegalArgumentException("manufacturer not specified!");
    }
    
    Processor.manufacturer = manufacturer;
  }

  /**
   * Retrieve the model name of the processor. This value combined with the
   * manufacturer name identifies the type of processor and it's capabilities.
   * 
   * @return the model
   * @see #getManufacturer()
   */
  public static String getModel() {
    return model;
  }

  /**
   * Update the model name of the processor. This value should be valid for the
   * manufacturer name set for the processor.
   * 
   * @param model the model to set
   * @see #getManufacturer()
   * 
   */
  public static void setModel(String model) {
    if (model == null) {
      throw new IllegalArgumentException("model not specified!");
    }

    Processor.model = model;
  }

}
