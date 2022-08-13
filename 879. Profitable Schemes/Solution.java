class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        int mod = (int)1e9 + 7;
        long[][] dp = new long[n + 1][minProfit + 1];
        
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;
        
        for (int i = 0; i < len; i++)   {
            for (int j = n; j >= group[i]; j--) {
                for (int k = minProfit; k >= 0; k--)    {
                    dp[j][k] = (dp[j][k] + dp[j - group[i]][Math.max(0, k - profit[i])]) % mod;;
                }
            }
        }
        
        return (int)(dp[n][minProfit] % mod);
    }
}
