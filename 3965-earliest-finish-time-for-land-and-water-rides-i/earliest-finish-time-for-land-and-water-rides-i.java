class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minFinishTime = Integer.MAX_VALUE;

        // Scenario 1: Land Ride -> Water Ride
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                int waterStart = Math.max(landFinish, waterStartTime[j]);
                int currentFinish = waterStart + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, currentFinish);
            }
        }

        // Scenario 2: Water Ride -> Land Ride
        for (int j = 0; j < m; j++) {
            int waterFinish = waterStartTime[j] + waterDuration[j];
            for (int i = 0; i < n; i++) {
                int landStart = Math.max(waterFinish, landStartTime[i]);
                int currentFinish = landStart + landDuration[i];
                minFinishTime = Math.min(minFinishTime, currentFinish);
            }
        }

        return minFinishTime;
    }
}