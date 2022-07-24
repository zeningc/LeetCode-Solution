class Solution {
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;
    char[][] grid;
    public int numIslands(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1')  {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
    
    
    void dfs(int x, int y)  {
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1)
            return;
        
        if (grid[x][y] != '1')
            return;
        
        grid[x][y] = '#';
        
        for (int[] d : dir) 
            dfs(x + d[0], y + d[1]);
        
        return;
    }
    
}
