class Solution {
    public String reformat(String s) {
        // StringBuilder for maintaining the characters
		StringBuilder sb = new StringBuilder();
        StringBuilder alpha = new StringBuilder();
        StringBuilder digit = new StringBuilder();
        
		// separate digits and alpha numerics
        for (char c : s.toCharArray()){
            if (Character.isDigit(c)) digit.append(c);
            else alpha.append(c);
        }
        
		// If any one of the builder length is greater than other then return empty
        if (Math.abs(alpha.length() - digit.length()) > 1) return "";
        
        int i = 0;
        int j = 0;
        //digit should be added first if its length is greater than alpha
		if (digit.length() > alpha.length()) sb.append(digit.charAt(j++));
        
		// if the complete string is not reached then add alpha and then digit, break when the length is >= to the string length
        while (sb.length() < s.length()){
            sb.append(alpha.charAt(i++));
            if (sb.length() >= s.length()) break;
            sb.append(digit.charAt(j++));
        }
        
        return sb.toString();
    }
}