class Solution {
    public int houseOfCards(int n) {
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = dp[i - 1][j] + (j >= (3 * i - 1) ? dp[i - 1][j - (3 * i - 1)] : 0);
        
        return dp[n][n];
    }
}
