class Solution {
    public int knightDialer(int n) {
        int mod = (int)1e9 + 7;
        Map<Integer, List<Integer>> next = new HashMap<>();
        next.put(1, List.of(6, 8));
        next.put(2, List.of(7, 9));
        next.put(3, List.of(4, 8));
        next.put(4, List.of(0, 3, 9));
        next.put(6, List.of(0, 1, 7));
        next.put(7, List.of(2, 6));
        next.put(8, List.of(1, 3));
        next.put(9, List.of(2, 4));
        next.put(0, List.of(4, 6));

        int[][] dp = new int[n][10];
        for (int i = 0; i <= 9; i++)
            dp[0][i] = 1;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= 9; j++)    {
                if (j == 5)
                    continue;
                for (int x : next.get(j))
                    dp[i + 1][x] = (dp[i][j] + dp[i + 1][x]) % mod;
            }
        }

        int ans = 0;
        for (int j = 0; j <= 9; j++)
            ans = (ans + dp[n - 1][j]) % mod;
        
        return ans;
    }
}
