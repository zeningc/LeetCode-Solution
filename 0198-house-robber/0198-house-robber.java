class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = 0;
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = max + nums[i];
            max = Math.max(max, dp[i - 1]);
        }
        return Math.max(dp[n - 1], max);
    }
}