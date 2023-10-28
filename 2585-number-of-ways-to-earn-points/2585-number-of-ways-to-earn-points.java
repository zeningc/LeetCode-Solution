class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int mod = (int)1e9 + 7;
        int n = types.length;
        long[][] dp = new long[n + 1][target + 1];
        dp[0][0] = 1L;
        
        for (int i = 1; i <= n; i++)
        {
            int cnt = types[i - 1][0];
            int point = types[i - 1][1];
            for (int j = 0; j <= target; j++)
            {
                for (int k = 0; k <= cnt && j >= k * point; k++)
                {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k * point]) % mod;
                }
            }
        }
        
        return (int)(dp[n][target] % mod);
    }
}