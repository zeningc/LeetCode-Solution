class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;
        Deque<int[]> q = new LinkedList<>();
        boolean[][][] vis = new boolean[m][n][m + n];
        q.offer(new int[] {0, 0, 1});
        int[][] dir = new int[][] {{1, 0}, {0, 1}};
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int cnt = curr[2];
            
            if (x == m - 1 && y == n - 1 && cnt == 0)
                return true;
            
            if (vis[x][y][cnt])
                continue;
            vis[x][y][cnt] = true;
            
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                int newVal = cnt + (grid[nx][ny] == '(' ? 1 : -1);
                if (newVal < 0)
                    continue;
                if (vis[nx][ny][newVal])
                    continue;
                q.offer(new int[] {nx, ny, newVal});
            }
        }
        
        return false;
    }
}
