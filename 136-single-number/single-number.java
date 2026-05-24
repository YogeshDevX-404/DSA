class Solution {
    public int singleNumber(int[] nums) {
        int target = 0;
        for(int idx=0; idx < nums.length; idx++){
            target = target ^ nums[idx];
        }
        return target;
    }
}