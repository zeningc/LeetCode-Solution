class Solution {
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++)    {
            presum[i] = presum[i - 1] + nums.get(i - 1);
        }
        
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i; k < j; k++)    {
                    boolean leftSplit = k == i || presum[k + 1] - presum[i] >= m;
                    boolean rightSplit = k == j - 1 || presum[j + 1] - presum[k + 1] >= m;
                    boolean left = i == k ? true : dp[i][k];
                    boolean right = k == j - 1 ? true : dp[k + 1][j];
                    dp[i][j] |= left && leftSplit && right && rightSplit;
                }
            }
            
        }
        
        return dp[0][n - 1];
    }
}