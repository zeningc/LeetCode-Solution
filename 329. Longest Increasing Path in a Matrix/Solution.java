class Solution {
    Integer[][] mem;
    int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m;
    int n;
    int[][] matrix;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        mem = new Integer[m][n];
        int ans = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
                ans = Math.max(ans, mem[i][j]);
            }
        }
        
        return ans;
    }
    
    int dfs(int x, int y)   {
        if (mem[x][y] != null)
            return mem[x][y];
        int maxLen = 0;
        
        for (int[] d : dir)   {
            int nx = d[0] + x;
            int ny = d[1] + y;
            
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || matrix[x][y] >= matrix[nx][ny])
                continue;
            
            maxLen = Math.max(maxLen, dfs(nx, ny));
        }
        
        mem[x][y] = 1 + maxLen;
        
        return mem[x][y];
    }
}
class Solution {
    Integer[][] mem;
    int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m;
    int n;
    int[][] matrix;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        mem = new Integer[m][n];
        int ans = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
                ans = Math.max(ans, mem[i][j]);
            }
        }
        
        return ans;
    }
    
    int dfs(int x, int y)   {
        if (mem[x][y] != null)
            return mem[x][y];
        int maxLen = 0;
        
        for (int[] d : dir)   {
            int nx = d[0] + x;
            int ny = d[1] + y;
            
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || matrix[x][y] >= matrix[nx][ny])
                continue;
            
            maxLen = Math.max(maxLen, dfs(nx, ny));
        }
        
        mem[x][y] = 1 + maxLen;
        
        return mem[x][y];
    }
}
