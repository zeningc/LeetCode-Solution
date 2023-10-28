class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int mod = (int)1e9 + 7;
        int n = types.length;
        long[] dp = new long[target + 1];
        dp[0] = 1L;
        
        for (int i = 1; i <= n; i++)
        {
            int cnt = types[i - 1][0];
            int point = types[i - 1][1];
            for (int j = target; j >= 0; j--)
            {
                for (int k = 1; k <= cnt && j >= k * point; k++)
                {
                    dp[j] = (dp[j] + dp[j - k * point]) % mod;
                }
            }
        }
        
        return (int)(dp[target] % mod);
    }
}