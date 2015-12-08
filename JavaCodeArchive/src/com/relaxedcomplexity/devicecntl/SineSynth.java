package com.relaxedcomplexity.devicecntl;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Generate a sine wave based sound.
 * 
 * The original version of this class was copied from a Stack Overflow 
 * post located at:
 * 
 * 	http://stackoverflow.com/questions/8632104/sine-wave-sound-generator-in-java?rq=1
 * 
 * @author jim.medlock
 *
 */

public class SineSynth {
    
   protected static final int SAMPLE_RATE = 16 * 1024;

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