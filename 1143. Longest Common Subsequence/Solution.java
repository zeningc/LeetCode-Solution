class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0;
        for (int i = 1; i <= m; i++)    {
            for (int j = 1; j <= n; j++)    {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        
        return dp[m][n];        
    }
}
