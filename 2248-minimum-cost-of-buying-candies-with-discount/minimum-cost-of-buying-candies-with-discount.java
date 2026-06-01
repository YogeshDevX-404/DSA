import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        
        int totalCost = 0;
        int n = cost.length;
        
        // Traverse from right to left (highest to lowest cost)
        // Step by 3 because we process chunks of 3 candies at a time
        for (int i = n - 1; i >= 0; i -= 3) {
            totalCost += cost[i];
            if (i - 1 >= 0) {
                totalCost += cost[i - 1];
            }
        }
        
        return totalCost;
    }
}