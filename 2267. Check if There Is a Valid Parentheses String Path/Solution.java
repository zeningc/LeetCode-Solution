class Solution {
    boolean ans = false;
    char[][] grid;
    int m;
    int n;
    boolean[][][] dp;
    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        dp = new boolean[m][n][m + n + 1];
        dfs(0, 0, 0);
        return ans;
    }
    
    void dfs(int i, int j, int leftCnt) {
        if (ans)
            return;
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1)
            return;
        if (dp[i][j][leftCnt])
            return;
        
        
        char c = grid[i][j];
        int leftCntCpy = leftCnt;
        if (c == '(')   {
            leftCnt++;
        }
        else if (c == ')')  {
            if (leftCnt <= 0)   {
                dp[i][j][leftCntCpy] = true;
                return;
            }
            leftCnt--;
        }
        
        if (i == m - 1 && j == n - 1)   {
            if (leftCnt == 0)
                ans = true;
            return;
        }
        
        dfs(i + 1, j, leftCnt);
        dfs(i, j + 1, leftCnt);
        
        dp[i][j][leftCntCpy] = true;
        return;
    }
}
