class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        
        int[] dp = new int[1 << n];
        
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
            
        for (int state = 0; state < (1 << n); state++)  {
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                if (dp[state - subset] >= Integer.MAX_VALUE / 2)
                    continue;
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    if ((subset & (1 << i)) != 0)
                        cnt += tasks[i];
                }
                if (cnt > sessionTime)
                    continue;
                dp[state] = Math.min(dp[state], dp[state - subset] + 1);
            }
        }
        
        return dp[(1 << n) - 1];
    }
}