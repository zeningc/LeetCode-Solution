class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int state = 0; state < (1 << n); state++)  {
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                int ttl = 0;
                for (int j = 0; j < n; j++) {
                    if ((subset & (1 << j)) != 0)   {
                        ttl += tasks[j];
                    }
                }
                
                if (ttl <= sessionTime) {
                    dp[state] = Math.min(dp[state - subset] + 1, dp[state]);
                }
            }
        }
        
        return dp[(1 << n) - 1];
    }
}


// dp[state] = min(dp[state - subset] + 1, dp[state])
