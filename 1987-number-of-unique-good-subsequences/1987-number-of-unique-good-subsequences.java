class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int i = 0;
        int n = binary.length();
        while (i < n && binary.charAt(i) == '0')
            i++;
        if (i == n)
            return 1;
        long[] last = new long[2];
        int mod = (int)1e9 + 7;
        long dp = 0;
        for (; i < n; i++)    {
            char c = binary.charAt(i);
            long preDp = dp;
            if (preDp == 0)
                dp = 1;
            else
                dp = (preDp * 2 + mod - last[c - '0']) % mod;
            last[c - '0'] = preDp;
        }
        return (int)((dp + mod + (binary.contains("0") ? 1 : 0)) % mod);
    }
}