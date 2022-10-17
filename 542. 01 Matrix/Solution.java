class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        boolean[][] vis = new boolean[m][n];
        int[][] dir = new int[][] {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[] {i, j, 0});
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int step = curr[2];
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            if (mat[x][y] != 0) {
                dist[x][y] = step;
            }
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (vis[nx][ny])
                    continue;
                q.offer(new int[]{nx, ny, step + 1});
            }
        }
        
        return dist;
    }
}
