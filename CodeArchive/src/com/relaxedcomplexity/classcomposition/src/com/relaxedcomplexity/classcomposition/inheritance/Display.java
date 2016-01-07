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
 * Display defines the attributes of the display device attached to a computer.
 * This may be either an internal display or an externally attached display. 
 * For example, a desktop computer has no built-in display while a laptop has
 * an integrated display monitor built-in to its lid.
 * 
 * @author Jim Medlock
 */
public class Display implements IDisplay {
  
  private boolean integrated = false;
  private float   screenDiagonal = 0.0f;
  private int	    pixelWidth = 0;
  private int	    pixelHeight = 0;
  
  /**
   * Class constructor building a new instance from the "display" attributes
   * stored in the JSON object parameter.
   * 
   * @param currJsonComputer
   */
  public Display(JsonObject currJsonComputer) {
    // Retrieve "display" attributes from the JSON object and add them to the
    // new instance of this object
    JsonObject currJsonDisplay = currJsonComputer.get("display").asObject();
    boolean integratedValue = currJsonDisplay.getBoolean("integrated", false);
    float diagonalValue = currJsonDisplay.getFloat("diagonal", 0.0f);
    int widthValue = currJsonDisplay.getInt("reswidth", 0);
    int heightValue = currJsonDisplay.getInt("resheight", 0);
 
    setIntegrated(integratedValue);
    setScreenDiagonal(diagonalValue);
    setPixelWidth(widthValue);
    setPixelHeight(heightValue);
  }

  /**
   * Retrieve the screen resolution width in pixels per inch
   * 
   * @return Screen resolution width in pixels per inch
   * @see #setPixelWidth(int)
   */
  public int getPixelWidth() {
    return pixelWidth;
  }

  /**
   * Update the screen resolution width in pixels per inch
   * 
   * @param pixelWidth Screen resolution width in pixels per inch
   * @see #getPixelWidth()
   */
  public void setPixelWidth(int pixelWidth) {
    if (pixelWidth <= 0) {
      throw new IllegalArgumentException("pixelWidth not > 0!");
    }
    this.pixelWidth = pixelWidth;
  }

  /**
   * Retrieve the screen resolution height in pixels per inch
   * 
   * @return Screen resolution height in pixels per inch
   * @see #setPixelHeight(int)
   */
  public int getPixelHeight() {
    return pixelHeight;
  }

  /**
   * Update the screen resolution height in pixels per inch
   * 
   * @param pixelHeight Screen resolution height in pixels per inch
   * @see #getPixelHeight()
   */
  public void setPixelHeight(int pixelHeight) {
    if (pixelHeight <= 0) {
      throw new IllegalArgumentException("pixelHeight not > 0!");
    }
    this.pixelHeight = pixelHeight;
  }

  /**
   * Retrieve the diagonal length of the screen in inches
   * 
   * @return Screen diagonal length (inches)
   * @see #setScreenDiagonal(float)
   */
  public float getScreenDiagonal() {
    return screenDiagonal;
  }

  /**
   * Update the diagonal dimension of the display.
   * 
   * @param screenDiagonal Screen diagonal length (inches)
   * @see #getScreenDiagonal()
   */
  public void setScreenDiagonal(float screenDiagonal) {
    if (screenDiagonal <= 0) {
      throw new IllegalArgumentException("screenDiagonal not > 0!");
    }
    this.screenDiagonal = screenDiagonal;
  }

  /**
   * Retreive the integrated display setting
   * 
   * @return Integrated display setting
   * @see #setIntegrated(boolean)
   */
  public boolean isIntegrated() {
    return integrated;
  }

  /**
   * Update the value of the integrated display setting
   * 
   * @param integrated True if builtin display; False if otherwise
   * @see #isIntegrated()
   */
  public void setIntegrated(boolean integrated) {
    this.integrated = integrated;
  }

  /**
   * Create a string containing the attributes of this object. The attribute 
   * string generated has the format "Display:[<attr-name>:<attr-value,...]".
   */
  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "Display: [", "]");
    sj.add("integrated: "+Boolean.toString(integrated))
      .add("screenDiagonal: "+Float.toString(screenDiagonal))
      .add("pixelWidth: "+Integer.toString(pixelWidth))
      .add("pixelHeight: "+Integer.toString(pixelHeight));
    return sj.toString();
  }

}
