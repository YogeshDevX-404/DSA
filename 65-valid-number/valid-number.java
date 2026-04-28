class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDecimal = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (Character.isDigit(curr)) {
                seenDigit = true;
            } else if (curr == '+' || curr == '-') {
                // Sign must be at start or immediately after 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (curr == 'e' || curr == 'E') {
                // Exponent must appear only once and must follow a digit
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                // Important: Reset seenDigit to ensure the exponent has its own integer
                seenDigit = false;
            } else if (curr == '.') {
                // Decimal point must appear only once and NOT after an exponent
                if (seenDecimal || seenExponent) {
                    return false;
                }
                seenDecimal = true;
            } else {
                // Any other character like 'a', ' ', etc.
                return false;
            }
        }

        return seenDigit;
    }
}