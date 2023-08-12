class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Integer[][] distToThief = new Integer[m][n];
        
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) 
            for (int j = 0; j < n; j++) 
                if (grid.get(i).get(j) == 1)
                    q.offer(new int[] {0, i, j});
            
        while (!q.isEmpty())    {
            int[] poll = q.poll();
            int dist = poll[0];
            int x = poll[1];
            int y = poll[2];
            
            if (distToThief[x][y] != null)
                continue;
            distToThief[x][y] = dist;
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || distToThief[nx][ny] != null)
                    continue;
                
                q.offer(new int[] {dist + 1, nx, ny});
            }
            
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        
        pq.offer(new int[] {distToThief[0][0], 0, 0});
        
        while (!pq.isEmpty())   {
            int[] poll = pq.poll();
            int dist = poll[0];
            int x = poll[1];
            int y = poll[2];
            
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            
            if (x == m - 1 && y == n - 1)
                return dist;
            
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                    continue;
                
                pq.offer(new int[] {Math.min(distToThief[nx][ny], dist), nx, ny});
            }
        }
        
        return -1;
    }
}