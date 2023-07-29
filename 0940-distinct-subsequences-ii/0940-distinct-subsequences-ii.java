class Solution {
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long dp = 0;
        int mod = (int)1e9 + 7;
        for (char c : s.toCharArray())  {
            long preDp = dp;
            dp = (preDp * 2 + 1 - last[c - 'a'] + mod) % mod;
            last[c - 'a'] = preDp + 1 % mod;
        }
        
        return (int)(dp);
    }
}