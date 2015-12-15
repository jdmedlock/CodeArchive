/**
 * 
 */
package com.relaxedcomplexity.devicecntl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * @author jimmedlock
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
