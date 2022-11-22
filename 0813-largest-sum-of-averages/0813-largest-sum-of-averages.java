class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        double[][] avg = new double[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                avg[i + 1][j + 1] = (double)sum / (j - i + 1);
            }
        }
        
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], (double)(Integer.MIN_VALUE / 2));
        
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++)   {
                for (int x = j; x <= i; x++)    {
                    dp[i][j] = Math.max(dp[i][j], dp[x - 1][j - 1] + avg[x][i]);
                }
            }
        }
        
        return dp[n][k];
    }
}
// dp[i][j] ith j groups minimal score
