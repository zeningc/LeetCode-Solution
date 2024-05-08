class Solution {
    public int minimumTime(int[][] grid) {
        int[][] dirs = new int[][] {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] vis = new boolean[m][n];
        pq.offer(new int[] {0, 0, 0});
        boolean movableAtStart = m > 1 && grid[1][0] <= 1 || n > 1 && grid[0][1] <= 1;
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            if (x == m - 1 && y == n - 1)
                return t;
            
            for (int[] d : dirs)    {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                if (vis[nx][ny])
                    continue;
                if (t + 1 >= grid[nx][ny])  {
                    pq.offer(new int[] {nx, ny, t + 1});
                    continue;
                }
                if (!movableAtStart)
                    continue;
                if ((grid[nx][ny] - t) % 2 == 1)    {
                    pq.offer(new int[] {nx, ny, grid[nx][ny]});
                    continue;
                }
                
                pq.offer(new int[] {nx, ny, grid[nx][ny] + 1});
            }
        }
        
        return -1;
    }
}