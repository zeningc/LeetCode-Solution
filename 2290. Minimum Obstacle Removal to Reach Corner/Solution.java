class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, 0});
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int cnt = curr[2];
            if (x == m - 1 && y == n - 1)
                return cnt;
            
            if (visited[x][y])
                continue;
            
            visited[x][y] = true;
            
            for (int[] d : dir) {
                int dx = d[0];
                int dy = d[1];
                int nx = x + dx;
                int ny = y + dy;
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (visited[nx][ny])
                    continue;
                int nCnt = cnt + (grid[nx][ny] == 1 ? 1 : 0);
                pq.offer(new int[] {nx, ny, nCnt});
            }
        }
        
        return -1;
    }
}
