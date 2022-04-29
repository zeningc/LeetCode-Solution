class Solution {
    public int maximumCost(int n, int[][] highways, int k) {
        int[][] map = new int[n + 1][n + 1];
        int ans = -1;
        for (int i = 0; i < n; i++)
            Arrays.fill(map[i], -1);
        
        for (int[] h : highways)    {
            map[h[0]][h[1]] = h[2];
            map[h[1]][h[0]] = h[2];
        }
        
        int[][] dp = new int[n][1 << n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        
        for (int i = 0; i < n; i++)
            dp[i][1 << i] = 0;
    
        for (int state = 0; state < (1 << n); state++)  {
            int cnt = 0;
            int stateCpy = state;
            
            while (stateCpy != 0)   {
                stateCpy &= (stateCpy - 1);
                cnt++;
            }
            if (cnt == 1 || cnt > k + 1)
                continue;

            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) == 0)
                    continue;
                int prev = state & (((1 << n) - 1) ^ (1 << i));
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != -1 && (state & (1 << j)) != 0 && dp[j][prev] != -1) 
                        dp[i][state] = Math.max(dp[i][state], dp[j][prev] + map[i][j]);
                }
                
                if (cnt == k + 1)
                    ans = Math.max(ans, dp[i][state]);
            }
            
            
        }
        return ans;
    }
}
