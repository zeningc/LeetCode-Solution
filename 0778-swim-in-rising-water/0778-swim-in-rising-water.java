class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, grid[0][0]});
        int[][] dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] vis = new boolean[n][n];
        while (!pq.isEmpty())   {
            int[] poll = pq.poll();
            int x = poll[0];
            int y = poll[1];
            int h = poll[2];
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            if (x == n - 1 && y == n - 1)
                return h;
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                pq.offer(new int[] {nx, ny, Math.max(h, grid[nx][ny])});
            }
        }
        return -1;
    }
}