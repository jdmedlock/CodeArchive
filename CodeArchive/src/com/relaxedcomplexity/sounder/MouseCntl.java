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
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
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
/**
 * 
 */
package com.relaxedcomplexity.sounder;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * @author jim.medlock
 *
 */
public class MouseCntl implements MouseListener, MouseMotionListener, MouseWheelListener {

  private static final Logger      logger         = Logger
      .getLogger("com.relaxedcomplexity.devicecntl");
  private              SoundState soundState = null;
  private static Stack<MouseEvent> mousePositions = new Stack<MouseEvent>();

  public static enum Direction {
    UP(1), DOWN(2), LEFT(3), RIGHT(4);

    Direction(int value) {
      this.value = value;
    }

    private final int value;

    public int value() {
      return value;
    }
  };

  public static Direction directionOfMovement;

  /**
   * MouseCntl Constructor
   */
  public MouseCntl(SoundState soundState) {
    this.soundState = soundState;
  }
  
  // -------------------------------------------------------------------------
  // Mouse Listener Event Handlers
  // -------------------------------------------------------------------------

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
   * MouseWheelEvent)
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent mouseWheelEvt) {
    Sounder.addToInfoArea("Mouse wheel was moved");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent mouseEvt) {
    Sounder.addToInfoArea("Mouse was dragged");
  }

  /*
   * Process a mouse move event
   * 
   * @see
   * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent mouseEvt) {
    Sounder.addToInfoArea("Mouse was moved");

    // Determine the current direction
    try {
      MouseEvent mouseLastPos = mousePositions.pop();
      determineDirection(mouseEvt, mouseLastPos);
      Sounder.addToInfoArea("...moved " + directionOfMovement);
    } catch (EmptyStackException ese) {
      // Catch the case of the first mouse movement where
      // there is nothing on the stack. Do nothing.
    }

    // Save the current mouse position for use next time
    mousePositions.push(mouseEvt);

    // Modify the sound based on the mouse movement
    //player.modifySound(directionOfMovement);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent mouseEvt) {
    Sounder.addToInfoArea("Mouse was clicked");
    if ((mouseEvt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
      Sounder.addToInfoArea("...left button");

      // Play the sound
      soundState.toggleSound();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent mouseEvt) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent mouseEvt) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent mouseEvt) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent mouseEvt) {
    // TODO Auto-generated method stub

  }

  // -------------------------------------------------------------------------
  // Utility Methods
  // -------------------------------------------------------------------------

  /**
   * Determine direction of mouse movement
   * <p>
   * When the user moves the mouse compare its current position to its position
   * captured when it was last moved to determine if it was moved horizontally
   * (i.e. left,right) or vertically (i.e. up, down).
   * <p>
   * It is important to keep in mind that mouse movements may occur across two
   * of the four axes. When calculating the direction this is taken into account
   * and the movement with the direction with highest delta from the prior
   * position of the mouse is chosen as the current direction.
   * <p>
   * Prior to exiting one of four class variables will be updated to indicate
   * the direction of movement. Note that direction is mutually exclusive so
   * only one direction will be indicated at any point in time.
   * 
   * @param currentPos
   *          MouseEvent associated with current mouse position
   * @param priorPos
   *          MouseEvent associated with last mouse position
   * @return Code indicating the direction of movement
   */
  private Direction determineDirection(MouseEvent currentPos, MouseEvent priorPos) {
    int deltaX = priorPos.getX() - currentPos.getX();
    int deltaY = priorPos.getY() - currentPos.getY();

    if (Math.abs(deltaX) > Math.abs(deltaY)) {
      if (deltaX >= 0) {
        directionOfMovement = Direction.LEFT;
      } else {
        directionOfMovement = Direction.RIGHT;
      }
    } else {
      if (deltaY >= 0) {
        directionOfMovement = Direction.UP;
      } else {
        directionOfMovement = Direction.DOWN;
      }
    }
    return directionOfMovement;
  }
}
