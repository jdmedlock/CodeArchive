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

import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.relaxedcomplexity.sounder.MouseCntl.Direction;

/**
 * 
 * 
 * @author Jim Medlock
 *
 */
public class SoundPlayer {

  private static final Logger logger     = Logger.getLogger("com.relaxedcomplexity.devicecntl");

  private static boolean      soundOn = false;

  /**
   * 
   */
  public void modifySound(MouseCntl.Direction direction) {
    // TODO: Finish coding modifySound method
    switch (direction) {
      case LEFT:
        break;
      case RIGHT:
        break;
      case UP:
        break;
      case DOWN:
        break;
      default:
        logger.severe("Invalid direction passed to modifySound. direction=" + direction);
    }
  }

  /**
   * Toggle the sound on/off.
   * 
   */
  public void toggleSound() {
    if (soundOn) {
      soundOn = false;
    } else {
      soundOn = true;
      SineSynth.playAudio();
    }
  }
}
