class Solution {
    public int minimumArea(int[][] grid) {
        int xLo = Integer.MAX_VALUE;
        int xHi = Integer.MIN_VALUE;
        int yLo = Integer.MAX_VALUE;
        int yHi = Integer.MIN_VALUE;
        
        for (int x = 0; x < grid.length; x++)   {
            for (int y = 0; y < grid[0].length; y++)    {
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