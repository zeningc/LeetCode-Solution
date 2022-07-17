class Solution {
    public long numberOfWays(String s) {
        int n = s.length();
        long[][][] dp = new long[n][2][4];
        char c = s.charAt(0);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j][0] = 1;
            }
        }
        
        dp[0][c - '0'][1] = 1;
        
        for (int i = 1; i < n; i++) {
            c = s.charAt(i);
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k <= 3; k++)    {
                    if (c - '0' == j)   {
                        dp[i][j][k] += dp[i - 1][1 - j][k - 1];
                    }
                    dp[i][j][k] += dp[i - 1][j][k];
                }
            }
        }
        
        return dp[n - 1][0][3] + dp[n - 1][1][3];
    }
}
