class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] po = new boolean[m][n];
        boolean[][] ao = new boolean[m][n];
        
        boolean[][] visited = new boolean[m][n];
        
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            q.offer(new int[] {i, 0});
        for (int i = 0; i < n; i++)
            q.offer(new int[] {0, i});
        
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            
            if (visited[x][y])
                continue;
            visited[x][y] = true;
            
            po[x][y] = true;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (heights[nx][ny] < heights[x][y])
                    continue;
                if (visited[nx][ny])
                    continue;
                q.offer(new int[] {nx, ny});
            }
        }
        visited = new boolean[m][n];
        
        q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            q.offer(new int[] {i, n - 1});
        for (int i = 0; i < n; i++)
            q.offer(new int[] {m - 1, i});
        
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            
            if (visited[x][y])
                continue;
            visited[x][y] = true;
            
            ao[x][y] = true;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                    continue;
                if (heights[nx][ny] < heights[x][y])
                    continue;
                if (visited[nx][ny])
                    continue;
                q.offer(new int[] {nx, ny});
            }
        }
        
        List<List<Integer>> ans = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (po[i][j] && ao[i][j])
                    ans.add(List.of(i, j));
            }
        }
        
        return ans;
    }
}
