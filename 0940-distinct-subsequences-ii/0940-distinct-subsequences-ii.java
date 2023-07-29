class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int mod = (int)1e9 + 7;
        int[][] lastSeen = new int[n][26];
        for (int i = 0; i < 26; i++)
            lastSeen[0][i] = -1;
        for (int i = 1; i < s.length(); i++)   {
            char c = s.charAt(i - 1);
            for (int j = 0; j < 26; j++)    {
                lastSeen[i][j] = lastSeen[i - 1][j];
            }
            lastSeen[i][c - 'a'] = i - 1;
        }
        int[] dp = new int[n];
        dp[0] = 2;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            dp[i] = (dp[i - 1] * 2) % mod;
            if (lastSeen[i][c - 'a'] != -1) {
                if (lastSeen[i][c - 'a'] == 0)  {
                    dp[i] = (dp[i] + mod - 1) % mod;
                }
                else    {
                    dp[i] = (dp[i] + mod - dp[lastSeen[i][c - 'a'] - 1]) % mod;
                }
            }
        }
        
        return (dp[n - 1] - 1 + mod) % mod;
    }
}

/*
lee
[] l e le
e  le  ee  lee
[] l   e
*/