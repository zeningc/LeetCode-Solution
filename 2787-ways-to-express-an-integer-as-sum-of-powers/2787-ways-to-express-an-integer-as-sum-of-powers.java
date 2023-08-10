class Solution {
    public int numberOfWays(int n, int x) {
        int dp[] = new int[301],  mod = 1000000007, v;
        dp[0] = 1;
        for (int a = 1; (v = (int)Math.pow(a, x)) <= n; a++)
            for (int i = n; i >= v; i--)
                dp[i] = (dp[i] + dp[i - v]) % mod;
        return dp[n];
    }
}