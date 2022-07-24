class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            dp[i][i] = true;
        String ans = String.valueOf(s.charAt(0));
        for (int l = 2; l <= n; l++)    {
            for (int i = 1; i <= n; i++)    {
                int j = i + l - 1;
                if (j <= n && s.charAt(i - 1) == s.charAt(j - 1)) {
                    if (i + 1 == j)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 1][j - 1];
                    
                    if (dp[i][j] && j - i + 1 > ans.length())   {
                        ans = s.substring(i - 1, j);
                    }
                }
                
            }
            
        }
        
        return ans;
    }
}
