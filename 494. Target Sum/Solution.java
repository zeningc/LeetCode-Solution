class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if ((sum - target) % 2 != 0 || sum < target)
            return 0;
        int remain = (sum - target) / 2;
        int[] dp = new int[remain + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++)   {
            for (int j = remain; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        
        return dp[remain];
    }
}
