class Solution {
    int endX;
    int endY;
    int[][] grid;
    int m;
    int n;
    int[] rowDir = new int[] {1, 0, -1, 0};
    int[] colDir = new int[] {0, -1, 0, 1};
    int target;
    Integer[][][] memo;
    public int uniquePathsIII(int[][] grid) {
        int startX = 0;
        int startY = 0;
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] %2 == 0)    {
                    target |= code(i, j);
                }
                if (grid[i][j] == 1)   {
                    startX = i;
                    startY = j;
                }
                else if(grid[i][j] == 2)    {
                    endX = i;
                    endY = j;
                }
            }
        }
        memo = new Integer[m][n][1 << m*n];
        return dfs(startX, startY, target);
        
    }
    
    private int code(int x, int y)  {
        return 1 << (x * n + y);
    }
    
    private Integer dfs(int x, int y, int todo)  {
        if (memo[x][y][todo] != null)   {
            return memo[x][y][todo];
        }
        
        int ans = 0;
        if (x == endX && y == endY) {
            if (todo == 0)    {
                ans++;
            }
            return ans;
        }
        
        for (int i = 0; i < 4; i++) {
            int newX = x + rowDir[i];
            int newY = y + colDir[i];
            if (newX < 0 || newX > m - 1 || newY < 0 || newY > n - 1 || (todo & code(newX, newY)) == 0)   {
                continue;
            }
            ans += dfs(newX, newY, todo ^ code(newX, newY));
        }
        
       
        memo[x][y][todo] = ans;
        return ans;
        
    }
}