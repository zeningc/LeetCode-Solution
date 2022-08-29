class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++)    {
            for (int j = 0; j <= Math.min(i, n / 2); j++)    {
                if (j != 0)
                    dp[i][j] = dp[i - 1][j - 1] + costs[i - 1][0];
                if (i - 1 >= j)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + costs[i - 1][1]);
            }
        }
        
        return dp[n][n / 2];
    }
}

// dp[i][j] ith person jth person goes to city a

// j <= (i + 1)
// dp[i][j] = min(dp[i - 1][j] + cost[i][b], dp[i - 1][j - 1] + cost[i][a])