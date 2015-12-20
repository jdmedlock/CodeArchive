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
 *
 *******************************************************************************/
package com.relaxedcomplexity.sounder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Manage the user interface
 * <p>
 * This program demonstrates how to control devices by controlling the pitch and
 * volume of a sound on the local computer using the mouse. Pressing and
 * releasing the right mouse button starts and stops the sound, moving the mouse
 * up and down adjusts the volume while moving it left and right adjusts the
 * pitch.
 * <p>
 * Since two discrete "things" must interact, namely mouse and sound,t his is an
 * interesting example of how to use design patterns to both simplify the code
 * and make it easily maintainable and extensible. 
 * 
 * @author Jim Medlock
 *
 */
public class Sounder extends JPanel {

  private static final Logger logger    = Logger.getLogger("com.relaxedcomplexity.devicecntl");
  private static final String NEWLINE   = System.getProperty("line.separator");

  private static ContentArea  contents  = null;
  private static JTextArea    infoArea  = null;
  private static MouseCntl    mouseCntl = null;

  /**
   * 
   * @param args
   *          An array of parameters to be used by the program. However, this
   *          are not necessary for this program and are ignored.
   * @throws LineUnavailableException
   */
  public static void main(String[] args) {
    logger.entering(Sounder.class.getSimpleName(), "main");

    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InstantiationException ex) {
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }

    // Since Swing objects aren't thread-safe we need to schedule a job
    // on the event dispatch thread to create and process events for
    // this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createGUI();
      }
    });

    logger.exiting(Sounder.class.getSimpleName(), null);
  }

  /**
   * Create the user interface the user will interact with to this program. This
   * method must be invoked from the event dispatch thread to ensure thread
   * safety.
   */
  private static void createGUI() {

    // Create and set up the window.
    JFrame frame = new JFrame("Device Control");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create and set up the content pane.
    JComponent newContentPane = new Sounder();
    newContentPane.setOpaque(true); // content panes must be opaque
    frame.setContentPane(newContentPane);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Define the GUI components and attach the event listners
   */
  public Sounder() {
    super(new GridLayout(0, 1));
    contents = new ContentArea(Color.CYAN);
    add(contents);

    infoArea = new JTextArea();
    infoArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(infoArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(200, 75));
    add(scrollPane);

    // Register for mouse events on blankArea and the panel.
    mouseCntl = new MouseCntl();
    contents.addMouseListener(mouseCntl);
    addMouseListener(mouseCntl);
    contents.addMouseMotionListener(mouseCntl);
    addMouseMotionListener(mouseCntl);

    setPreferredSize(new Dimension(450, 450));
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  /**
   * Display a message in the information area of the GUI window
   * 
   * @param aText
   *          Text to include in the message
   */
  public static void addToInfoArea(String aText) {
    infoArea.append(aText + "." + NEWLINE);
    infoArea.setCaretPosition(infoArea.getDocument().getLength());
  }
}
