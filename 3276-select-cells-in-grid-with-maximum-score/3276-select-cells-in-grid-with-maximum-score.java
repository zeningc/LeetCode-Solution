class Solution {
    public int maxScore(List<List<Integer>> grid) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < grid.size(); i++)
            for (int j = 0; j < grid.get(0).size(); j++)
                map.computeIfAbsent(grid.get(i).get(j), x -> new ArrayList<>()).add(i);
        int[][] dp = new int[101][1 << grid.size()];
        for (int i = 0; i < 101; i++)
            Arrays.fill(dp[i], -1);
        return dfs(map, dp, 100, 0);
    }
    
    int dfs(Map<Integer, List<Integer>> map, int[][] dp, int num, int mask)  {
        if (num == 0)
            return 0;
        
        if (dp[num][mask] != -1)
            return dp[num][mask];
        
        int ans = dfs(map, dp, num - 1, mask);
        for (int i : map.getOrDefault(num, new ArrayList<>())) {
            if ((mask & (1 << i)) == 0) {
                ans = Math.max(ans, dfs(map, dp, num - 1, mask | (1 << i)) + num);
            }
        }
        dp[num][mask] = ans;
        return ans;
    }
}