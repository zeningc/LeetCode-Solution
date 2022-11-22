class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], (double)(Integer.MIN_VALUE / 2));
        
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++)   {
                int sum = 0;
                for (int x = i; x <= n; x++)    {
                    sum += nums[x - 1];
                    dp[x][j] = Math.max(dp[x][j], dp[i - 1][j - 1] + (double)sum / (x - i + 1));
                }
            }
        }
        
        return dp[n][k];
    }
}
// dp[i][j] ith j groups minimal score
