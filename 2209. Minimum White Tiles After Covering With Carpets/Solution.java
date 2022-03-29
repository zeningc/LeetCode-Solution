class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        floor = "#" + floor;
        int[][] dp = new int[n + 1][numCarpets + 1];
        dp[0][0] = 0;
        for (int i=1; i<=n; i++)    {
            for (int j=0; j<=numCarpets; j++)   {
                dp[i][j] = dp[i-1][j] + (floor.charAt(i)=='1' ? 1 : 0);                               
                if (j>=1)
                    dp[i][j] = Math.min(dp[i][j], i>=carpetLen ? dp[i-carpetLen][j-1]:0);
            }
        }
        return dp[n][numCarpets];
    }
}