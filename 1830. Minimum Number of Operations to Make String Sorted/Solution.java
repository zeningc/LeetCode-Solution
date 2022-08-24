class Solution {
    int[] freq;
    long[] dp;
    int mod;
    public int makeStringSorted(String s) {
        freq = new int[26];
        dp = new long[3001];
        mod = (int)1e9 + 7;
        int n = s.length();
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        dp[0] = 1;
        long ret = 0;
        for (int i = 1; i <= 3000; i++)
            dp[i] = (dp[i - 1] * i) % mod;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int cnt = 0;
            for (int k = 0; k < c - 'a'; k++)
                cnt += freq[k];
            
            long ans = dp[n - i - 1] % mod;
            
            for (int k = 0; k < 26; k++)    {
                ans = ans * inv(dp[freq[k]]) % mod;
            }
            
            ret = (ret + cnt * ans) % mod;
            freq[c - 'a']--;
        }
        
        return (int)(ret % mod);
    }
    
    long inv(long x) 
    {
        long s = 1;
        for (; x > 1; x = mod % x)
          s = s * (mod - mod / x) % mod;
        return s;
    }
}
