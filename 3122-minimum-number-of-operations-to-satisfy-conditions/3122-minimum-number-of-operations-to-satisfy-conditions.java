class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cnt = new int[n + 1][10];
        int[][] dp = new int[n + 1][10];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                cnt[i][grid[j][i - 1]]++;
            }
        }
        for (int i = 1; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 9; j++)    {
                for (int k = 0; k <= 9; k++)    {
                    if (k == j)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + m - cnt[i][j]);
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++)
            ans = Math.min(ans, dp[n][i]);
        
        return ans;
    }
}