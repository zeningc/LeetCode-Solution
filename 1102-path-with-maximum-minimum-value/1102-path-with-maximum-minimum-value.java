class Solution {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] vis = new boolean[m][n];
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[] {grid[0][0], 0, 0});
        
        while (!pq.isEmpty())   {
            int[] poll = pq.poll();
            int score = poll[0];
            int x = poll[1];
            int y = poll[2];
            
            
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            
            if (x == m - 1 && y == n - 1)
                return score;
            
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                
                pq.offer(new int[] {Math.min(score, grid[nx][ny]), nx, ny});
            }
        }
        
        return -1;
    }
}