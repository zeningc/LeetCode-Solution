class Solution {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp = new int[n][n/3 + 1];
        
        for (int i = 0; i < n; i++) 
            dp[i][1] = i == 0 ? slices[i] : Math.max(slices[i], dp[i - 1][1]);
        
        for (int j = 2; j <= n / 3; j++)    {
            for (int i = 1; i < n - 1; i++) {
                if ((i + 1) < j)
                    continue;
                dp[i][j] = dp[i - 1][j];
                if (i >= 2)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j - 1] + slices[i]);
            }
        }
        
        int ans = dp[n - 2][n / 3];
        
        dp = new int[n][n/3 + 1];
        
        for (int i = 1; i < n; i++) 
            dp[i][1] = Math.max(slices[i], dp[i - 1][1]);
        
        for (int j = 2; j <= n / 3; j++)    {
            for (int i = 2; i < n; i++) {
                if (i < j)
                    continue;
                dp[i][j] = dp[i - 1][j];
                if (i >= 2)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j - 1] + slices[i]);
            }
        }
        
        return Math.max(ans, dp[n - 1][n / 3]);
    }
}
