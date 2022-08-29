class Solution {
    public int maxCoins(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int[][] dp = new int[n][n];
        
        for (int l = 1; l <= n; l++)    {
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                int leftmost = i > 0 ? nums[i - 1] : 1;
                int rightmost = j < n - 1 ? nums[j + 1] : 1;
                for (int k = i; k <= j; k++)    {
                    int left = k == i ? 0 : dp[i][k - 1];
                    int right = k == j ? 0 : dp[k + 1][j];
                    dp[i][j] = Math.max(dp[i][j], left + right + leftmost * rightmost * nums[k]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
