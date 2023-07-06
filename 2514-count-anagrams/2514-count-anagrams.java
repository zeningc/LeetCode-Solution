class Solution {
    int MOD = 1000000007;
    public int countAnagrams(String s) {
        long ans = 1L;
        String[] split = s.split(" ");
        
        long[] inv = new long[100001];
        inv[1] = 1;
        for (int i = 2; i < 100001; i++)
            inv[i] = (MOD - MOD / i) * inv[MOD % i] % MOD;
        
        for (String word : split)   {
            int[] freq = new int[26];
            for (char c : word.toCharArray())   {
                freq[c - 'a']++;
            }
            for (int i = 1; i <= word.length(); i++)
                ans = ans * i % MOD;
            for (int i = 0; i < 26; i++)    {
                for (int j = 1; j <= freq[i]; j++)   {
                    ans = ans * inv[j] % MOD;
                }
            }
        }
        
        return (int)(ans % MOD);
    }
}