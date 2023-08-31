class Solution {
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        for (int i = 1; i <= n; i++)    {
            int start = events[i - 1][0];
            int end = events[i - 1][1];
            int value = events[i - 1][2];
            int lo = 1;
            int hi = n;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (events[mid - 1][1] >= start)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            for (int j = 1; j <= k; j++)    {
                dp[i][j] = Math.max(dp[i - 1][j], dp[hi][j - 1] + value);
            }
        }
        
        return dp[n][k];
    }
}