class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] vis = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(vis[i], -1);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        pq.offer(new int[] {0, 0, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];
            if (vis[x][y] != -1)
                continue;
            vis[x][y] = c;
            
            for (int i = 0; i < dir.length; i++)    {
                int[] d = dir[i];
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny] != -1)
                    continue;
                pq.offer(new int[] {nx, ny, c + (i + 1 == grid[x][y] ? 0 : 1)});
            }
        }
        
        return vis[m - 1][n - 1];
    }
}