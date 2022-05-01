class Solution {
    boolean[][] visited = new boolean[201][401];
    int[][] direction = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    Robot robot;
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(100, 200, 0);
    }
    
    void dfs(int x, int y, int d)  {
        visited[x][y] = true;
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[(i + d) % 4][0];
            int newY = y + direction[(i + d) % 4][1];
            if (visited[newX][newY] || !robot.move())   {
                robot.turnLeft();
                continue;
            }
            if (!visited[newX][newY])
                dfs(newX, newY, (i + d) % 4);
            robot.turnLeft();
        }
        
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }
}
