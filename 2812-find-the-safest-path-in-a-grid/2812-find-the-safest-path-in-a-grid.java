class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid.get(i).get(j) == 1)
                    q.offer(new int[] {i, j, 0});
        boolean[][] vis = new boolean[n][n];
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] minDist = new int[n][n];
        while (!q.isEmpty())    {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];
            if (vis[x][y])
                continue;
            minDist[x][y] = dist;
            vis[x][y] = true;
            for (int[] d : dir) {
                int dx = d[0];
                int dy = d[1];
                int nx = x + dx;
                int ny = y + dy;
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                q.offer(new int[] {nx, ny, dist + 1});
            }
        }
        vis = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[] {minDist[0][0], 0, 0});
        int ans = Integer.MAX_VALUE;
        while (!pq.isEmpty())   {
            int[] poll = pq.poll();
            int curDist = poll[0];
            int x = poll[1];
            int y = poll[2];
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            ans = Math.min(curDist, ans);
            if (x == n - 1 && y == n - 1)
                break;
            
            for (int[] d : dir) {
                int dx = d[0];
                int dy = d[1];
                int nx = x + dx;
                int ny = y + dy;
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                int nextDist = minDist[nx][ny];
                pq.offer(new int[] {nextDist, nx, ny});
            }
        }
        
        return ans;
    }
    
    int mhtDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}