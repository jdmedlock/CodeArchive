package com.relaxedcomplexity.sounder;

import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * SineSynth simplifies the process of interfacing with the audio system
 * by acting as a facade for the AudioFormat and AudioSystem libraries.
 * It allows audio output to be created, opened, and closed with only two
 * method calls.
 * 
 * The original version of this class was copied from a Stack Overflow 
 * post located at:
 * 
 * 	http://stackoverflow.com/questions/8632104/sine-wave-sound-generator-in-java?rq=1
 * 
 * @author Jim Medlock
 *
 */

public class SineSynth {
    
	private static final Logger logger = Logger.getLogger("com.relaxedcomplexity.devicecntl");
	protected static final int SAMPLE_RATE = 16 * 1024;

	private static AudioFormat 	af = null;
    private static SourceDataLine	line = null;
    
	/**
	 * Generate a sine wave buffer of a given frequency and duration
	 * 
	 * @param freq Frequency to be used to generate the sound
	 * @param ms Duration in milliseconds (ms)
	 * @return byte array containing the generated sound
	 */
   
	public static byte[] createSineWaveBuffer(double freq, int ms) {
		int samples = (int)((ms * SAMPLE_RATE) / 1000);
		byte[] output = new byte[samples];
		double period = (double)SAMPLE_RATE / freq;
       
		for (int i = 0; i < output.length; i++) {
			double angle = 2.0 * Math.PI * i / period;
			output[i] = (byte)(Math.sin(angle) * 127f);  }

		return output;
	}

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
    public static void playAudio() {
    	// Open the audio line if it's not already opened.
    	if (af == null) {
	        try {
				openAudio(SineSynth.SAMPLE_RATE);
			} catch (LineUnavailableException e) {
				logger.severe("LineUnavailableException error encountered");
				e.printStackTrace();
			}
    	}
    	
    	// Create and play a sound buffer
	    boolean forwardNotBack = true;
	    for(double freq = 400; freq <= 800;)  {
	        byte [] toneBuffer = SineSynth.createSineWaveBuffer(freq, 50);
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
	    
   /**
    * Testing method used to exercise class functionality
    * 
    * @throws LineUnavailableException
    */
	public static void test() throws LineUnavailableException {   
       final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
       SourceDataLine line = AudioSystem.getSourceDataLine(af);
       line.open(af, SAMPLE_RATE);
       line.start();

       boolean forwardNotBack = true;
       for(double freq = 400; freq <= 800;)  {
           byte [] toneBuffer = createSineWaveBuffer(freq, 50);
           int count = line.write(toneBuffer, 0, toneBuffer.length);

           if(forwardNotBack)  {
               freq += 20;  
               forwardNotBack = false;  }
           else  {
               freq -= 10;
               forwardNotBack = true;  
           }
       }

       line.drain();
       line.close();
    }

}