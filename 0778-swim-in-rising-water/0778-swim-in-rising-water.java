class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, grid[0][0]});
        boolean[][] vis = new boolean[n][n];
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            if (x == n - 1 && y == n - 1)
                return t;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                pq.offer(new int[] {nx, ny, Math.max(t, grid[nx][ny])});
            }
        
        }
        
        return -1;
    }
}