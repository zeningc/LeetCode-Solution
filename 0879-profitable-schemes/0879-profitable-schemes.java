class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int profitSum = 0;
        int groupSum = 0;
        for (int i = 0; i < m; i++)
        {
            profitSum += profit[i];
            groupSum += group[i];
        }
        long[][] dp = new long[n + 1][minProfit + 1];
        dp[0][0] = 1L;
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= m; i++)
        {
            for (int j = n; j >= 0; j--)
            {
                for (int k = minProfit; k >= 0; k--)
                {
                    dp[j][k] = dp[j][k];
                    if (j - group[i - 1] >= 0)
                        dp[j][k] = (dp[j][k] + dp[j - group[i - 1]][Math.max(k - profit[i - 1], 0)]) % mod;
                }
            }
        }
        
        long ans = 0L;
        for (int i = 0; i <= n; i++)
                ans = (ans + dp[i][minProfit]) % mod;
        
        return (int)(ans % mod);
    }
}