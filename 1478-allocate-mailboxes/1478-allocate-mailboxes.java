class Solution {
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + houses[i];
        int[][] range = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int mid = (i + j) / 2;
                if ((j - i + 1) % 2 == 0)
                    range[i + 1][j + 1] = presum[j + 1] - 2 * presum[mid + 1] + presum[i];
                else
                    range[i + 1][j + 1] = presum[j + 1] - presum[mid + 1] - presum[mid] + presum[i];
            }
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++)    {
                for (int x = 0; x < i; x++) {
                    dp[i][j] = Math.min(dp[i][j], dp[x][j - 1] + range[x + 1][i]);
                }
            }
        }
        return dp[n][k];
    }
}