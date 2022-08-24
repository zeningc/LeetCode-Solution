class Solution {
    public String minWindow(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        for (int i = 0; i <= m; i++)
            dp[i][0] = 0;
        
        for (int i = 1; i <= m; i++)    {
            for (int j = 1; j <= n; j++)    {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
            }
        }
        
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i <= m; i++)
            minLen = Math.min(minLen, dp[i][n]);
        
        if (minLen > m)
            return "";
        
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= m; i++)    {
            if (dp[i][n] == minLen) {
                return s1.substring(i - minLen, i);
            }
        }
        
        return "";
    }
}
