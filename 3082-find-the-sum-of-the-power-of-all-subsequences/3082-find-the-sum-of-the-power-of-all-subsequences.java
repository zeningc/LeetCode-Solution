class Solution {
    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        long power = 1;
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= n; i++) {
            for (int v = k; v >= nums[i - 1]; v--) {
                  for (int j = 1; j <= n; j++) {
                    dp[j][v] = (dp[j][v] + dp[j - 1][v - nums[i - 1]]) % mod;
                }
            }
        }
                                                     
        long ans = 0;
        for (int i = n; i >= 1; i--)    {
            ans = (ans + dp[i][k] * power) % mod;
            power = (power * 2) % mod;
        }
        return (int)(ans % mod);
    }
}