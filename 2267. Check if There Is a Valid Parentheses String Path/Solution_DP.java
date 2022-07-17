class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;
        
        boolean[][][] dp = new boolean[m][n][m + n + 1];
        dp[0][0][1] = true;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < (m + n + 1) / 2; k++) {
                    if (i == 0 && j == 0)
                        continue;
                    char c = grid[i][j];
                    if (c == '(' && k > 0)   {
                        dp[i][j][k] = (i > 0 && dp[i - 1][j][k - 1]) || (j > 0 && dp[i][j - 1][k - 1]);
                    }
                    
                    if (c == ')')   {
                        dp[i][j][k] = (i > 0 && dp[i - 1][j][k + 1]) || (j > 0 && dp[i][j - 1][k + 1]);
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}
