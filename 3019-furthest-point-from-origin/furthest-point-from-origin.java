class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int under=0,left=0,right=0;
        for(char c:moves.toCharArray()){
            if(c=='_')under++;
            else if(c=='L')left++;
            else right++;
        }
        return Math.abs(right-left)+under;
    }
}