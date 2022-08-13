class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[][] dp = new int[n + 1][2];
        dp[0][0] = Integer.MIN_VALUE;
        
        for (int i = 1; i <= n; i++)    {
            dp[i][0] = Math.max(dp[i - 1][0], (i >= 2 ? dp[i - 2][1] : 0) - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }
        
        return Math.max(dp[n][0], dp[n][1]);
    }
}
