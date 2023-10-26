class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        
        int[] dp = new int[budget + 1];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = budget; j >= present[i]; j--)
            {
                dp[j] = Math.max(dp[j], dp[j - present[i]] + future[i] - present[i]);
            }
        }
        
        int ans = 0;
        for (int i = 0; i <= budget; i++)
            ans = Math.max(ans, dp[i]);
        
        return ans;
    }
}