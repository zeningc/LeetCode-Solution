class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int mod = (int)1e9 + 7;
        int[] last = new int[26];
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++)    {
            char c = s.charAt(i - 1);
            dp[i] = (dp[i - 1] * 2) % mod;
            if (last[c - 'a'] >= 1)
                dp[i] = (dp[i] - dp[last[c - 'a'] - 1] + mod) % mod;
            last[c - 'a'] = i;
        }
        return (dp[n] - 1 + mod) % mod;
    }
}

/*
lee
[] l e le
e  le  ee  lee
[] l   e   
*/