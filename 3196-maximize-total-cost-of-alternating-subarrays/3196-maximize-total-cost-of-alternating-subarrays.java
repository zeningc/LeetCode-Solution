class Solution {
    public long maximumTotalCost(int[] nums) {
        long[][] dp = new long[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++)   {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + nums[i];
            dp[i][1] = dp[i - 1][0] - nums[i];
        }
        
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}