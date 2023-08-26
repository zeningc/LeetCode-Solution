class Solution {
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        boolean[][] dp = new boolean[n][n];
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + nums.get(i);
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        
        for (int i = n - 1; i >= 0; i--)    {
            for (int j = i + 1; j < n; j++) {
                for (int k = i; k < j; k++) {
                    boolean leftSplit = (k == i || presum[k + 1] - presum[i] >= m) && dp[i][k];
                    boolean rightSplit = (k == j - 1 || presum[j + 1] - presum[k + 1] >= m) && dp[k + 1][j];
                    dp[i][j] |= leftSplit && rightSplit;
                }
            }
        }
        
        return dp[0][n - 1];
    }
}