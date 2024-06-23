class Solution {
    int[][] grid;
    int m;
    int n;
    public int minimumSum(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                ans = Math.min(ans, calc(0, 0, m - 1, i) + calc(0, i + 1, m - 1, j) + calc(0, j + 1, m - 1, n - 1));
            }
        }
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                ans = Math.min(ans, calc(0, 0, i, n - 1) + calc(i + 1, 0, j, n - 1) + calc(j + 1, 0, m - 1, n - 1));
            }
        }
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                ans = Math.min(ans, calc(0, 0, i, j) + calc(0, j + 1, i, n - 1) + calc(i + 1, 0, m - 1, n - 1));
                ans = Math.min(ans, calc(i + 1, 0, m - 1, j) + calc(i + 1, j + 1, m - 1, n - 1) + calc(0, 0, i, n - 1));
            }
        }
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                ans = Math.min(ans, calc(0, i + 1, j, n - 1) + calc(j + 1, i + 1, m - 1, n - 1) + calc(0, 0, m - 1, i));
                ans = Math.min(ans, calc(0, 0, j, i) + calc(j + 1, 0, m - 1, i) + calc(0, i + 1, m - 1, n - 1));
            }
        }
        
        return ans;
    }
    
    int calc(int x1, int y1, int x2, int y2)    {
        int xLo = Integer.MAX_VALUE;
        int xHi = Integer.MIN_VALUE;
        int yLo = Integer.MAX_VALUE;
        int yHi = Integer.MIN_VALUE;
        
        for (int x = x1; x <= x2; x++)   {
            for (int y = y1; y <= y2; y++)    {
                if (grid[x][y] == 1)    {
                    xLo = Math.min(xLo, x);
                    xHi = Math.max(xHi, x);
                    yLo = Math.min(yLo, y);
                    yHi = Math.max(yHi, y);
                }
            }
        }
        
        return Math.abs(xHi - xLo + 1) * Math.abs(yHi - yLo + 1);
    }
}