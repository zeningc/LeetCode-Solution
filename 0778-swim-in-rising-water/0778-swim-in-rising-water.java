class Solution {
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        List<int[]> minPoints = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        
        pq.offer(new int[] {0, 0, grid[0][0]});
        boolean[][] vis = new boolean[m][n];
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            
            if (vis[x][y])
                continue;
            
            vis[x][y] = true;
            int v = cur[2];
            if (x == m - 1 && y == n - 1)
                return v;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                
                pq.offer(new int[] {nx, ny, Math.max(v, grid[nx][ny])});
            }
        
        }
        
        return -1;
    }
}