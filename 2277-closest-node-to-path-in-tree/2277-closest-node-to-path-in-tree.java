class Solution {
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        if (n == 1)
            return new int[n];
        int[][] dist = new int[n][n];
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            edgeMap.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            edgeMap.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }
        
        for (int i = 0; i < n; i++) {
            dfs(edgeMap, dist, i, i, -1, 0);
        }
        int[] ans = new int[query.length];
        int i = 0;
        for (int[] q : query)   {
            int start = q[0];
            int end = q[1];
            int node = q[2];
            ans[i++] = dfs1(edgeMap, dist, start, end, node, -1)[1];
        }
        
        return ans;
    }
    
    void dfs(Map<Integer, List<Integer>> edgeMap, int[][] dist, int start, int u, int p, int d)  {
        dist[start][u] = d;
        for (int v : edgeMap.get(u))    {
            if (v == p)
                continue;
            dfs(edgeMap, dist, start, v, u, d + 1);
        }
    }
    
    
    int[] dfs1(Map<Integer, List<Integer>> edgeMap, int[][] dist, int u, int end, int node, int p)   {
        int d = dist[u][node];
        int target = u;
        for (int v : edgeMap.get(u))    {
            if (v == p)
                continue;
            if (dist[v][end] != dist[u][end] - 1)
                continue;
            int[] nxt = dfs1(edgeMap, dist, v, end, node, u);
            if (nxt[0] < d) {
                d = nxt[0];
                target = nxt[1];
            }
        }
        
        return new int[] {d, target};
    }
}