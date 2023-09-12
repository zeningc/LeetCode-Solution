class Solution {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int c = 26;
        int[][] prefix = new int[n][c + 1];
        int[] parent = new int[n];
        int[] depth = new int[n];
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(new int[] {v, w});
            graph.get(v).add(new int[] {u, w});
        }
        
        dfs(graph, prefix, parent, depth, 0, 0, 0);
        
        int[][] dp = new int[n][32];
        
        for (int i = 0; i < n; i++)
            dp[i][0] = parent[i];
        
        for (int i = 1; i < 32; i++)
            for (int j = 0; j < n; j++)
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++)    {
            int a = queries[i][0];
            int b = queries[i][1];
            int anc = lca(dp, depth, a, b);
            int maxFreq = 0;
            for (int j = 1; j <= 26; j++)   {
                int freq = prefix[a][j] + prefix[b][j] - 2 * prefix[anc][j];
                maxFreq = Math.max(maxFreq, freq);
            }
            int len = depth[a] + depth[b] - 2 * depth[anc];
            ans[i] = len - maxFreq;
        }
        
        return ans;
    }
    
    int lca(int[][] dp, int[] depth, int a, int b)   {
        if (depth[a] > depth[b])    {
            int t = a;
            a = b;
            b = t;
        }
        
        int depthGap = depth[b] - depth[a];
        
        for (int i = 0; i < 32; i++)    {
            if ((depthGap & (1 << i)) != 0) {
                b = dp[b][i];
            }
        }
        
        if (a == b)
            return a;
        
        for (int i = 31; i >= 0; i--)    {
            if (dp[a][i] == dp[b][i])
                continue;
            a = dp[a][i];
            b = dp[b][i];
        }
        
        
        return dp[a][0];
    }
    
    void dfs(Map<Integer, List<int[]>> graph, int[][] prefix, int[] parent, int[] depth, int p, int u, int d)    {
        depth[u] = d;
        parent[u] = p;
        if (!graph.containsKey(u))
            return;
        for (int[] next : graph.get(u))    {
            int v = next[0];
            int w = next[1];
            if (v == p)
                continue;
            prefix[v] = prefix[u].clone();
            prefix[v][w]++;
            dfs(graph, prefix, parent, depth, u, v, d + 1);
        }
    }
}