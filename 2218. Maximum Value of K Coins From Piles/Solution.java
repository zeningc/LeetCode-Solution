class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        int[][] prefix = new int[n][2001];
        int[] len = new int[n];
        for (int i = 0; i < piles.size(); i++)  {
            List<Integer> pile = piles.get(i);
            for (int j = 0; j < pile.size(); j++)   {
                prefix[i][j + 1] = prefix[i][j] + pile.get(j);
            }
            len[i] = pile.size();
        }

        for (int i = 1; i <= n; i++)    {
            for (int j = 1; j <= k; j++)    {
                for (int x = 0; x <= len[i - 1] && x <= j; x++)    {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] + prefix[i - 1][x]);
                }
            }
        }
        
        return dp[n][k];
    }
}
