class Solution {
    int[][] maze;
    int[][] direction = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    String[] dirStr = new String[] {"r", "l", "d", "u"};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.maze = maze;
        int m = maze.length;
        int n = maze[0].length;
        boolean[][][] vis = new boolean[m][n][4];
        Queue<Path> pq = new PriorityQueue<>();
        for (int i = 0; i < 4; i++)
            if (!hit(ball[0], ball[1], i))
                pq.offer(new Path(1, ball[0] + direction[i][0], ball[1] + direction[i][1], i, dirStr[i]));
        
        while (!pq.isEmpty())   {
            Path curr = pq.poll();
            int currR = curr.r;
            int currC = curr.c;
            int currStep = curr.step;
            int currDir = curr.dir;
            String currPath = curr.pathStr;
            if (vis[currR][currC][currDir])
                continue;
            vis[currR][currC][currDir] = true;
            if (currR == hole[0] && currC == hole[1])
                return currPath;
            if (!hit(currR, currC, currDir))    {
                pq.offer(new Path(currStep + 1, currR + direction[currDir][0], currC + direction[currDir][1], currDir, currPath));
                continue;
            }
            for (int i = 0; i < 4; i++) {
                if (hit(currR, currC, i))
                    continue;
                int nextR = currR + direction[i][0];
                int nextC = currC + direction[i][1];
                if (vis[nextR][nextC][i])
                    continue;
                String nextPath = currPath + dirStr[i];
                pq.offer(new Path(currStep + 1, nextR, nextC, i, nextPath));
            }
        }
        
        return "impossible";
    }
    
    boolean hit(int r, int c, int dir)   {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length)
            return true;
        int nextR, nextC;
        if (dir != -1)  {
            nextR = r + direction[dir][0];
            nextC = c + direction[dir][1];
            return nextR < 0 || nextR >= maze.length || nextC < 0 || nextC >= maze[0].length || maze[nextR][nextC] == 1;
        }
        for (int i = 0; i < 4; i++) {
            nextR = r + direction[i][0];
            nextC = c + direction[i][1];
            if (nextR < 0 || nextR >= maze.length || nextC < 0 || nextC >= maze[0].length || maze[nextR][nextC] == 1)
                return true;
        }
        return false;
    }
}

class Path implements Comparable<Path>  {
    int r;
    int c;
    String pathStr;
    int step;
    int dir;
    
    public Path(int step, int r, int c, int dir, String pathStr)    {
        this.step = step;
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.pathStr = pathStr;
    }
    
    @Override
    public int compareTo(Path p)    {
        if (p.step == this.step)
            return this.pathStr.compareTo(p.pathStr);
        return this.step - p.step;
    }
    
    
}