class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m + 1][target + 1][n + 1];
        
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= target; j++)
                for (int k = 0; k <= n; k++)
                    dp[i][j][k] = Integer.MAX_VALUE / 2;

        for (int k = 0; k <= n; k++)
            dp[0][0][k] = 0;
        
        for (int i = 1; i <= m; i++)    {
            if (houses[i - 1] != 0) {
                for (int j = 1; j <= Math.min(target, i); j++)   {
                    for (int k = 1; k <= n; k++)    {
                        if (k == houses[i - 1] && i != 1)
                            dp[i][j][houses[i - 1]] = Math.min(dp[i][j][houses[i - 1]], dp[i - 1][j][houses[i - 1]]);
                        else
                            dp[i][j][houses[i - 1]] = Math.min(dp[i][j][houses[i - 1]], dp[i - 1][j - 1][k]);
                    }
                }
                    
                continue;
            }
            for (int j = 1; j <= Math.min(target, i); j++)   {
                for (int k = 1; k <= n; k++)    {
                    dp[i][j][k] = Integer.MAX_VALUE;
                    for (int x = 1; x <= n; x++)    {
                        if (x == k && i != 1) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][x] + cost[i - 1][k - 1]);
                        }
                        else    {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j - 1][x] + cost[i - 1][k - 1]);
                        }
                    }
                }
            }
        }
        
        int ans = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= n; i++)    {
            ans = Math.min(ans, dp[m][target][i]);
        }
        
        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
    }
}