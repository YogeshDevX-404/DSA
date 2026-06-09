class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = nums[0];
        int minVal = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }

        long maxSubarrayValue = (long) maxVal - minVal;

        return maxSubarrayValue * k;
    }
}