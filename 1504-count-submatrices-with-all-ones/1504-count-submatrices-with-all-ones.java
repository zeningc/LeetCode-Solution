class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        
        for (int i = 0; i < n; i++)
            if (mat[m - 1][i] == 1){
                dp[m - 1][i] = 1;
                ans++;
            }
        
        for (int i = m - 2; i >= 0; i--)    {
            for (int j = n - 1; j >= 0; j--)    {
                if (mat[i][j] == 0)
                    continue;
                dp[i][j] = 1 + dp[i + 1][j];
                ans += dp[i][j];
            }
        }
        
        for (int i = m - 1; i >= 0; i--)    {
            for (int j = n - 1; j >= 0; j--)    {
                int min = dp[i][j];
                for (int k = j + 1; k < n; k++) {
                    if (mat[i][k] == 0)
                        break;
                    min = Math.min(dp[i][k], min);
                    ans += min;
                }
            }
        }
        
        return ans;
    }
}