class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        int n = robot.size();
        int m = factory.length;
        long[][][] dist = new long[m + 1][n + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = j; k < n; k++) {
                    sum += Math.abs(robot.get(k) - factory[i][0]);
                    dist[i][j][k] = sum;
                }
            }
        }
        
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        for (int i = 0; i <= m; i++)
            dp[i][0] = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= Math.min(j, factory[i - 1][1]); k++)   {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + dist[i - 1][j - k][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}