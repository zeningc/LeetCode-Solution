class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * k];
        
        for (int i = 0; i < 2 * k; i += 2)
            dp[0][i] = Integer.MIN_VALUE;
        
        for (int i = 1; i <= n; i++)    {
            for (int j = 0; j < 2 * k; j++) {
                if (j == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], -prices[i - 1]);
                    continue;
                }
                if (j % 2 == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i - 1]);
                }
                else    {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i - 1]);
                }
            }
        }
        
        int ans = 0;
        for (int i = 1; i < 2 * k; i += 2) {
            ans = Math.max(ans, dp[n][i]);
        }
        
        return ans;
    }
}
