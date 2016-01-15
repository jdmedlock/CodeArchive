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
package com.relaxedcomplexity.sounder;

import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * SineSynth simplifies the process of interfacing with the audio system by
 * acting as a facade for the AudioFormat and AudioSystem libraries. This 
 * object must run in its own thread.
 * <p>
 * The original version of this class was copied from a Stack Overflow post
 * located at:
 * <p>
 * http://stackoverflow.com/questions/8632104/sine-wave-sound-generator-in-java?
 * rq=1
 * 
 * @author Jim Medlock
 *
 */

public class SineSynth {

  private static final Logger   logger      = Logger.getLogger("com.relaxedcomplexity.devicecntl");
  protected static final int    SAMPLE_RATE = 16 * 1024;

  private static AudioFormat    af          = null;
  private static SourceDataLine line        = null;
  
  // -------------------------------------------------------------------------
  // Sound Generation Methods
  // -------------------------------------------------------------------------

  /**
   * Close the audio output line
   * <p>
   * The line will be closed only if it is open.
   */
  public void closeAudio() {
    if (line != null) {
      line.drain();
      line.close();
    }
  }

  /**
   * Generate a sine wave buffer of a given frequency and duration
   * 
   * @param freq Frequency to be used to generate the sound
   * @param ms Duration in milliseconds (ms)
   * @return byte array containing the generated sound
   */

  private byte[] createSineWaveBuffer(double freq, int ms) {
    // Test preconditions
    if (freq < SoundPlayer.STARTINGPITCH || freq > SoundPlayer.ENDINGPITCH) {
      throw new IllegalArgumentException("Invalid freq of "+freq+" passed.");
    }
    if (ms <= 0) {
      throw new IllegalArgumentException("Invalid ms of "+ms+" passed.");
    }

    // Create the sound
    int samples = (int) ((ms * SAMPLE_RATE) / 1000);
    byte[] output = new byte[samples];
    double period = (double) SAMPLE_RATE / freq;

    for (int i = 0; i < output.length; i++) {
      double angle = 2.0 * Math.PI * i / period;
      output[i] = (byte) (Math.sin(angle) * 127f);
    }

    return output;
  }

  /**
   * Open a new audio output line
   * 
   * @param sampleRate Sampling rate for the audio
   * @throws LineUnavailableException
   */
  public void openAudio(int sampleRate) throws LineUnavailableException {
    // Test preconditions
    if (sampleRate <= 0) {
      throw new IllegalArgumentException("Invalid sampleRate of "+sampleRate+" passed.");
    }

    // Open an audio channel
    af = new AudioFormat(sampleRate, 8, 1, true, true);
    line = AudioSystem.getSourceDataLine(af);
    line.open(af, sampleRate);
    line.start();
  }

  /**
   * Play a tone
   * 
   * @param soundPlayer Reference to SoundPlayer object
   */
  public void playAudio(SoundPlayer soundPlayer) {
    // Test preconditions
    if (soundPlayer == null) {
      throw new IllegalArgumentException("Null soundPlayer passed.");
    }

    // Open the audio line if it's not already opened.
    if (af == null) {
      try {
        openAudio(SineSynth.SAMPLE_RATE);
      } catch (LineUnavailableException lue) {
        logger.severe("LineUnavailableException error encountered");
        logger.severe(lue.getMessage());
        lue.printStackTrace();
      }
    }

    // Create and play a sound buffer
    boolean forwardNotBack = true;
    for (double freq = SoundPlayer.STARTINGPITCH; freq <= SoundPlayer.ENDINGPITCH;) {
      byte[] toneBuffer = createSineWaveBuffer(freq, 50);
      int count = line.write(toneBuffer, 0, toneBuffer.length);

      if (forwardNotBack) {
        freq += 20;
        forwardNotBack = false;
      } else {
        freq -= 10;
        forwardNotBack = true;
      }
    }
  }

  /**
   * Set the volume (gain) of the sound
   * 
   * @param newVolume New volume level
   */
  public void adjustVolume(float newVolume) {
    // Test preconditions
    if (newVolume < 0 || newVolume > SoundPlayer.ENDINGVOLUME) {
      throw new IllegalArgumentException("newVolume not in range 0.0-1.0");
    }
    
    // Adjust the volume
    if (line !=null) {
      if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
        FloatControl volume = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(newVolume);
      }
    }
  }
  
  // -------------------------------------------------------------------------
  // Testing Methods
  // -------------------------------------------------------------------------

  /**
   * Testing method used to exercise class functionality
   * 
   * @throws LineUnavailableException
   */
  public void test() throws LineUnavailableException {
    final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
    SourceDataLine line = AudioSystem.getSourceDataLine(af);
    line.open(af, SAMPLE_RATE);
    line.start();

    boolean forwardNotBack = true;
    for (double freq = SoundPlayer.STARTINGPITCH; freq <= SoundPlayer.ENDINGPITCH;) {
      byte[] toneBuffer = createSineWaveBuffer(freq, 50);
      int count = line.write(toneBuffer, 0, toneBuffer.length);

      if (forwardNotBack) {
        freq += 20;
        forwardNotBack = false;
      } else {
        freq -= 10;
        forwardNotBack = true;
      }
    }

    line.drain();
    line.close();
  }

}