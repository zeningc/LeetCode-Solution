class Solution {
    int m;
    int n;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    char[][] grid;
    public int minPushBox(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int xPlayer = -1;
        int yPlayer = -1;
        int xBox = -1;
        int yBox = -1;
        int xTarget = -1;
        int yTarget = -1;
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S')  {
                    xPlayer = i;
                    yPlayer = j;
                }
                else if (grid[i][j] == 'B') {
                    xBox = i;
                    yBox = j;
                }
                else if (grid[i][j] == 'T') {
                    xTarget = i;
                    yTarget = j;
                }
            }
        }
        
        Deque<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][4];
        q.offer(new int[] {0, -1, xPlayer, yPlayer, xBox, yBox});
        
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int px = curr[2];
            int py = curr[3];
            int bx = curr[4];
            int by = curr[5];
            int cnt = curr[0];
            int currDir = curr[1];
            if (currDir != -1 && visited[bx][by][currDir])
                continue;
            if (currDir != -1)
                visited[bx][by][currDir] = true;
            
            if (bx == xTarget && by == yTarget)
                return cnt;
            
            for (int i = 0; i < 4; i++) {
                int dx = dir[i][0];
                int dy = dir[i][1];
                int nx = dx + bx;
                int ny = dy + by;
                if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1)
                    continue;
                if (visited[nx][ny][i])
                    continue;
                if (grid[nx][ny] == '#')
                    continue;
                if (canReach(bx - dx, by - dy, px, py, bx, by))
                    q.offer(new int[] {cnt + 1, i, bx, by, nx, ny});
                
            }
            
        }
        
        
        return -1;
    }
    
    boolean canReach(int x, int y, int px, int py, int bx, int by)  {
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1)
            return false;
        if (x == bx && y == by)
            return false;
        if (grid[x][y] == '#')
            return false;
        Deque<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(new int[] {px, py});
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];
            if (visited[currX][currY])
                continue;
            visited[currX][currY] = true;
            if (currX == x && currY == y)
                return true;
            
            for (int[] d : dir) {
                int dx = d[0];
                int dy = d[1];
                int newX = dx + currX;
                int newY = dy + currY;
                if (newX < 0 || newX > m - 1 || newY < 0 || newY > n - 1)
                    continue;
                if (grid[newX][newY] == '#')
                    continue;
                if (visited[newX][newY])
                    continue;
                if (newX == bx && newY == by)
                    continue;
                q.offer(new int[] {newX, newY});
            }
        
        }
        
        return false;
    }
}
