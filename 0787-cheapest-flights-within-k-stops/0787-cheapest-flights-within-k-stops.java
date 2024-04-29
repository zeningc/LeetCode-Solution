class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = dp.clone();
            for (int[] f : flights) {
                next[f[1]] = Math.min(next[f[1]], dp[f[0]] + f[2]);
            }
            dp = next;
        }
        
        return dp[dst] == Integer.MAX_VALUE / 2 ? -1 : dp[dst];
    }
}