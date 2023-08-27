class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size();
        int m = 35;
        long[][][] dp = new long[m][n][2];
        
        for (int j = 0; j < n; j++) {
            dp[0][j][0] = receiver.get(j).longValue();
            dp[0][j][1] = receiver.get(j).longValue();
        }
        
        for (int i = 1; i < m; i++)    {
            for (int j = 0; j < n; j++) {
                int x = (int)dp[i - 1][j][0];
                dp[i][j][0] = dp[i - 1][x][0];
                dp[i][j][1] = dp[i - 1][x][1] + dp[i - 1][j][1];
            }
        }
        
        long ans = 0;
        for (int j = 0; j < n; j++) {
            long profit = j;
            int now = j;
            for (int i = 0; i < m; i++) {
                if ((k & (1l << i)) == 0)
                    continue;
                profit += dp[i][now][1];
                now = (int)dp[i][now][0];
            }
            ans = Math.max(ans, profit);
        }
        
        return ans;
    }
}