/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

/**
 * Display defines the attributes of the display device attached to a computer.
 * This may be either an internal display or an externally attached display. 
 * For example, a desktop computer has no built-in display while a laptop has
 * an integrated display monitor built-in to its lid.
 * 
 * @author Jim Medlock
 */
public class Display implements IDisplay {
  
  private static float screenDiagonal = 0.0f;
  private static int	 pixelWidth = 0;
  private static int	 pixelHeight = 0;
  
  /**
   * Class constructor. This is the default class constructor with no 
   * parameters. This constructor creates a basic instance of the Display 
   * object with no class variables initialized.
   */
  public Display() {
    
  }
  
  /**
   * Class constructor specifying parameters to assign values to the various
   * attributes of the display.
   * 
   * @param screenDiagonal  Diagonal screen length (inches)
   * @param pixelWidth      Screen width (pixels)
   * @param pixelHeight     Screen height (pixels)
   */
  public Display (float screenDiagonal, int pixelWidth, int pixelHeight) {
    if (screenDiagonal <= 0) {
      throw new IllegalArgumentException("screenDiagonal not > 0!");
    }
	  if (pixelWidth <= 0) {
		  throw new IllegalArgumentException("pixelWidth not > 0!");
	  }
	  if (pixelHeight <= 0) {
		  throw new IllegalArgumentException("pixelHeight not > 0!");
	  }
	  
	  Display.setScreenDiagonal(screenDiagonal);
	  Display.setPixelWidth(pixelWidth);
	  Display.setPixelHeight(pixelHeight);
  }

  /**
   * Retrieve the screen width in pixels
   * 
   * @return Screen width in pixels
   */
  public static int getPixelWidth() {
    return pixelWidth;
  }

  /**
   * Update the screen width in pixels
   * 
   * @param pixelWidth Screen width in pixels
   */
  public static void setPixelWidth(int pixelWidth) {
    Display.pixelWidth = pixelWidth;
  }

  /**
   * Retrieve the screen height in pixels
   * 
   * @return Screen height in pixels
   */
  public static int getPixelHeight() {
    return pixelHeight;
  }

  /**
   * Update the screen height in pixels
   * 
   * @param pixelHeight Screen height in pixels
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

}
