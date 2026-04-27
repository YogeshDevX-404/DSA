class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            boolean isValid = true;

            for (int j = 0; j < n; j++) {

                if (i == j) continue;

                if (nums[i] < 2 * nums[j]) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                return i;
            }
        }

        return -1;
    }
    
}