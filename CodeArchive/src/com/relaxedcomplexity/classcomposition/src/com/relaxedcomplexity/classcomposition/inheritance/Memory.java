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
 * Memory defines the attributes of the memory (RAM) installed on a particular
 * computer.
 * 
 * @author Jim Medlock
 *
 */
public class Memory implements IMemory {
  
  private int        installedCapacity = 0;
  private int        maxCapacity = 0;
  private MemoryType type = null;
  
  /**
   * Class constructor building a new instance from the "memory" attributes
   * stored in the JSON object parameter.
   * 
   * @param currJsonComputer
   */
  public Memory(JsonObject currJsonComputer) {
    JsonObject currJsonMemory = currJsonComputer.get("memory").asObject();
    int installedCapacityValue = currJsonMemory.getInt("installedcapacity", 0);
    int maxCapacityValue = currJsonMemory.getInt("maxcapacity", 0);
    String typeValue = currJsonMemory.getString("type", null);

    setInstalledCapacity(installedCapacityValue);
    setMaxCapacity(maxCapacityValue);
    setType(typeValue);
 }  

  /**
   * Retrieve the amount of installed memory in MB
   * 
   * @return the installed memory capacity (MB)
   * @see #setInstalledCapacity(int)
   */
  public int getInstalledCapacity() {
    return installedCapacity;
  }

  /**
   * Update the installed memory capacity in MB
   * 
   * @param installedCapacity the installed memory capacity (MB) to set
   * @see #getInstalledCapacity()
   */
  public void setInstalledCapacity(int installedCapacity) {
    if (installedCapacity <= 0) {
      throw new IllegalArgumentException("installedCapacity not > 0!");
    }
    this.installedCapacity = installedCapacity;
  }

  /**
   * Retrieve the maximum possible memory capacity in MB.
   * <p>
   * The difference between the amount of installed memory and this value is
   * the amount of memory that could be added to the computer.
   * 
   * @return the maximum possible memory capacity (MB)
   * @see #setMaxCapacity(int)
   */
  public int getMaxCapacity() {
    return maxCapacity;
  }

  /**
   * Update the maximum possible memory capacity in MB.
   * 
   * @param maxCapacity the maximum possible memory capacity (MB) to set
   * @see #getMaxCapacity()
   */
  public void setMaxCapacity(int maxCapacity) {
    if (maxCapacity <= 0) {
      throw new IllegalArgumentException("maxCapacity not > 0!");
    }
    this.maxCapacity = maxCapacity;
  }

  /**
   * Retrieve the type of memory that the device will accept.
   * 
   * @return the type of memory
   * @see #setType()
   */
  public String getType() {
    return type.getType();
  }

  /**
   * Update the type of memory the device will accept.
   * 
   * @param type the type of memory to set
   * @see #getType()
   */
  public void setType(String type) {
    if (type == null) {
      throw new IllegalArgumentException("type not specified!");
    }

    for (MemoryType memoryType : MemoryType.values()) {
      if (memoryType.getType().equalsIgnoreCase(type)) {
        this.type = memoryType;
        break;
      }
    }
    
    if (this.type == null) {
      throw new IllegalArgumentException("invalid memory type specified!");
    }
  }
    
  /**
   * Create a string containing the attributes of this object. The attribute 
   * string generated has the format "Memory:[<attr-name>:<attr-value,...]".
   */
  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "Memory: [", "]");
    sj.add("installedCapacity: "+Integer.toString(installedCapacity))
      .add("maxCapacity: "+Integer.toString(maxCapacity))
      .add("type: "+getType());
    return sj.toString();
  }
}
