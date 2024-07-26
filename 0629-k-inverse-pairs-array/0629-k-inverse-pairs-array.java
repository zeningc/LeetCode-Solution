class Solution {
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= n; i++)
            dp[i][0] = 1;
        
        for (int i = 2; i <= n; i++)    {
            for (int j = 1; j <= k; j++)    {
                if (j >= i)
                    dp[i][j] = (mod + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i]) % mod;
                else
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
            }
        }
        
        return (int)(dp[n][k] % mod);
    }
}

/*
dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + ... + dp[i - 1][j - i + 1]
dp[i][j - 1] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - i];

dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i]
*/