class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int m = costs[0].length;
        int[][] dp = new int[n][m];
        dp[0] = costs[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {
                    if (k == j)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k]);
                }
                dp[i][j] += costs[i][j];
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++)
            ans = Math.min(ans, dp[n - 1][i]);
        return ans;
    }
}