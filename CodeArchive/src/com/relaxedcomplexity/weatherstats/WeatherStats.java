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
package com.relaxedcomplexity.weatherstats;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class calculates the mean and median for weather observations taken 
 * in 2014 at the Deep Moor observation station on Lake Pend Oreille in 
 * Northeastern Washington. Raw weather observation data for this location
 * is available from http://lpo.dt.navy.mil.
 * 
 * @author jim.medlock
 *
 */
public class WeatherStats {
	
    private static BufferedInputStream urlin = null;
    private static BufferedReader urlrdr = null;
    private static int bufSize = 1024;
    
    // Static accumulation fields. These are used for the 
    // calculation of the mean and median of the various
    // observation points. 
    
    private static long		 currLineNo = 0;
    
    // Define which fields in the sample point line we are
    // actually interesting in calculating statistics for
    private static final int STARTFLDNO = 2;
    private static final int ENDFLDNO = 8;
    private static final int FLDCOUNT = ENDFLDNO - STARTFLDNO + 1;
    
    // The cell positions in these fields correspond to the
    // order of the observation types in the data set.
    private static String[] statNames = {"Air_Temp",  		  "Barometric_Press", "Dew_Point",
    									 "Relative_Humidity", "Wind_Dir", 		  "Wind_Gust", 
    									 "Wind_Speed"};
    private static int[]    countOfObs = new int[FLDCOUNT];
    private static float[]  totalOfObs = new float[FLDCOUNT];
    private static float[]	mean = new float[FLDCOUNT];
    private static float[]	median = new float[FLDCOUNT];
    
    // The observations field is an array of ArrayList's used to 
    // hold the values for the various observation points across
    // all of the retrieved samples.
    private static ArrayList<Float> observations[] = new ArrayList[FLDCOUNT];    

    /**
     * Initialize calculation fields
     */
    private static void initializeCalc() {
    	for (int i=0; i < FLDCOUNT; i++) {
    		countOfObs[i] = 0;
    		totalOfObs[i] = (float) 0.0;
    		mean[i] = (float) 0.0;
    		median[i] = (float) 0.0;
    		observations[i] = new ArrayList();
    	}
    }
    
	/** 
	 * Open a URL so records can then be read from it. Note that this assumes
	 * we are reading from a file.
	 * 
	 * @param url A string containing a completely formed url 
	 */
	public static void openURL(String url) {
		initializeCalc();
        URL from = null;
		try {
			from = new URL("http://lpo.dt.navy.mil/data/DM/Environmental_Data_Deep_Moor_2014.txt");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			urlin = new BufferedInputStream(from.openConnection().getInputStream(),bufSize);
			urlrdr = new BufferedReader(
		            new InputStreamReader(urlin, StandardCharsets.UTF_8));
	    } catch (IOException ioex) {
	    	//TODO: Add exception logic
			ioex.printStackTrace();
	    } catch (SecurityException sx) {
	    	//TODO: Add exception logic
			sx.printStackTrace();
	    }		
	}
	
	/**
	 * Close the URL connection
	 */
	public static void closeURL( ) {
        try {
			urlin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Read a line from the file made available by openURL.
	 */
	public static String readLine() {
		String line = null;
		
	    try {
	    	line = urlrdr.readLine();
	    	currLineNo++;
	    } catch (IOException cioex) {
	    	cioex.printStackTrace();
	    } 
	    
	    return line;
	}
	
	/**
	 * Given a line for the sample point, parse it into individual fields
	 * and process the observations as follows:
	 * - save the value in a sorted list specific to each type of observation
	 * - add the value to a running total for each type of observation
	 * - increment the count for each type of observation
	 */
	public static void collectStats(String sampleLine) {
		String delims = "[ \t]+";
		String[] fields = sampleLine.split(delims);
		if (ENDFLDNO > fields.length) {
			// TODO: throw error if the ending field number exceeds total number of fields
		}
		int j = 0;
		float f = 0;
		for (int i = STARTFLDNO; i <= ENDFLDNO; i++) {
			j = i - STARTFLDNO;
			f = Float.parseFloat(fields[i]);
			countOfObs[j]++;
			totalOfObs[j] = totalOfObs[j] + f;
			try {
			observations[j].add(new Float(f));
			} catch (NullPointerException npe) {
				System.out.println("Current line no: "+currLineNo);
				System.out.println("Current field index i: "+i);
				System.out.println("Current j: "+j);
				System.out.println("float f: "+f);
				npe.printStackTrace();
				throw npe;
			}
		}
	}
	
	/**
	 * Calculate the mean and median of each 
	 */
	public static void calculateStats() {
		int midPoint = 0;
		int noObservations = 0;
		boolean isEven = false;
		for (int i=0; i < FLDCOUNT; i++) {
			Collections.sort(observations[i]);
			mean[i] = totalOfObs[i] / countOfObs[i];
		    noObservations = observations[i].size();
			midPoint = noObservations / 2;
			isEven = ((noObservations % 2) == 0) ? true : false;
			if (isEven) {
				median[i] = (observations[i].get(midPoint) + observations[i].get(midPoint+1)) / 2;
			} else {
				median[i] = observations[i].get(midPoint+1);
			}
		}
	}
	
	/**
	 * Print the calculated statistics
	 */
	public static void printStats() {
		System.out.println("Calculated Statistics");
		System.out.println("=====================\n");
		System.out.println("No. lines read: "+currLineNo+"\n");
		System.out.println("Measurement Type           Mean Median  # Obs.    Sum of Obs.");
		System.out.println("----------------          ----- ------ ------- --------------");
		for (int i=0; i < FLDCOUNT; i++) {
			System.out.printf("%-24s %,6.2f %,6.2f %,7d %,13.2f \n",statNames[i],mean[i],median[i],
					countOfObs[i], totalOfObs[i]);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Begin by opening the raw data located at the Lake Pend Oreille
		// website maintained by the U.S. Navy
		openURL("http://lpo.dt.navy.mil/data/DM/Environmental_Data_Deep_Moor_2014.txt");
	    
		// Loop over the contents of the file, collecting the following 
		// for each sample point.
		// - save the value in a sorted list specific to each type of observation
		// - add the value to a running total for each type of observation
		// - increment the count for each type of observation
	    String sampleLine = null;
	    sampleLine = readLine();		// skip the header line
	    while ((sampleLine = readLine()) != null) {
	    	collectStats(sampleLine);
	    }
	    closeURL();
	    
		// After all sample points have been read calculate the following
		// for each type of observation:
		// - Mean = total of all values divided by the number of values for
		//          each type of observation
		// - Median - for each sorted list created for each type of observation:
		//      - Even number of entries: use the average of the two values in
		//        the middle of the list.
		//      - Odd number of entries: use the middle value. That is, the value 
		//        having the same number of entries before and after it.
		//   
	    calculateStats();
	    printStats();
	}

}
