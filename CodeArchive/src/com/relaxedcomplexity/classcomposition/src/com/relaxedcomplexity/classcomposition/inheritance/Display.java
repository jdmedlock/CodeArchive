/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * @author Jim Medlock
 *
 */
public class Display implements IDisplay {
  private static int	pixelWidth = 0;
  private static int	pixelHeight = 0;
  
  public Display (int pixelWidth, int pixelHeight) {
	  if (pixelWidth <= 0) {
		  throw new IllegalArgumentException("pixelWidth not > 0!");
	  }
	  if (pixelHeight <= 0) {
		  throw new IllegalArgumentException("pixelHeight not > 0!");
	  }
	  
	  Display.setPixelWidth(pixelWidth);
	  Display.setPixelHeight(pixelHeight);
  }

  public static int getPixelWidth() {
	return pixelWidth;
  }

  public static void setPixelWidth(int pixelWidth) {
	Display.pixelWidth = pixelWidth;
  }

  public static int getPixelHeight() {
	return pixelHeight;
  }

  public static void setPixelHeight(int pixelHeight) {
	Display.pixelHeight = pixelHeight;
  }

}
