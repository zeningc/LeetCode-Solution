class Solution {
    int[][] dir;
    Map<Integer, Integer> map;
    Set<Integer> path;
    public int minimumMoves(int[][] grid) {
        dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        map = new HashMap<>();
        map.put(111111111, 0);
        path = new HashSet<>();
        path.add(encode(grid));
        int ans = dfs(grid);
        return ans;
    }
    
    int dfs(int[][] grid)   {
        int curEncode = encode(grid);
        if (map.containsKey(curEncode))
            return map.get(curEncode);
        int ans = Integer.MAX_VALUE;
        boolean move = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 1) {
                    for (int[] d : dir) {
                        int nx = i + d[0];
                        int ny = j + d[1];
                        if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3)
                            continue;
                        grid[i][j]--;
                        grid[nx][ny]++;
                        if (!path.contains(encode(grid)))   {
                            path.add(encode(grid));
                            int nextAns = dfs(grid);
                            if (nextAns != -1)  {
                                move = true;
                                ans = Math.min(ans, nextAns + 1);
                            }
                            path.remove(encode(grid));
                        }
                        grid[nx][ny]--;
                        grid[i][j]++;
                    }
                }
            }
        }
        
        if (!move)
            return -1;
        map.put(curEncode, ans);
        return ans;
    }
    
    int encode(int[][] grid)  {
        int ret = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                ret = ret * 10 + grid[i][j];
        return ret;
    }
}