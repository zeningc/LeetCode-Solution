class Solution {
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] prefixRow2 = new int[m + 1][n + 1];
        int[][] prefixCol2 = new int[m + 1][n + 1];
        int[][] prefixRow5 = new int[m + 1][n + 1];
        int[][] prefixCol5 = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)    {
            Arrays.fill(prefixRow2[i], 0);
            Arrays.fill(prefixCol2[i], 0);
            Arrays.fill(prefixRow5[i], 0);
            Arrays.fill(prefixCol5[i], 0);
        }
        So
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixRow2[i][j + 1] = prefixRow2[i][j] + cnt2(grid[i][j]);
                prefixCol2[i + 1][j] = prefixCol2[i][j] + cnt2(grid[i][j]);
                prefixRow5[i][j + 1] = prefixRow5[i][j] + cnt5(grid[i][j]);
                prefixCol5[i + 1][j] = prefixCol5[i][j] + cnt5(grid[i][j]);
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) 
            ans = Math.max(ans, Math.min(prefixRow2[i][n], prefixRow5[i][n]));
        for (int j = 0; j < n; j++)
            ans = Math.max(ans, Math.min(prefixCol2[m][j], prefixCol5[m][j]));
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int leftUp2 = prefixRow2[i][j] + prefixCol2[i + 1][j];
                int rightUp2 = prefixRow2[i][n] - prefixRow2[i][j] + prefixCol2[i][j];
                int leftDown2 = prefixRow2[i][j] + prefixCol2[m][j] - prefixCol2[i][j];
                int rightDown2 = prefixRow2[i][n] - prefixRow2[i][j] + prefixCol2[m][j] - prefixCol2[i + 1][j];
                
                int leftUp5 = prefixRow5[i][j] + prefixCol5[i + 1][j];
                int rightUp5 = prefixRow5[i][n] - prefixRow5[i][j] + prefixCol5[i][j];
                int leftDown5 = prefixRow5[i][j] + prefixCol5[m][j] - prefixCol5[i][j];
                int rightDown5 = prefixRow5[i][n] - prefixRow5[i][j] + prefixCol5[m][j] - prefixCol5[i + 1][j];
                
                ans = Math.max(ans, Math.min(leftUp2, leftUp5));
                ans = Math.max(ans, Math.min(rightUp2, rightUp5));
                ans = Math.max(ans, Math.min(leftDown2, leftDown5));
                ans = Math.max(ans, Math.min(rightDown2, rightDown5));
            }
        }
        
        return ans;
    }
    
    int cnt2(int num)   {
        int cnt = 0;
        while (num != 0 && num % 2 == 0)    {
            cnt++;
            num /= 2;
        }
        return cnt;
    }
    
    int cnt5(int num)   {
        int cnt = 0;
        while (num != 0 && num % 5 == 0)    {
            cnt++;
            num /= 5;
        }
        return cnt;
    }
}