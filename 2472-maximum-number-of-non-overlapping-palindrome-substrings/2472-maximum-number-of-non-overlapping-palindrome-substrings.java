class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < Math.min(i + k + 1, n); j++) {
                if (i == j)
                    dp[i][j] = true;
                else if (j == i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        
        int ans = 0;
        
        int i = 0;
        while (i <= n - k)   {
            ans += dp[i][i + k - 1] || dp[i][i + k] ? 1 : 0;
            if (dp[i][i + k - 1])
                i += k;
            else if (dp[i][i + k])
                i += k + 1;
            else
                i++;
        }
        
        return ans;
    }
}