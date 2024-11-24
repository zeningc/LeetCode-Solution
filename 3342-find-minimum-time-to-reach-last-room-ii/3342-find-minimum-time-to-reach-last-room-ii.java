class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;
        
        boolean[][][] vis = new boolean[m][n][2];
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, 0, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            int s = cur[3];
            if (vis[x][y][s])
                continue;
            vis[x][y][s] = true;
            
            if (x == m - 1 && y == n - 1)
                return t;
            
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny][1 - s])
                    continue;
                pq.offer(new int[] {nx, ny, Math.max(moveTime[nx][ny], t) + (s == 0 ? 1 : 2), 1 - s});
            }
        }
        
        return -1;
    }
}