class Solution {
    boolean[][] visited;
    char[][] board;
    String word;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        this.word = word;
        this.board = board;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (dfs(i, j, 0))
                    return true;
        
        return false;
    }
    
    boolean dfs(int x, int y, int idx)   {
        if (board[x][y] != word.charAt(idx))
            return false;
        if (idx == word.length() - 1)
            return true;
        visited[x][y] = true;
        boolean ans = false;
        for (int i = 0; i < 4 && !ans; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                continue;
            if (visited[nx][ny])
                continue;
            
            ans |= dfs(nx, ny, idx + 1);
        }
        visited[x][y] = false;
        
        return ans;
    }
}
