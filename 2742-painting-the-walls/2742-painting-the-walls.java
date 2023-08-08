class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][2 * n + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        
        dp[0][n] = 0;
        for (int i = 0; i < n; i++)    {
            for (int j = 0; j <= 2 * n; j++)    {
                if (dp[i][j] >= Integer.MAX_VALUE / 2)
                    continue;
                int cappedJ = Math.max(0, j - 1);
                dp[i + 1][cappedJ] = Math.min(dp[i + 1][cappedJ], dp[i][j]);
                cappedJ = Math.min(j + time[i], 2 * n);
                dp[i + 1][cappedJ] = Math.min(dp[i + 1][cappedJ], dp[i][j] + cost[i]);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = n; i <= 2 * n; i++)
            ans = Math.min(ans, dp[n][i]);
        return ans;
        
    }
}