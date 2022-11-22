class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--)    {
            for (int j = i; j < n; j++) {
                for (int k = i; k <= j; k++)    {
                    int left = k - 1 < i ? 0 : dp[i][k - 1];
                    int right = k + 1 > j ? 0 : dp[k + 1][j];
                    int leftReach = i == 0 ? 1 : nums[i - 1];
                    int rightReach = j == n - 1 ? 1 : nums[j + 1];
                    dp[i][j] = Math.max(dp[i][j], left + right + leftReach * nums[k] * rightReach);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}