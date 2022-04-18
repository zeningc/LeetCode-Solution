class Solution {
    int[][] dp;
    int threshold;
    int m;
    int n;
    public int maxSideLength(int[][] mat, int threshold) {
        m = mat.length;
        n = mat[0].length;
        this.threshold = threshold;
        dp = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] - dp[i][j] + mat[i][j];
        
        int[][] d = dp;
        int left = 1;
        int right = Math.min(n, m);
        
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (check(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        
        return right;
    }
    
    
    boolean check(int len)  {
        int min = Integer.MAX_VALUE;
        for (int i = len; i <= m; i++)
            for (int j = len; j <= n; j++)
                min = Math.min(dp[i][j] - dp[i - len][j] - dp[i][j - len] + dp[i - len][j - len], min);
        
        return min > threshold;
    }
}
