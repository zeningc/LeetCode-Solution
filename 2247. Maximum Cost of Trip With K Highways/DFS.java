class Solution {
    Map<Integer, List<int[]>> map;
    int ans = -1;
    int[][] dp;
    int k;
    public int maximumCost(int n, int[][] highways, int k) {
        if (n < k + 1)
            return -1;
        this.k = k;
        map = new HashMap<>();
        dp = new int[n][1 << n];
        
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        
        for (int i = 0; i < n; i++)
            map.put(i, new LinkedList<>());
        
        for (int[] h : highways)    {
            int u = h[0];
            int v = h[1];
            int c = h[2];
            map.get(u).add(new int[] {v, c});
            map.get(v).add(new int[] {u, c});
        }
            
        for (int i = 0; i < n; i++)
            dfs(i, 1 << i, 0, 0);
        
        return ans;
    }
    
    int dfs(int i, int state, int steps, int sum)  {
        if (steps == k) {
            ans = Math.max(ans, sum);
            return 0;
        }
        
        if (dp[i][state] != -1) {
            ans = Math.max(ans, sum + dp[i][state]);
            return dp[i][state];
        }
        
        int max = -1;
        for (int[] dest : map.get(i))   {
            int v = dest[0];
            int c = dest[1];
            if ((state & (1 << v)) != 0)
                continue;
            max = Math.max(max, c + dfs(v, state | (1 << v), steps + 1, sum + c));
        }
        
        dp[i][state] = max;
        return dp[i][state];
    }
}
