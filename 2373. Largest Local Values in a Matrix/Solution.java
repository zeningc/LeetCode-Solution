class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int maxVal = 0;
                for (int x = i; x <= i + 2; x++)    {
                    for (int y = j; y <= j + 2; y++)    {
                        maxVal = Math.max(maxVal, grid[x][y]);
                    }
                }
                ans[i][j] = maxVal;
            }
        }
        
        return ans;
    }
}
