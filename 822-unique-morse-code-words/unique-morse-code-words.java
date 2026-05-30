import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        // Morse code mappings for 'a' through 'z'
        String[] morseCode = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
            ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", 
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
        };
        
        // Set to store unique transformations
        Set<String> uniqueTransformations = new HashSet<>();
        
        // Process each word
        for (String word : words) {
            StringBuilder transformation = new StringBuilder();
            for (char c : word.toCharArray()) {
                transformation.append(morseCode[c - 'a']);
            }
            // Add the final concatenated Morse string to the set
            uniqueTransformations.add(transformation.toString());
        }
        
        // The size of the set represents the number of unique transformations
        return uniqueTransformations.size();
    }
}