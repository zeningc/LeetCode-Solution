class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] direction = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] vis = new boolean[m][n];
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, start[0], start[1]});
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int currR = curr[1];
            int currC = curr[2];
            int currStep = curr[0];
            if (vis[currR][currC])
                continue;
            vis[currR][currC] = true;
            if (currR == destination[0] && currC == destination[1])
                return currStep;
            for (int i = 0; i < 4; i++) {
                int nextR = currR;
                int nextC = currC;
                int step = 0;
                int tR = nextR + direction[i][0];
                int tC = nextC + direction[i][1];
                while (tR >= 0 && tR < m && tC >= 0 && tC < n && maze[tR][tC] == 0)  {
                    nextR += direction[i][0];
                    nextC += direction[i][1];
                    step++;
                    tR = nextR + direction[i][0];
                    tC = nextC + direction[i][1];
                }
                if (nextR == currR && nextC == currC)
                    continue;
                if (vis[nextR][nextC])
                    continue;
                pq.offer(new int[] {currStep + step, nextR, nextC});
            }
        }
        
        return -1;
    }
}