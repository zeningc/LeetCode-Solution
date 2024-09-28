/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    public void cleanRoom(Robot robot) {
        boolean[][] visited = new boolean[500][500];
        int[][] dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        dfs(dir, robot, visited, 100, 200, 0);
    }
    
    void dfs(int[][] dir, Robot robot, boolean[][] visited, int x, int y, int d)    {
        visited[x][y] = true;
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[(i + d) % 4][0];
            int ny = y + dir[(i + d) % 4][1];
            if (visited[nx][ny] || !robot.move())   {
                robot.turnLeft();
                continue;
            }
            dfs(dir, robot, visited, nx, ny, (i + d) % 4);
            robot.turnLeft();
        }
        
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }
}