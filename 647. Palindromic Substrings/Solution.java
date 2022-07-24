class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        int ans = n;
        for (int i = n; i > 0; i--) {
            for (int j = i + 1; j <= n; j++)    {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    if (i + 1 == j)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 1][j - 1];
                }
                
                if (dp[i][j])
                    ans++;
            }
        }
        
        return ans;
    }
}
