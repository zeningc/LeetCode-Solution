class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = new int[][] {
            {0, 1}, {0, -1},
            {1, 0}, {-1, 0}
        };
        Set<Integer> s = new HashSet<>();
        for (int q : queries)
            s.add(q);
        int[] arr = new int[s.size()];
        int[] cnt = new int[s.size()];
        int idx = 0;
        for (int val : s)
            arr[idx++] = val;
        Arrays.sort(arr);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        q.offer(new int[] {0, 0, grid[0][0]});
        int[][] vis = new int[m][n];
        
        for (int[] v : vis)
            Arrays.fill(v, -1);

        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int val = curr[2];
            if (vis[x][y] != -1)
                continue;
            vis[x][y] = val;
            
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (vis[nx][ny] != -1)
                    continue;
                q.offer(new int[] {nx, ny, Math.max(val, grid[nx][ny])});
            }
        }
        
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == -1)
                    continue;
                int val = vis[i][j];
                int lo = 0;
                int hi = arr.length - 1;
                while (lo <= hi)    {
                    int mid = lo + (hi - lo) / 2;
                    if (arr[mid] > val)
                        hi = mid - 1;
                    else
                        lo = mid + 1;
                }
                if (lo < arr.length)    {
                    cnt[lo]++;
                }
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int prevCnt = 0;
        for (int i = 0; i < s.size(); i++)  {
            prevCnt += cnt[i];
            map.put(arr[i], prevCnt);
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++)    {
            ans[i] = map.get(queries[i]);
        }
        
        return ans;
    }
}