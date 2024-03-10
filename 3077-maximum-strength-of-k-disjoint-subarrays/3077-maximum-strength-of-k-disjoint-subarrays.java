class Solution {
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n][k];
        for (int j = 0; j < k; j++)
            for (int i = 0; i < n; i++)
                dp[i][j] = Long.MIN_VALUE / 2;
        int sign = 1;
        long[] prev = new long[n];
        for (int j = 0; j < k; j++) {
            long mul = k - j;
            int left = k - j - 1;
            for (int i = j; i < n - left; i++) {
                dp[i][j] = Math.max(i == 0 ? Long.MIN_VALUE / 2 : dp[i - 1][j], j == 0 ? 0 : prev[i - 1]) + mul * sign * nums[i];
            }
            
            prev = new long[n];
            Arrays.fill(prev, Long.MIN_VALUE / 2);
            for (int i = j; i < n - left; i++) {
                prev[i] = Math.max(i == 0 ? Long.MIN_VALUE / 2 : prev[i - 1], dp[i][j]);
            }
            sign = -sign;
        }
        
        long ans = Long.MIN_VALUE;
        for (int i = k - 1; i < n; i++)
            ans = Math.max(ans, prev[i]);
        
        return ans;
    }
}