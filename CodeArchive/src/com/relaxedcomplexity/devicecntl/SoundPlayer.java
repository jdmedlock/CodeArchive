/**
 * 
 */
package com.relaxedcomplexity.devicecntl;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * SoundPlayer simplifies the process of interfacing with the audio system
 * by acting as a facade for the AudioFormat and AudioSystem libraries.
 * It allows audio output to be created, opened, and closed with only two
 * method calls.
 * 
 * @author Jim Medlock
 *
 */
public class SoundPlayer {
	
    private static AudioFormat 		af = null;
    private static SourceDataLine	line = null;
    
    /**
     * Open a new audio output line
     * 
     * @param sampleRate Sampling rate for the audio
     * @throws LineUnavailableException 
     */
    public static void openAudio(int sampleRate) throws LineUnavailableException {
    	af = new AudioFormat(sampleRate, 8, 1, true, true);
    	line = AudioSystem.getSourceDataLine(af);
    	line.open(af, sampleRate);
    	line.start();
    }
    
    /**
     * Close the audio output line
     * <p>
     * The line will be closed only if it is open.
     */
    public static void closeAudio() {
    	if (line != null) {
    		line.drain();
    		line.close();
    	}
    }

    /**
     * Play a tone
     * 
     * @param toneBuffer byte array containing the tone to be played
     */
    public static void playAudio(byte[] toneBuffer) {
	    boolean forwardNotBack = true;
	    for(double freq = 400; freq <= 800;)  {
	        //byte [] toneBuffer = createSineWaveBuffer(freq, 50);
	        int count = line.write(toneBuffer, 0, toneBuffer.length);
	
	        if(forwardNotBack)  {
	            freq += 20;  
	            forwardNotBack = false;  }
	        else  {
	            freq -= 10;
	            forwardNotBack = true;  
	        }
	    }
    }

}
