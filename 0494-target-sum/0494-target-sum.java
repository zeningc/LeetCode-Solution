class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int OFFSET = 1000;
        int n = nums.length;
        int[][] dp = new int[n][2 * OFFSET + 1];
        dp[0][nums[0] + OFFSET] += 1;
        dp[0][-nums[0] + OFFSET] += 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 2 * OFFSET; j++)   {
                if (j - nums[i] >= 0)
                    dp[i][j] += dp[i - 1][j - nums[i]];
                if (j + nums[i] <= 2 * OFFSET)
                    dp[i][j] += dp[i - 1][j + nums[i]];
            }
        }
        
        return dp[n - 1][target + OFFSET];
    }
}

/*

dp[i][j] ith jit

*/