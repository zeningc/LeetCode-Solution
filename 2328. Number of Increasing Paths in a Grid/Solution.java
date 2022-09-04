class Solution {
    int m;
    int n;
    Integer[][] mem;
    int[][] grid;
    int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int mod = (int)1e9 + 7;
    public int countPaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        mem = new Integer[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
            }
        }
        
        long ans = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + mem[i][j]) % mod;
            }
        }
        
        return (int)ans;
    }
    
    int dfs(int x, int y)   {
        if (mem[x][y] != null)
            return mem[x][y];
        mem[x][y] = 1;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || grid[nx][ny] <= grid[x][y])
                continue;
            
            mem[x][y] = (mem[x][y] + dfs(nx, ny)) % mod;
        }
        return mem[x][y];
    }
}
