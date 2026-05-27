class Solution {
    public int numberOfSpecialChars(String word) {
        // Initialize arrays to store positions. 
        // Using -1 to indicate that the character hasn't been seen yet.
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        
        java.util.Arrays.fill(lastLower, -1);
        java.util.Arrays.fill(firstUpper, -1);
        
        // Traverse the string to record the indices
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            
            if (Character.isLowerCase(ch)) {
                lastLower[ch - 'a'] = i; // Always update to get the LAST occurrence
            } else {
                int idx = ch - 'A';
                if (firstUpper[idx] == -1) {
                    firstUpper[idx] = i; // Only update if it's the FIRST occurrence
                }
            }
        }
        
        int specialCount = 0;
        
        // Check the condition for each letter from 'a' to 'z'
        for (int i = 0; i < 26; i++) {
            // Both must exist, and the last lowercase must be before the first uppercase
            if (lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }
}