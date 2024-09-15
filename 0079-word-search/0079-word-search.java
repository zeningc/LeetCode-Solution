class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (dfs(dir, board, new boolean[m][n], word, i, j, 0))
                    return true;
        return false;
    }
    
    boolean dfs(int[][] dir, char[][] board, boolean[][] vis, String word, int x, int y, int idx) {
        int m = board.length;
        int n = board[0].length;
        char expected = word.charAt(idx);
        char actual = board[x][y];
        if (expected != actual)
            return false;
        vis[x][y] = true;
        idx++;
        if (idx >= word.length())
            return true;
        boolean ans = false;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny])
                continue;
            ans |= dfs(dir, board, vis, word, nx, ny, idx);
        }
        vis[x][y] = false;
        return ans;
    }
}