class Solution {
    public int swimInWater(int[][] grid) {
        int[][] direction = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];
        pq.offer(new int[] {0, 0, grid[0][0]});
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int val = curr[2];
            
            if (x == m - 1 && y == n - 1)    {
                return val;
            }
            
            for (int i = 0; i < 4; i++) {
                int newX = x + direction[i][0];
                int newY = y + direction[i][1];
                if (newX < 0 || newX > m - 1 || newY < 0 || newY > n - 1 || visited[newX][newY])    {
                    continue;
                }
                
                visited[newX][newY] = true;
                
                pq.offer(new int[] {newX, newY, Math.max(val, grid[newX][newY])});
            }
        }
        
        return -1;
    }
}