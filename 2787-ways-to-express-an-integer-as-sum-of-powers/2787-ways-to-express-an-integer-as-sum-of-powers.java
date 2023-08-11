class Solution {
    public int numberOfWays(int n, int x) {
        long[] dp = new long[n + 1];
        int[] element = new int[n + 1];
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= n; i++)
            element[i] = (int)Math.pow(i, x);
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++)    {
            for (int j = n; j >= 0; j--)    {
                if (j >= element[i])
                    dp[j] = (dp[j] + dp[j - element[i]]) % mod;
            }
        }
        
        return (int)(dp[n] % mod);
    }
}