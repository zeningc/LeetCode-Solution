class Solution {
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] ranges = new int[n][n];
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + houses[i - 1];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int mid = i + (j - i) / 2;
                ranges[i][j] = houses[mid] * (mid - i) - (presum[mid] - presum[i]) + (presum[j + 1] - presum[mid + 1]) - houses[mid] * (j - mid);
            }
        
        int[][] dp = new int[n][k];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                }
                for (int x = 0; x < i; x++) {
                    if (j == 0)
                        dp[i][j] = ranges[0][i];
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[x][j - 1] + ranges[x + 1][i]);
                }
            }
        }
        
        return dp[n - 1][k - 1];
    }
}