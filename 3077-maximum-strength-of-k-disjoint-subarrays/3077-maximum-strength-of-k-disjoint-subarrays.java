class Solution {
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 1; j <= k; j++)
                dp[i][j] = Long.MIN_VALUE / 2;
        long sign = 1;
        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++)    {
            long mul = k - j + 1;
            long[] cur = new long[n + 1];
            Arrays.fill(cur, Long.MIN_VALUE / 2);
            for (int i = j; i <= n - k + j; i++)    {
                dp[i][j] = Math.max(dp[i - 1][j], prev[i - 1]) + mul * sign * nums[i - 1];
                cur[i] = Math.max(cur[i - 1], dp[i][j]);
            }
            prev = cur;
            sign = -sign;
        }
        
        long ans = Long.MIN_VALUE;
        for (int i = 0; i <= n; i++)
            ans = Math.max(ans, prev[i]);
        
        return ans;
    }
}