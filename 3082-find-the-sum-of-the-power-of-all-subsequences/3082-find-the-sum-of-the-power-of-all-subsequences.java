class Solution {
    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        long power = 1;
        int mod = (int)1e9 + 7;
        for (int num : nums) {
            for (int v = k; v >= num; v--) {
                  for (int j = 1; j <= n; j++) {
                    dp[j][v] = (dp[j][v] + dp[j - 1][v - num]) % mod;
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