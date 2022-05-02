class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        for (int j = 1; j <= n; j++)    {
            char c = p.charAt(j - 1);
            if (c == '*')   
                dp[0][j] = dp[0][j - 2];
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s.charAt(i - 1);
                char c2 = p.charAt(j - 1);
                if (c1 == c2 || c2 == '.')   
                    dp[i][j] = dp[i - 1][j - 1];
                else if (c2 == '*') {
                    dp[i][j] = dp[i][j - 2];
                    dp[i][j] |= ((c1 == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                }
            }
        }
        
        return dp[m][n];
    }
}
