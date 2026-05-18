class Solution {
    public String addStrings(String n1, String n2) {
        int i = n1.length() - 1;
        int j = n2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += n1.charAt(i--) - '0';
            if (j >= 0) sum += n2.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}