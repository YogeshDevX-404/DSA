class Solution {
    public int countSegments(String s) {
        if(s.length()<1)return 0;
        int seg=0;
        int flag=0;
        for(int i=0;i<s.length();i++){
            if(flag==0 && s.charAt(i)!=' '){
                flag=1;
                seg++;
            }
            if(flag==1 && s.charAt(i)==' '){
                flag=0;
            }
        }
        return seg;
    }
}