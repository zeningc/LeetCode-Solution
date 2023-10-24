class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        long[][] dp = new long[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1L;
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= m; i++)
            for (int j = n; j >= group[i - 1]; j--)
                for (int k = minProfit; k >= 0; k--)
                    dp[j][k] = (dp[j][k] + dp[j - group[i - 1]][Math.max(k - profit[i - 1], 0)]) % mod;
        
        return (int)(dp[n][minProfit] % mod);
    }
}