class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] time = new int[1 << n];
        
        for (int i = 0; i < (1 << n); i++)  {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)
                    sum += jobs[j];
            }
            time[i] = sum;
        }
        
        int[][] dp = new int[k][1 << n];
        
        for (int i = 0; i < (1 << n); i++)
            dp[0][i] = time[i];
        
        for (int i = 1; i < k; i++) {
            for (int state = 0; state < (1 << n); state++)  {
                dp[i][state] = Integer.MAX_VALUE;
                for (int subset = state; subset >= 0; subset = (subset - 1) & state) {
                    dp[i][state] = Math.min(dp[i][state], Math.max(dp[i - 1][state - subset], time[subset]));
                    if (subset == 0)
                        break;
                }
            }
        }
        
        return dp[k - 1][(1 << n) - 1];
    }
}
