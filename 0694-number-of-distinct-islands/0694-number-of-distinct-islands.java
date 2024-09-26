class Solution {
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<String> shapes = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    continue;
                Map<Integer, int[]> rowCnt = new HashMap<>();
                int xLo = m - 1;
                int xHi = 0;
                Deque<int[]> q = new LinkedList<>();
                q.offer(new int[] {i, j});
                StringBuilder sb = new StringBuilder();
                while (!q.isEmpty())    {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    if (grid[x][y] == 0)
                        continue;
                    grid[x][y] = 0;
                    sb.append('0' + (x - i));
                    sb.append(',');
                    sb.append('0' + (y - j));
                    sb.append('_');
                    for (int[] d : dir) {
                        int nx = x + d[0];
                        int ny = y + d[1];
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0)
                            continue;
                    
                        q.offer(new int[] {nx, ny});
                    }
                }
                shapes.add(sb.toString());
            }
        }
        
        return shapes.size();
    }
}