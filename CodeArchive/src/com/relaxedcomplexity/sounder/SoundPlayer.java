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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Maintains the state of the sound system and contains methods that allow
 * the sound to be manipulated by starting and stopping it, as well as changing
 * volume and pitch.
 * <p>
 * SoundPlayer runs on the main thread and facilitates communication between
 * the mouse event handler and the sound system (MouseCntl and SineSynth).
 * 
 * @author Jim Medlock
 *
 */
public class SoundPlayer {
  
  private static final Logger logger = Logger.getLogger("com.relaxedcomplexity.sounder");
  private static final ExecutorService executor = Executors.newSingleThreadExecutor();
  private ReentrantLock lock = new ReentrantLock();
  private static SineSynth sineSynth = null;

  private double       pitch = 0d;
  private double       startingPitch = 400;
  private double       pitchDelta = 20;
  
  private float        volume = 0f;
  private float        startingVolume = 0.5f;
  private float        volumeDelta = .05f;
  
  private boolean      soundPlaying = false;
  
  public SoundPlayer() {
    sineSynth = new SineSynth(this);
    pitch = startingPitch;
    volume = startingVolume;
  }
  
  // -------------------------------------------------------------------------
  // Sound Manipulation Methods
  // -------------------------------------------------------------------------

  /**
   * Modify volume/pitch of the sound
   * 
   * @param direction Direction enum value
   */
  public void modifySound(MouseCntl.Direction direction) {
    // TODO: Finish coding modifySound method
    switch (direction) {
      case LEFT:
        decrPitch();
        break;
      case RIGHT:
        incrPitch();
        break;
      case UP:
        incrVolume();
        break;
      case DOWN:
        decrVolume();
        break;
      default:
        logger.severe("Invalid direction passed to modifySound. direction=" + direction);
    }
  }
  
  // -------------------------------------------------------------------------
  // Pitch Methods
  // -------------------------------------------------------------------------

  /**
   * Decrement the pitch 
   */
  public void decrPitch() {
    lock.lock();
    try {
      pitch -= pitchDelta;
    } finally {
        lock.unlock();
    }
  }
  /**
   * Get current pitch value
   */
  public double getPitch() {
    return pitch;
  }
  
  /**
   * Increment the pitch 
   */
  public void incrPitch() {
    lock.lock();
    try {
      pitch += pitchDelta;
    } finally {
        lock.unlock();
    }
  }
  
  /**
   * Set  current pitch value
   * 
   * @param newPitch New pitch value
   * 
   * TODO: Perform editing on inbound newFrequency value
   */
  public void setPitch(double newPitch) {
    lock.lock();
    try {
      pitch = newPitch;
    } finally {
      lock.unlock();
    }
  }
 
  // -------------------------------------------------------------------------
  // Volume Methods (gain)
  // -------------------------------------------------------------------------

  /**
   * Decrement the volume level
   */
  public void decrVolume() {
    lock.lock();
    try {
      volume -= volumeDelta;
    } finally {
      lock.unlock();
    }
    sineSynth.adjustVolume(getVolume());
  }
  
 /**
   * Get current volume level
   */
  public float getVolume() {
    return volume;
  }
  
  /**
   * Increment the volume level
   */
  public void incrVolume() {
    lock.lock();
    try {
      volume += volumeDelta;
    } finally {
      lock.unlock();
    }
    sineSynth.adjustVolume(getVolume());
  }
  
  /**
   * Set current volume level
   * 
   * @param newVolume New volume level
   * 
   * TODO: Perform editing on inbound newGain value
   */
  public void setVolume(float newVolume) {
    lock.lock();
    try {
      volume = newVolume;
    } finally {
      lock.unlock();
    }
    sineSynth.adjustVolume(getVolume());
  }
 
  // -------------------------------------------------------------------------
  // Sound Playing State Methods
  // -------------------------------------------------------------------------

  /**
   * Get sound state (i.e. current playing or not playing)
   */
  public boolean isSoundPlaying() {
    return soundPlaying;
  }
  
  /**
   * Set the sound indicator on/off
   * 
   * @param soundIndicator boolean indicating if sound is on or off
   */
  public void setSoundPlaying(boolean soundIndicator) {
    lock.lock();
    try {
      soundPlaying = soundIndicator;
    } finally {
      lock.unlock();
    }
  }
  
  /**
   * Toggle sound on/off.
   * 
   * Since playing sound is an operation that's concurrent with event 
   * processing a separate thread must be created for it. 
   * 
   */
  public void toggleSound() {
    if (isSoundPlaying()) {
      setSoundPlaying(false);
    } else {
      setSoundPlaying(true);
      executor.submit(() -> {
        while (isSoundPlaying()) {
          sineSynth.playAudio(this);
        }
      });
     }
  }

}
