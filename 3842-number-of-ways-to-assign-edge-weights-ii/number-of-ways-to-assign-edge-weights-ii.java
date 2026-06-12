import java.util.*;

class Solution {
    private ArrayList<Integer>[] adj;
    private int[][] up;
    private int[] depth;
    private int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        up = new int[n + 1][LOG];
        depth = new int[n + 1];
        
        // Step 1: Precompute depths and direct parents using DFS
        dfs(1, 1, 0);
        
        // Step 2: Fill the binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        
        // Step 3: Precompute powers of 2 for O(1) retrieval
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        int MOD = 1_000_000_007;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        // Step 4: Process queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            
            int lca = getLCA(u, v);
            int k = depth[u] + depth[v] - 2 * depth[lca];
            
            ans[i] = pow2[k - 1];
        }
        
        return ans;
    }
    
    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        up[node][0] = parent;
        for (int neighbor : adj[node]) {
            if (neighbor != parent) {
                dfs(neighbor, node, d + 1);
            }
        }
    }
    
    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        // Bring both nodes to the same depth level
        for (int j = LOG - 1; j >= 0; j--) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = up[u][j];
            }
        }
        
        if (u == v) return u;
        
        // Lift both nodes simultaneously right below their LCA
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        
        return up[u][0];
    }
}