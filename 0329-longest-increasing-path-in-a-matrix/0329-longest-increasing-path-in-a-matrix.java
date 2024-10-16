class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        Map<String, Integer> cache = new HashMap<>();
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                dfs(dir, cache, matrix, i, j);
    
        int ans = 1;
        for (int val : cache.values())
            ans = Math.max(ans, val);
        return ans;
    }
    
    int dfs(int[][] dir, Map<String, Integer> cache, int[][] matrix, int x, int y)  {
        String key = x + "_" + y;
        if (cache.containsKey(key))
            return cache.get(key);
        
        int ans = 1;
        
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx > matrix.length - 1 || ny < 0 || ny > matrix[0].length - 1 || matrix[nx][ny] <= matrix[x][y])
                continue;
            ans = Math.max(ans, 1 + dfs(dir, cache, matrix, nx, ny));
        }
        
        cache.put(key, ans);
        return ans;
    }
}