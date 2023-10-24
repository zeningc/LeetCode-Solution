class Solution {
    public int countPartitions(int[] nums, int k) {
        long total = 0L;
        for (int num : nums)
            total += num;
        if (total < 2 * k)
            return 0;
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= k; j++)
            {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0)
                {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - nums[i - 1]]) % mod;
                }
            }
        }
        
        long totalComb = 1;
        for (int i = 0; i < n; i++)
            totalComb = (totalComb * 2) % mod;
        
        long sum = 0;
        for (int i = 0; i < k; i++)
            sum = (sum + dp[n][i]) % mod;
        
        return (int)((totalComb + 2 * mod - 2 * sum) % mod);
    }
}