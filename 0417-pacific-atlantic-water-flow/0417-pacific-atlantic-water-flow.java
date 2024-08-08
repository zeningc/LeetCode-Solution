class Solution {
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] pacific = flow(heights, m, n, 0, 0);
        boolean[][] atlantic = flow(heights, m, n, m - 1, n - 1);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (pacific[i][j] && atlantic[i][j])
                    ans.add(List.of(i, j));
        return ans;
    }
    
    boolean[][] flow(int[][] heights, int m, int n, int initX, int initY)    {
        boolean[][] vis = new boolean[m][n];
        Deque<Pair<Integer, Integer>> q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            q.offer(new Pair<Integer, Integer>(i, initY));
        for (int i = 0; i < n; i++)
            q.offer(new Pair<Integer, Integer>(initX, i));
        
        while (!q.isEmpty())    {
            Pair<Integer, Integer> u = q.poll();
            int x = u.getKey();
            int y = u.getValue();
            if (vis[x][y])
                continue;
            vis[x][y] = true;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || vis[nx][ny] || heights[nx][ny] < heights[x][y])
                    continue;
                q.offer(new Pair<Integer, Integer>(nx, ny));
            }
        }
        
        return vis;
    }
}