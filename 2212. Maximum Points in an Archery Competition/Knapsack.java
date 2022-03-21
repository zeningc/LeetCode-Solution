class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int[] weight = new int[12];
        for (int i = 0; i < 12; i++)    {
            weight[i] = aliceArrows[i] + 1;
        }
        int[] ans = new int[12];
        int[][] dp = new int[13][numArrows + 1];
        
        Arrays.fill(dp[0], 0);
        
        for (int i = 1; i < 12; i++)    {
            for (int j = 0; j <= numArrows; j++)    {
                dp[i][j] = dp[i - 1][j];
                if (j >= weight[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + i);
            }
        }
        
        int j = numArrows;
        for (int i = 11; i > 0; i--)   {
            if (j >= weight[i] && dp[i][j] == dp[i - 1][j - weight[i]] + i) {
                j -= weight[i];
                ans[i] = weight[i];
            }
        }
        ans[0] = j;
        return ans;
    }
}