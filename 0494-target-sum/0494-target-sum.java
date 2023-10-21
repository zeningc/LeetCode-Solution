class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        int offset = Math.max(sum, Math.abs(target));
        int[][] dp = new int[n + 1][2 * offset + 1];
        dp[0][offset] = 1;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 0; j <= 2 * offset; j++)
            {
                if (j - nums[i - 1] >= 0)
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                if (j + nums[i - 1] <= 2 * offset)
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
            }
        }
        
        return dp[n][target + offset];
    }
}