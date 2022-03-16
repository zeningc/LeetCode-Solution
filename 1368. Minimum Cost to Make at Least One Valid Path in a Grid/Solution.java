class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dict = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        pq.offer(new int[] {0, 0, 0});
        boolean[][] vis = new boolean[m][n];
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int currCost = curr[0];
            int r = curr[1];
            int c = curr[2];
            if (vis[r][c])
                continue;
            if (r == m - 1 && c == n - 1)
                return currCost;
            vis[r][c] = true;
            for (int[] d : dict)    {
                int nextR = r + d[0];
                int nextC = c + d[1];
                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n)
                    continue;
                if (vis[nextR][nextC])
                    continue;
                int nextCost;
                if (grid[r][c] == 1 && d[1] == 1 || grid[r][c] == 2 && d[1] == -1 || grid[r][c] == 3 && d[0] == 1 || grid[r][c] == 4 && d[0] == -1)
                    nextCost = 0;
                else
                    nextCost = 1;
                pq.offer(new int[] {currCost + nextCost, nextR, nextC});
            }
        }
        
        return -1;
    }
}