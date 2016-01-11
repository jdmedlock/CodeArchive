/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Relaxed Complexity, LLC
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
 *******************************************************************************/
package com.relaxedcomplexity.sounder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Maintain attributes of the content area on the screen
 * 
 * @author Jim Medlock
 *
 */
public class ContentArea extends JLabel {
  Dimension minSize = new Dimension(100, 50);

  /**
   * Set background color of background area
   * 
   * @param color New background color
   */
  public ContentArea(Color color) {
    // Test precondition
    if (color == null) {
      throw new IllegalArgumentException("Null color passed.");
    }
    
    // Set the background color
    setBackground(color);
    setOpaque(true);
    setBorder(BorderFactory.createLineBorder(Color.black));
  }

  /**
   * Get the content areas minimum size
   */
  public Dimension getMinimumSize() {
    return minSize;
  }

  /**
   * Get the content areas preferred size
   */
  public Dimension getPreferredSize() {
    return minSize;
  }
}
