class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = time.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++)    {
                int cap = Math.min(n, j + time[i] + 1);
                dp[i + 1][cap] = Math.min(dp[i + 1][cap], dp[i][j] + cost[i]);
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
            }
        }
        return dp[n][n];
    }
}