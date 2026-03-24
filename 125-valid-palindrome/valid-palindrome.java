class Solution {
    public boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while(left < right){
            char l = str.charAt(left);
            char r = str.charAt(right);
            if(!Character.isLetterOrDigit(l)){
                left++;
                continue;
            }
            if(!Character.isLetterOrDigit(r)){
                right--;
                continue;
            }
            if(Character.toLowerCase(l) != Character.toLowerCase(r)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}