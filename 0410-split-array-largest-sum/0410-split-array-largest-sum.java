class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++)    {
            for (int j = 1; j <= Math.min(i, k); j++)   {
                int sum = 0;
                for (int x = i; x <= n; x++)    {
                    sum += nums[x - 1];
                    dp[x][j] = Math.min(dp[x][j], Math.max(dp[i - 1][j - 1], sum));
                }
            }
        }
        
        return dp[n][k];
    }
}