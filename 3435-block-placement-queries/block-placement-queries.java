import java.util.*;

class Solution {
    // Segment Tree to maintain the maximum gap in a coordinate range
    private int[] tree;
    private int maxCoord;

    public List<Boolean> getResults(int[][] queries) {
        // Step 1: Find the maximum coordinate we need to worry about
        maxCoord = 0;
        for (int i = 0; i < queries.length; i++) {
            maxCoord = Math.max(maxCoord, queries[i][1]);
        }
        
        // Dynamic boundary condition ko safe limit par normalize kiya
        maxCoord = Math.min(50000, maxCoord);

        // Initialize Segment Tree. Size 4 * N is a safe standard.
        tree = new int[4 * (maxCoord + 1)];

        // TreeSet to maintain sorted positions of obstacles
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0); // Origin acts as an implicit obstacle
        obstacles.add(maxCoord + 1); // Sentinel boundary

        List<Boolean> results = new ArrayList<>();

        // Bilkul normal for loop index i ke saath
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            int x = queries[i][1];

            if (type == 1) {
                // Agar x range ke andar hai tabhi process karenge
                if (x <= maxCoord) {
                    // Find the immediate obstacles before and after 'x'
                    int prev = obstacles.floor(x);
                    int next = obstacles.ceiling(x);

                    // Add the new obstacle
                    obstacles.add(x);

                    // Update gaps in the Segment Tree
                    update(1, 0, maxCoord, x, x - prev);
                    
                    if (next <= maxCoord) {
                        update(1, 0, maxCoord, next, next - x);
                    }
                }

            } else if (type == 2) {
                int sz = queries[i][2];

                // Case 1: Look at the maximum gap formed by obstacles strictly <= x
                int maxGapInSegment = query(1, 0, maxCoord, 0, x);

                // Case 2: Look at the remaining room between the last obstacle before x and x itself
                int lastObstacleBeforeX = obstacles.floor(x);
                int tailGap = x - lastObstacleBeforeX;

                if (maxGapInSegment >= sz || tailGap >= sz) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }
        }

        return results;
    }

    // Standard Segment Tree Point Update
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    // Standard Segment Tree Range Maximum Query
    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        
        int actualR = Math.min(r, end);
        if (l > actualR) return 0;

        int mid = start + (end - start) / 2;
        int p1 = query(2 * node, start, mid, l, actualR);
        int p2 = query(2 * node + 1, mid + 1, end, l, actualR);
        return Math.max(p1, p2);
    }
}