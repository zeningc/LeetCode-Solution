class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int[][] dirs = new int[][] {
            {0, 1}, {0, -1}, {-1, 0}, {1, 0}
        };
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] vis = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(vis[i], -1);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        pq.offer(new int[] {0, 0, grid[0][0]});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int maxMet = cur[2];
            
            if (vis[x][y] != -1)
                continue;
            vis[x][y] = maxMet;
            for (int[] d : dirs)    {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (vis[nx][ny] != -1)
                    continue;
                pq.offer(new int[] {nx, ny, Math.max(maxMet, grid[nx][ny])});
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (vis[i][j] != -1)
                    map.put(vis[i][j], map.getOrDefault(vis[i][j], 0) + 1);
        
        
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(-1, 0);
        int sum = 0;
        for (int i : list)  {
            sum += map.get(i);
            treeMap.put(i, sum);
        }
        
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++)    {
            int q = queries[i];
            Integer lowerKey = treeMap.lowerKey(q);
            ans[i] = treeMap.get(lowerKey);
        }
        
        return ans;
    }
}