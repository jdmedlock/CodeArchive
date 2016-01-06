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
  
  private static boolean  integrated = false;
  private static float    screenDiagonal = 0.0f;
  private static int	    pixelWidth = 0;
  private static int	    pixelHeight = 0;
  
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
    setDisplayAttributes(integratedValue, diagonalValue, widthValue, heightValue);
 }

  /**
   * Helper method accepting parameters used to assign values to the various
   * attributes of the display.
   * 
   * @param screenDiagonal  Diagonal screen length (inches)
   * @param pixelWidth      Screen resolution width (pixels per inch)
   * @param pixelHeight     Screen resolution height (pixels per inch)
   */
  public void setDisplayAttributes (boolean integrated, float screenDiagonal, 
                                    int pixelWidth, int pixelHeight) {
    if (screenDiagonal <= 0) {
      throw new IllegalArgumentException("screenDiagonal not > 0!");
    }
	  if (pixelWidth <= 0) {
		  throw new IllegalArgumentException("pixelWidth not > 0!");
	  }
	  if (pixelHeight <= 0) {
		  throw new IllegalArgumentException("pixelHeight not > 0!");
	  }
	  
	  Display.setIntegrated(integrated);
	  Display.setScreenDiagonal(screenDiagonal);
	  Display.setPixelWidth(pixelWidth);
	  Display.setPixelHeight(pixelHeight);
  }
  
  /**
   * Retrieve the screen resolution width in pixels per inch
   * 
   * @return Screen resolution width in pixels per inch
   */
  public static int getPixelWidth() {
    return pixelWidth;
  }

  /**
   * Update the screen resolution width in pixels per inch
   * 
   * @param pixelWidth Screen resolution width in pixels per inch
   */
  public static void setPixelWidth(int pixelWidth) {
    Display.pixelWidth = pixelWidth;
  }

  /**
   * Retrieve the screen resolution height in pixels per inch
   * 
   * @return Screen resolution height in pixels per inch
   */
  public static int getPixelHeight() {
    return pixelHeight;
  }

  /**
   * Update the screen resolution height in pixels per inch
   * 
   * @param pixelHeight Screen resolution height in pixels per inch
   */
  public static void setPixelHeight(int pixelHeight) {
    Display.pixelHeight = pixelHeight;
  }

  /**
   * Retrieve the diagonal length of the screen in inches
   * 
   * @return Screen diagonal length (inches)
   */
  public static float getScreenDiagonal() {
    return screenDiagonal;
  }

  /**
   * Update the diagonal dimension of the display.
   * 
   * @param screenDiagonal Screen diagonal length (inches)
   */
  public static void setScreenDiagonal(float screenDiagonal) {
    Display.screenDiagonal = screenDiagonal;
  }

  /**
   * Retreive the integrated display setting
   * 
   * @return Integrated display setting
   */
  public static boolean isIntegrated() {
    return integrated;
  }

  /**
   * Update the value of the integrated display setting
   * 
   * @param integrated True if builtin display; False if otherwise
   */
  public static void setIntegrated(boolean integrated) {
    Display.integrated = integrated;
  }

}
