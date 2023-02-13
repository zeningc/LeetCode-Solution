class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp1 = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++)    {
            for (int j = 1; j <= n; j++)    {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp1[i][j] = dp1[i - 1][j - 1] + 1;
                }
            }
        }
        int[][] dp2 = new int[m + 2][n + 2];
        for (int i = m; i >= 1; i--)    {
            for (int j = n; j >= 1; j--)    {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp2[i][j] = dp2[i + 1][j + 1] + 1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 1; i <= m; i++)    {
            for (int j = 1; j <= n; j++)    {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    ans += (dp1[i - 1][j - 1] + 1) * (dp2[i + 1][j + 1] + 1);
                }
            }
        }
        
        return ans;
    }
}