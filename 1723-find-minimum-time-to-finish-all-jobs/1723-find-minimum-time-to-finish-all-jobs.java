class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        int[] time = new int[1 << n];
        
        for (int i = 0; i < (1 << n); i++)  {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)
                    sum += jobs[j];
            }
            time[i] = sum;
        }
        
        for (int i = 0; i < k; i++) {
            for (int state = (1 << n) - 1; state >= 0; state--) {
                for (int subState = state; subState > 0; subState = (subState - 1) & state)  {
                    int curWorkerState = subState;
                    int preWorkerState = state - curWorkerState;
                    if (dp[preWorkerState] >= Integer.MAX_VALUE / 2)
                        continue;
                    dp[state] = Math.min(dp[state], Math.max(dp[preWorkerState], time[curWorkerState]));
                    if (subState == 0) break;
                }
            }
        }
        
        return dp[(1 << n) - 1];
    }
}