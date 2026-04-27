class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        boolean[][] connects = {
            null,                             
            {true,  true,  false, false},        
            {false, false, true,  true},         
            {true,  false, false, true},         
            {false, true,  false, true},          
            {true,  false, true,  false},        
            {false, true,  true,  false}          
        };
        
        int[] dr = {0,  0, -1, 1};
        int[] dc = {-1, 1,  0, 0};  
        int[] opposite = {1, 0, 3, 2};
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            if (r == m - 1 && c == n - 1) return true;
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;
                
                if (connects[grid[r][c]][d] && connects[grid[nr][nc]][opposite[d]]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return false;
    }
}