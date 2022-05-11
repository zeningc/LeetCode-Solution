class Solution {
    public int countTexts(String pressedKeys) {
        int[] cnt = new int[10];
        Arrays.fill(cnt, 3);
        cnt[7] = 4;
        cnt[9] = 4;
        int n = pressedKeys.length();
        int mod = 1000000007;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            char c = pressedKeys.charAt(i - 1);
            for (int j = 1; j <= cnt[c - '0']; j++)   {
                if (i - j < 0)
                    break;
                dp[i] = (dp[i] + dp[i - j]) % mod;
                if (i - j - 1 >= 0 && pressedKeys.charAt(i - j - 1) != c)
                    break;
            }
        }
        
        return (int)dp[n];
    }
}
