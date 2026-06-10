import java.util.PriorityQueue;

class Solution {
    // Inner class to represent a subarray candidate in the priority queue
    class SubarrayState {
        long value;
        int l;
        int r;

        SubarrayState(long value, int l, int r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        
        // 1. Build the Sparse Tables for O(1) Max and Min range queries
        int maxLog = 31 - Integer.numberOfLeadingZeros(n);
        int[][] maxTable = new int[maxLog + 1][n];
        int[][] minTable = new int[maxLog + 1][n];

        for (int i = 0; i < n; i++) {
            maxTable[0][i] = nums[i];
            minTable[0][i] = nums[i];
        }

        for (int j = 1; j <= maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxTable[j][i] = Math.max(maxTable[j - 1][i], maxTable[j - 1][i + (1 << (j - 1))]);
                minTable[j][i] = Math.min(minTable[j - 1][i], minTable[j - 1][i + (1 << (j - 1))]);
            }
        }

        // Helper lambda-like function implementation for O(1) query
        // Returns max(nums[l..r]) - min(nums[l..r])
        java.util.function.BiFunction<Integer, Integer, Long> queryValue = (l, r) -> {
            int len = r - l + 1;
            int log = 31 - Integer.numberOfLeadingZeros(len);
            int maxVal = Math.max(maxTable[log][l], maxTable[log][r - (1 << log) + 1]);
            int minVal = Math.min(minTable[log][l], minTable[log][r - (1 << log) + 1]);
            return (long) maxVal - minVal;
        };

        // 2. Max-Heap to extract the largest subarray values greedily
        PriorityQueue<SubarrayState> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));

        // Initialize the heap with the largest element of each stream: (l, n - 1)
        for (int i = 0; i < n; i++) {
            long val = queryValue.apply(i, n - 1);
            maxHeap.offer(new SubarrayState(val, i, n - 1));
        }

        long totalValue = 0;

        // 3. Pop the top k maximum elements
        while (k > 0 && !maxHeap.isEmpty()) {
            SubarrayState curr = maxHeap.poll();
            totalValue += curr.value;
            k--;

            // If the row stream has more candidates (r > l), push the next one (l, r - 1)
            if (curr.r > curr.l) {
                long nextVal = queryValue.apply(curr.l, curr.r - 1);
                maxHeap.offer(new SubarrayState(nextVal, curr.l, curr.r - 1));
            }
        }

        return totalValue;
    }
}