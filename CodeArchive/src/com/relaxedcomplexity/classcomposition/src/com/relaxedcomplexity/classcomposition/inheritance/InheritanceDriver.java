/**
 * 
 */
package com.relaxedcomplexity.classcomposition.inheritance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

/**
 * @author Jim Medlock
 *
 */
public class InheritanceDriver {

  /**
   * @param args
   */
  public static void main(String[] args) {
	  try {
        FileReader reader = new FileReader("MyFile.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        JsonObject object = Json.parse(input).asObject();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
              System.out.println(line);
        }
        reader.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
  }

}
