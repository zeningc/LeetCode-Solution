class Solution {
    Map<Integer, List<int[]>> graph;
    int[] ans;
    public int[] minEdgeReversals(int n, int[][] edges) {
        graph = new HashMap<>();
        ans = new int[n];
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, a -> new LinkedList<int[]>()).add(new int[] {v, 1});
            graph.computeIfAbsent(v, a -> new LinkedList<int[]>()).add(new int[] {u, -1});
        }
        
        int cnt = dfs1(0, -1);
        dfs2(0, -1, cnt);
        return ans;
    }
    
    int dfs1(int u, int p)
    {
        int cnt = 0;
        for (int[] nxt : graph.get(u))
        {
            int v = nxt[0];
            int diff = nxt[1];
            if (p == v)
                continue;
            cnt += dfs1(v, u) + (diff == -1 ? 1 : 0);
        }
        return cnt;
    }
    
    void dfs2(int u, int p, int c)
    {
        ans[u] = c;
        for (int[] nxt : graph.get(u))
        {
            int v = nxt[0];
            int diff = nxt[1];
            if (p == v)
                continue;
            dfs2(v, u, diff == 1 ? c + 1 : c - 1);
        }
    }
    
}