class Solution {
    public int minimumPartition(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
            long num = 0;
            long mult = 1;
            for (int j = i; j >= Math.max(0, i - 9); j--)   {
                num = mult * (s.charAt(j) - '0') + num;
                if (num <= k)
                    dp[i] = Math.min(dp[i], (j >= 1 ? dp[j - 1] : 0) + 1);
                else
                    break;
                mult *= 10;
            }
        }
        return dp[n - 1] >= Integer.MAX_VALUE / 2 ? -1 : dp[n - 1];
    }
}


