class Solution {
    public int numWays(int n, int k) {
        if (n == 1)
            return k;
        if (n == 2)
            return k * k;

        int[][][] dp = new int[n][k][3];
        for (int i = 0; i < k; i++)
            dp[0][i][1] = 1;
        
        for (int i = 0; i < k; i++)
            dp[1][i][1] = k - 1;
        for (int i = 0; i < k; i++)
            dp[1][i][2] = 1;
        
        int pre1 = k * (k - 1);
        int pre2 = k;
        for (int i = 2; i < n; i++) {
            int cur1 = 0;
            int cur2 = 0;
            for (int j = 0; j < k; j++) {
                dp[i][j][1] = pre1 - dp[i - 1][j][1] + pre2 - dp[i - 1][j][2];
                dp[i][j][2] = dp[i - 1][j][1];
                cur1 += dp[i][j][1];
                cur2 += dp[i][j][2];
            }
            pre1 = cur1;
            pre2 = cur2;
        }
        
        return pre1 + pre2;
    }
}