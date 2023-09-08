class Solution {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (!g.containsKey(u))
                g.put(u, new LinkedList<>());
            if (!g.containsKey(v))
                g.put(v, new LinkedList<>());
            g.get(u).add(new int[] {v, w});
            g.get(v).add(new int[] {u, w});
        }
        int[] d = new int[n];
        int[][] p = new int[n][27];
        int m = 15;
        int[][] dp = new int[n][m];
        dfs(g, d, dp, p, 0, 0, 0);
        for (int j = 1; j < m; j++)
            for (int i = 0; i < n; i++)
                dp[i][j] = dp[dp[i][j - 1]][j - 1];

        int[] ans = new int[queries.length];
        for (int j = 0; j < queries.length; j++)    {
            int[] query = queries[j];
            int x = query[0];
            int y = query[1];
            int ancestor = lca(d, dp, m, x, y);
            int maxFreq = -1;
            int len = d[x] + d[y] - 2 * d[ancestor];
            for (int i = 1; i <= 26; i++)   {
                int freq = p[x][i] + p[y][i] - 2 * p[ancestor][i];
                maxFreq = Math.max(maxFreq, freq);
            }
            ans[j] = len - maxFreq;
        }
        
        return ans;
    }
    
    void dfs(Map<Integer, List<int[]>> g, int[] d, int[][] dp, int[][] p, int parent, int node, int depth)  {
        d[node] = depth;
        dp[node][0] = parent;
        if (!g.containsKey(node))
            return;
        for (int[] next : g.get(node))   {
            if (next[0] == parent)
                continue;
            int v = next[0];
            int w = next[1];
            p[v] = p[node].clone();
            p[v][w]++;
            dfs(g, d, dp, p, node, v, depth + 1);
        }
    }
    
    int lca(int[] d, int[][] dp, int m, int x, int y)    {
        if (d[x] > d[y])    {
            int t = x;
            x = y;
            y = t;
        }
        
        int gap = d[y] - d[x];
        
        for (int i = 0; i < m; i++)
            if ((gap & (1 << i)) != 0)
                y = dp[y][i];
        
        if (x == y)
            return x;
        
        for (int i = m - 1; i >= 0; i--)    {
            if (dp[x][i] != dp[y][i])   {
                x = dp[x][i];
                y = dp[y][i];
            }
        }
        
        
        return dp[x][0];
    }
}