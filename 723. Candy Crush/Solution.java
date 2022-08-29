class Solution {
    int m;
    int n;
    int[][] board;
    boolean[][] visited;
    boolean[][] crashed;
    public int[][] candyCrush(int[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        while (true)    {
            crashed = new boolean[m][n];
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != 0 && canCrash(i, j)) {
                        visited = new boolean[m][n];
                        flag = true;
                    }
                }
            }
            if (!flag)
                break;
            crash();
            fall();
        }
        
        return board;
    }
    
    boolean canCrash(int x, int y)  {
        int val = board[x][y];
        boolean ans = false;
        if (isCandy(x + 1, y, val) && isCandy(x + 2, y, val))   {
            ans = true;
            crashed[x][y] = true;
            crashed[x + 1][y] = true;
            crashed[x + 2][y] = true;
        }
        if (isCandy(x, y + 1, val) && isCandy(x, y + 2, val))   {
            ans = true;
            crashed[x][y] = true;
            crashed[x][y + 1] = true;
            crashed[x][y + 2] = true;
        }
        
        return ans;
    }
    
    void crash()    {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (crashed[i][j])
                    board[i][j] = 0;
            }
        }
    }
    
    boolean isCandy(int x, int y, int val)  {
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1)
            return false;
        return board[x][y] == val;
    }
    
    void fall() {
        int[][] b = board;
        for (int j = 0; j < n; j++) {
            List<Integer> nonEmpty = new ArrayList<>(50);
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] != 0)
                    nonEmpty.add(board[i][j]);
                board[i][j] = 0;
            }
            
            for (int i = m - 1, x = 0; x < nonEmpty.size() && i >= 0; i--)
                board[i][j] = nonEmpty.get(x++);
        }
    }
}
