class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int empty = 0;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    ans = bfs(i, j, grid, dist, empty--);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int bfs(int sx, int sy, int[][] grid, int[][] dist, int empty)  {
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        q.offer(new int[] {sx, sy});
        int step = 0;
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                dist[x][y] += step;
                if (x != sx || y != sy)
                    ans = Math.min(ans, dist[x][y]);
                for (int[] d : dir) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != empty)
                        continue;
                    grid[nx][ny] = empty - 1;
                    q.offer(new int[] {nx, ny});
                }
            }
            step++;
        }

        return ans;
    }
}