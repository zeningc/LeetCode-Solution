class Solution {
    int MOD = 1000000007;
    public int makeStringSorted(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        
        long[] factorial = new long[s.length() + 1];
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++)
            factorial[i] = factorial[i - 1] * i % MOD;
        
        long ans = 0L;
        for (int i = 0; i < s.length(); i++)    {
            char c = s.charAt(i);
            long lowerBitCnt = 0;
            for (int j = 0; j < c - 'a'; j++)
                lowerBitCnt += freq[j];
            long permCnt =  factorial[s.length() - i - 1] % MOD;
            for (int j = 0; j < 26; j++)
                permCnt = permCnt * inv(factorial[freq[j]]) % MOD;
            ans = (ans + permCnt * lowerBitCnt) % MOD;
            freq[c - 'a']--;
        }
        
        return (int)(ans % MOD);
    }
    
    long inv(long x) 
    {
        long s = 1;
        for (; x > 1; x = MOD % x)
          s = s * (MOD - MOD / x) % MOD;
        return s;
    }
}