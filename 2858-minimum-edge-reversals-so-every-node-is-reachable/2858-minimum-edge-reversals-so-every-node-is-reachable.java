class Solution {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Set<Integer>> orderGraph;
    int[] ans;
    int n;
    public int[] minEdgeReversals(int n, int[][] edges) {
        graph = new HashMap<>();
        orderGraph = new HashMap<>();
        this.n = n;
        ans = new int[n];
        for (int[] edge : edges)    {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
            orderGraph.computeIfAbsent(edge[0], x -> new HashSet<>()).add(edge[1]);
        }
        
        int cnt = dfs(0, -1);
        dfs1(0, -1, cnt);
        return ans;
    }
    
    int dfs(int u, int p)   {
        int cnt = 0;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            if (orderGraph.getOrDefault(u, new HashSet<>()).contains(v))
                cnt++;
            cnt += dfs(v, u);
        }
        
        return cnt;
    }
    
    void dfs1(int u, int p, int cnt) {
        ans[u] = n - cnt - 1;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            int delta = 0;
            if (orderGraph.getOrDefault(u, new HashSet<>()).contains(v))
                delta = -1;
            else
                delta = 1;
            dfs1(v, u, cnt + delta);
        }
    }
}