class TreeAncestor {
    int[][] dp;
    int pow;
    public TreeAncestor(int n, int[] parent) {
        pow = (int) (Math.log(n) / Math.log(2)) + 1;
        dp = new int[n][pow];
        for (int i = 0; i < n; i++)
            dp[i][0] = parent[i];
        for (int i = 1; i < pow; i++)   {
            for (int j = 0; j < n; j++) {
                if (dp[j][i - 1] == -1) {
                    dp[j][i] = -1;
                    continue;
                }
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
        }
    }
    
    public int getKthAncestor(int node, int k) {
        for (int i = 0; i < pow; i++)    {
            if ((k & (1 << i)) != 0)    {
                if (node == -1)
                    return -1;
                node = dp[node][i];
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */