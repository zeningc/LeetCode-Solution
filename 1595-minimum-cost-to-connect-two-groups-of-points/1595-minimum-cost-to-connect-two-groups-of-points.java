class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        
        int[][] grpCost = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            for (int state = 0; state < (1 << n); state++)  {
                int val = 0;
                for (int j = 0; j < n; j++) {
                    if ((state & (1 << j)) == 0)
                        continue;
                    val += cost.get(i).get(j);
                }
                
                grpCost[i][state] = val;
            }
        }
        
        for (int i = 0; i < m; i++)
            Collections.sort(cost.get(i));
        
        
        for (int state = 1; state < (1 << n); state++)
            dp[0][state] = grpCost[0][state];
        
        for (int i = 1; i < m; i++) {
            for (int state = 1; state < (1 << n); state++)  {
                for (int subset = state; subset > 0; subset = (subset - 1) & state)    {
                    dp[i][state] = Math.min(dp[i][state], dp[i - 1][state - subset] + grpCost[i][subset]);
                }
                dp[i][state] = Math.min(dp[i][state], dp[i - 1][state] + cost.get(i).get(0));
            }
        }
        
        return dp[m - 1][(1 << n) - 1];
    }
    
}