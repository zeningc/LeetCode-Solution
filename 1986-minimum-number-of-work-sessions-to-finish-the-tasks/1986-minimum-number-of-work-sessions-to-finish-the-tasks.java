class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int[] cnts = new int[1 << n];
        for (int state = 0; state < (1 << n); state++)  {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) != 0)
                    cnt += tasks[i];
            }
            cnts[state] = cnt;
        }
        int[] dp = new int[1 << n];
        
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
            
        for (int state = 0; state < (1 << n); state++)  {
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                if (dp[state - subset] >= Integer.MAX_VALUE / 2)
                    continue;
                if (cnts[subset] > sessionTime)
                    continue;
                dp[state] = Math.min(dp[state], dp[state - subset] + 1);
            }
        }
        
        return dp[(1 << n) - 1];
    }
}