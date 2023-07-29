class Solution {
    /*
    dp[i]: the number of distinct subsequence with (char)(i + 'a') as the last character
    at the current point s[i], we can attach s[i] to the end of every subsequence, and form a new sequence
    plus we can have '' + s[i]
    which makes dp[s[i] - 'a'] = sum(dp) + 1
    */
    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] dp = new int[26];
        int mod = (int)1e9 + 7;
        for (char c : s.toCharArray())  {
            int sum = 0;
            for (int i = 0; i < 26; i++)    {
                sum = (sum + dp[i]) % mod;
            }
            dp[c - 'a'] = (sum + 1) % mod;
        }
        
        int ret = 0;
        for (int i = 0; i < 26; i++)
            ret = (ret + dp[i]) % mod;
        
        return ret;
    }
}