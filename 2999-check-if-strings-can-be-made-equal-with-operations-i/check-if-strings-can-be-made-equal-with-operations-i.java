class Solution {
    public boolean check(String s1, String s2, int[] order) {
        return s1.charAt(0) == s2.charAt(order[0]) &&
        s1.charAt(1) == s2.charAt(order[1]) &&
        s1.charAt(2) == s2.charAt(order[2]) &&
        s1.charAt(3) == s2.charAt(order[3]);
    }
    public boolean canBeEqual(String s1, String s2) {
        if(check(s1,s2,new int[] {0,1,2,3})) return true;
        if(check(s1,s2,new int[] {2,1,0,3})) return true;
        if(check(s1,s2,new int[] {0,3,2,1})) return true;
        if(check(s1,s2,new int[] {2,3,0,1})) return true;
        return false;
    }
}