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

  private static boolean      soundState = false;

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
    if (soundState) {
      soundState = false;
    } else {
      soundState = true;
      SineSynth.playAudio();
    }
  }
}
