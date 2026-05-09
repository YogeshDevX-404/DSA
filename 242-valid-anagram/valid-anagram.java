class Solution {
    public boolean isAnagram(String s, String t) {
        // Different lengths cannot be anagrams
        if (s.length() != t.length()) return false;

        // Array to store counts of 26 lowercase letters
        int[] charCount = new int[26];

        // Increment for s, decrement for t
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }

        // If it's an anagram, all counts must be 0
        for (int count : charCount) {
            if (count != 0) return false;
        }
        
        return true;
    }
}