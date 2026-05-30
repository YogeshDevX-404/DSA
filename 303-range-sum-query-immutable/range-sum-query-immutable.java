class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        // Create a prefix array of size N + 1
        prefixSum = new int[nums.length + 1];
        
        // Fill the prefix sum array
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        // O(1) retrieval
        return prefixSum[right + 1] - prefixSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */