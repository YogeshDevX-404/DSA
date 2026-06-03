import java.util.Arrays;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        // Try Land first, then Water
        long ans1 = solve(landStartTime, landDuration, waterStartTime, waterDuration);
        // Try Water first, then Land
        long ans2 = solve(waterStartTime, waterDuration, landStartTime, landDuration);
        
        return (int) Math.min(ans1, ans2);
    }
    
    private long solve(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int n = start1.length;
        int m = start2.length;
        
        int[][] rides1 = new int[n][2];
        for (int i = 0; i < n; i++) {
            rides1[i][0] = start1[i];
            rides1[i][1] = dur1[i];
        }
        
        int[][] rides2 = new int[m][2];
        for (int i = 0; i < m; i++) {
            rides2[i][0] = start2[i];
            rides2[i][1] = dur2[i];
        }
        
        // Sort both by start time
        Arrays.sort(rides1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(rides2, (a, b) -> Integer.compare(a[0], b[0]));
        
        // prefMinDur2[i] stores the minimum duration in rides2 from 0 to i
        long[] prefMinDur2 = new long[m];
        prefMinDur2[0] = rides2[0][1];
        for (int i = 1; i < m; i++) {
            prefMinDur2[i] = Math.min(prefMinDur2[i - 1], rides2[i][1]);
        }
        
        // suffixMinEnd2[i] stores the minimum absolute finish time (start + dur) from i to m-1
        long[] suffixMinEnd2 = new long[m + 1];
        suffixMinEnd2[m] = Long.MAX_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            suffixMinEnd2[i] = Math.min(suffixMinEnd2[i + 1], (long) rides2[i][0] + rides2[i][1]);
        }
        
        long minFinishTime = Long.MAX_VALUE;
        
        // Extract strictly the start times of rides2 for binary searching
        int[] startTimes2 = new int[m];
        for (int i = 0; i < m; i++) {
            startTimes2[i] = rides2[i][0];
        }
        
        for (int i = 0; i < n; i++) {
            long finish1 = (long) rides1[i][0] + rides1[i][1];
            
            // Find the first ride in category 2 that starts >= finish1
            int idx = lowerBound(startTimes2, finish1);
            
            // Case 1: rides2 that start before finish1 (index 0 to idx - 1)
            if (idx > 0) {
                minFinishTime = Math.min(minFinishTime, finish1 + prefMinDur2[idx - 1]);
            }
            
            // Case 2: rides2 that start after or at finish1 (index idx to m - 1)
            if (idx < m) {
                minFinishTime = Math.min(minFinishTime, suffixMinEnd2[idx]);
            }
        }
        
        return minFinishTime;
    }
    
    // Returns the first index where array[index] >= target
    private int lowerBound(int[] arr, long target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}