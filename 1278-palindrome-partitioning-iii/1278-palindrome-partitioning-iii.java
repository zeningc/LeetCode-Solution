class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    f[i][j] = 0;
                    continue;
                }
                else if (i + 1 == j)    {
                    f[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                }
                else    {
                    f[i][j] = s.charAt(i) == s.charAt(j) ? f[i + 1][j - 1] : f[i + 1][j - 1] + 1;
                }
            }
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        // for (int i = 0; i <= k; i++)
        //     dp[i][0] = 0;
        dp[0][0] = 0;
        
        for (int j = 1; j <= k; j++)    {
            for (int i = j; i <= n; i++)    {
                for (int x = i; x > j - 1; x--) {
                    dp[i][j] = Math.min(dp[i][j], dp[x - 1][j - 1] + f[x - 1][i - 1]);
                }
            }
        }
        
        return dp[n][k];
    }
}

// f[i][j] == (i == j => f[i + 1][j - 1]) else (f[i + 1][j], f[i][j - 1]) + 1

// dp[i][j] s[:i] to j groups
// dp[i][j] = min(dp[i - k][j - 1] + s[k:i])
