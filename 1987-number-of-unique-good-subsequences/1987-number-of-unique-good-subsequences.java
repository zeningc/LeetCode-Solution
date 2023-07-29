class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int i = 0;
        int n = binary.length();
        while (i < n && binary.charAt(i) == '0')
            i++;
        if (i == n)
            return 1;
        int[] last = new int[2];
        int mod = (int)1e9 + 7;
        long[] dp = new long[n + 1];
        dp[++i] = 1;
        i++;
        for (; i <= n; i++)    {
            char c = binary.charAt(i - 1);
            int j = last[c - '0'];
            dp[i] = (dp[i - 1] * 2 + mod - (j >= 1 ? dp[j - 1] : 0)) % mod;
            last[c - '0'] = i;
        }
        return (int)(dp[binary.length()] + (binary.contains("0") ? 1 : 0) % mod);
    }
}