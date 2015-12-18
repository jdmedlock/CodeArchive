/**
 * 
 */
package com.relaxedcomplexity.sounder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Portions of the main, createGui, and GUICntl methods were copied from the
 * following which is copyrighted by Oracle:
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 * 
 * @author Jim Medlock
 *
 */
public class ContentArea extends JLabel {
  Dimension minSize = new Dimension(100, 50);

  public ContentArea(Color color) {
    setBackground(color);
    setOpaque(true);
    setBorder(BorderFactory.createLineBorder(Color.black));
  }

  public Dimension getMinimumSize() {
    return minSize;
  }

  public Dimension getPreferredSize() {
    return minSize;
  }
}
