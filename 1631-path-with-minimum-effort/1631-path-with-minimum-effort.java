class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] vis = new boolean[m][n];
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0, 0});
        while(!pq.isEmpty())    {
            int[] poll = pq.poll();
            int x = poll[1];
            int y = poll[2];
            int curHeight = poll[0];
            
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            
            if (x == m - 1 && y == n - 1)
                return curHeight;
            
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                pq.offer(new int[] {Math.max(curHeight, Math.abs(heights[x][y] - heights[nx][ny])), nx, ny});
            }
        }
        
        return -1;
    }
}