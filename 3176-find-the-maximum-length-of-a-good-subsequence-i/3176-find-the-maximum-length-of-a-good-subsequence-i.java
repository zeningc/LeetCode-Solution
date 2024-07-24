class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            for (int y = 0; y <= k; y++)    {
                for (int x = 0; x < i; x++) {
                    if (y > 0 && nums[i] != nums[x])
                        dp[i][y] = Math.max(dp[i][y], dp[x][y - 1] + 1);
                    if (nums[i] == nums[x])
                        dp[i][y] = Math.max(dp[i][y], dp[x][y] + 1);
                }
                ans = Math.max(ans, dp[i][y]);
            }
        }
        
        return ans;
    }
}