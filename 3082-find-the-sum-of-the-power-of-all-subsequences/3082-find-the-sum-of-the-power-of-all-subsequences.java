class Solution {
    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        long[][][] dp = new long[n + 1][n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0][0] = 1;

        long power = 1;
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                for (int v = 1; v <= k; v++)  {
                    dp[i][j][v] = dp[i - 1][j][v];
                    if (nums[i - 1] > v)
                        continue;
                    dp[i][j][v] = (dp[i][j][v] + dp[i - 1][j - 1][v - nums[i - 1]]) % mod;
                }
            }
        }

        long ans = 0;
        for (int i = n; i >= 1; i--)    {
            ans = (ans + dp[n][i][k] * power) % mod;
            power = (power * 2) % mod;
        }
        return (int)(ans % mod);
    }
}