class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d)  {
            return -1;
        }
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++)    {
            for (int j = 1; j <= Math.min(i, d); j++)    {
                int diff = 0;
                for (int x = i; x <= n; x++)    {
                    diff = Math.max(diff, jobDifficulty[x - 1]);
                    dp[x][j] = Math.min(dp[x][j], dp[i - 1][j - 1] + diff);
                }
            }
        }
        
        return dp[n][d];
    }
}