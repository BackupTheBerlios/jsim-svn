/*
 * Serializer.java
 *
 * Created on 28 November 2006, 21:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appserver;

/**
 *
 * @author Roger
 */
public class Serializer {
    
    /**
     * Creates a new instance of Serializer
     */
    public Serializer() {
        
    }
    
     public String[] ExtractSubstrings(String text) {
        //Horton: 'Beginning Java Ch 4'
        int count = 0;                               // Number of substrings
        char separator = ' ';                        // Substring separator

        // Determine the number of substrings
        int index = 0;
        do {
          ++count;                                   // Increment count of substrings
          ++index;                                   // Move past last position
          index = text.indexOf(separator, index);
        } while (index != -1);

        // Extract the substring into an array
        String[] subStr = new String[count];         // Allocate for substrings
        index = 0;                                   // Substring start index
        int endIndex = 0;                            // Substring end index
        for(int i = 0; i < count; i++) {
          endIndex = text.indexOf(separator,index);  // Find next separator

          if(endIndex == -1) {                       // If it is not found
            subStr[i] = text.substring(index);       // extract to the end
          } else {                                         // otherwise
            subStr[i] = text.substring(index, endIndex);   // to end index
          }

          index = endIndex + 1;                      // Set start for next cycle
        }
        return subStr;
      }         
}
