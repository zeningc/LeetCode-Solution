class Solution {
    int[][] board;
    int m;
    int n;
    public int[][] candyCrush(int[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        boolean flag = true;
        while (flag)    {
            flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (canCrash(i, j))
                        flag = true;
                }
            }
            fall();
        }
        return board;
    }
    
    void fall()  {
        for (int j = 0; j < n; j++) {
            int p = m - 1;
            for (int i = m - 1; i > -1; i--)    {
                if (board[i][j] > 0)
                    board[p--][j] = board[i][j];
            }
            while (p >= 0)   {
                board[p--][j] = 0;
            }
        }
    }
    
    boolean canCrash(int x, int y)  {
        if (x < 0 || y < 0 || x > m - 1 || y > n - 1 || board[x][y] == 0)
            return false;
        boolean ans = false;
        if (x > 0 && x < m - 1 && Math.abs(board[x - 1][y]) == Math.abs(board[x][y]) && Math.abs(board[x][y]) == Math.abs(board[x + 1][y])) {
            board[x - 1][y] = -Math.abs(board[x - 1][y]);
            board[x][y] = -Math.abs(board[x][y]);
            board[x + 1][y] = -Math.abs(board[x + 1][y]);
            ans = true;
        }
        
        if (y > 0 && y < n - 1 && Math.abs(board[x][y - 1]) == Math.abs(board[x][y]) && Math.abs(board[x][y]) == Math.abs(board[x][y + 1])) {
            board[x][y - 1] = -Math.abs(board[x][y - 1]);
            board[x][y] = -Math.abs(board[x][y]);
            board[x][y + 1] = -Math.abs(board[x][y + 1]);
            ans = true;
        }
        
        return ans;
    }
    
    
}