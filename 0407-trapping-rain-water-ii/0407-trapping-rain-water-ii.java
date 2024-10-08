class Solution {
    public int trapRainWater(int[][] heightMap) {
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = heightMap.length;
        int n = heightMap[0].length;
        
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        for (int i = 0; i < m; i++) {
            pq.offer(new int[] {i, 0, heightMap[i][0]});
            pq.offer(new int[] {i, n - 1, heightMap[i][n - 1]});
        }
        
        for (int j = 1; j < n - 1; j++) {
            pq.offer(new int[] {0, j, heightMap[0][j]});
            pq.offer(new int[] {m - 1, j, heightMap[m - 1][j]});
        }
        
        int h = 0;
        int ans = 0;
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int curHeight = cur[2];
            
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            h = Math.max(curHeight, h);
            ans += h - heightMap[x][y];
            for (int[] d : dir)   {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                pq.offer(new int[] {nx, ny, Math.max(h, heightMap[nx][ny])});
            }
        }
        
        return ans;
    }
}