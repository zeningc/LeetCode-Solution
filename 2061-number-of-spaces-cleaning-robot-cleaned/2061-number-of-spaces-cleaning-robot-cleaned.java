class Solution {
    int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};;
    public int numberOfCleanRooms(int[][] room) {
        int m = room.length;
        int n = room[0].length;
        
        dfs(new HashSet<>(), room, 0, 0, 0);
        
        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (room[i][j] == -1)
                    ans++;
        
        return ans;
    }
    
    void dfs(Set<String> vis, int[][] room, int x, int y, int d)   {
        vis.add(x + "_" + y + "_" + d);
        room[x][y] = -1;
        for (int i = d; i < d + 4; i++) {
            int nx = x + dirs[i % 4][0];
            int ny = y + dirs[i % 4][1];
            
            if (nx < 0 || nx > room.length - 1 || ny < 0 || ny > room[0].length - 1 || room[nx][ny] == 1)
                continue;
            
            String nxtKey = nx + "_" + ny + "_" + (i % 4);
            if (vis.contains(nxtKey))
                return;
            
            dfs(vis, room, nx, ny, i % 4);
            break;
        }
        
    }
}