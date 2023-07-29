class Solution {
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