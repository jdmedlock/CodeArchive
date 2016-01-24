/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Relaxed Complexity, LLC
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
/**
 * 
 */
package com.relaxedcomplexity.classcomposition.composition;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

/**
 * CompositionDriver controls the execution of the computer inventory 
 * application using composition-based object model. As such, it contains the 
 * main method that is the entrypoint to this application.
 * <p>
 * Using the composition-based class model for the computer inventory 
 * application allows it to be compared and contrasted with the 
 * inheritance-based model for the same application. Doing this for an 
 * application that has the same functionality allows conclusions to be drawn 
 * regarding the two types of object models.
 * <p>
 * the description of the computers in the inventory of available machines is
 * maintained in the file computers.json. This file is parsed and the attributes
 * of the individuals computers are extracted into JSON objects and are passed
 * to the Computer class constructor which uses them to establish values for
 * the various fields in the instance.
 * 
 * @author Jim Medlock
 *
 */
public class CompositionDriver {
  
  private static ArrayList<Computer> computers = new ArrayList<Computer>();

  /**
   * @param args
   */
  public static void main(String[] args) {
    // Read the json file containing the various Computers and their attributes
    // so we have the data needed to construct the Computer object
    JsonObject computerAttrs = null;
    FileReader reader = null;
	  try {
        reader = new FileReader("/Users/jim.medlock/Development/GitRepos/CodeArchive/CodeArchive/src/com/relaxedcomplexity/classcomposition/src/com/relaxedcomplexity/classcomposition/computers.json");
        computerAttrs = Json.parse(reader).asObject();
        System.out.println(computerAttrs);
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
	  
	  // Parse the JSON object containing the computers and their attributes. 
	  // For each "computer" pass it's attributes to the Computer class 
	  // constructor to build a new instance.
	  int noMembers = computerAttrs.size();
	  while (noMembers > 0) {
	    JsonObject currJsonComputer = computerAttrs.get("computer").asObject();
	    String computerType = currJsonComputer.getString("type", "computer");
	    switch (computerType) {
	      case "desktop":
          computers.add(new Desktop(currJsonComputer));          
	        break;
	      case "laptop":
          computers.add(new Laptop(currJsonComputer));          
	        break;
	      case "smartphone":
          computers.add(new Smartphone(currJsonComputer));          
	        break;
	      case "tablet":
          computers.add(new Tablet(currJsonComputer));          
	        break;
	      default:
	        System.out.println("Invalid computer type: "+ computerType);	        
	    }
      computerAttrs.remove("computer").asObject();
      noMembers = computerAttrs.size();
	  }
	  
	  // Print the attributes of the computers 
	  for(Computer computer : computers ) {
	    computer.print();
	  }
  }

}
