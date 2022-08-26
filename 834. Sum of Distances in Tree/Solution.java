class Solution {
    int[] subTreeCnt;
    int[] subTreeSum;
    int[] ans;
    int n;
    Map<Integer, List<Integer>> graph;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 1)
            return new int[] {0};
        this.n = n;
        subTreeCnt = new int[n];
        subTreeSum = new int[n];
        int[] sc = subTreeCnt;
        int[] ss = subTreeSum;
        ans = new int[n];
        graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        dfs(0, -1);
        dfs2(0, -1);
        
        return ans;
    }
    
    void dfs(int u, int p)  {
        int cnt = 1;
        int sum = 0;
        
        for (int v : graph.get(u))  {
            if (v == p)
                continue;
            dfs(v, u);
            cnt += subTreeCnt[v];
            sum += subTreeSum[v] + subTreeCnt[v];
        }
        subTreeCnt[u] = cnt;
        subTreeSum[u] = sum;
        return;
    }
    
    void dfs2(int u, int p) {
        if (p == -1)    {
            ans[u] = subTreeSum[u];
        }
        else    {
            ans[u] = ans[p] - subTreeCnt[u] + (n - subTreeCnt[u]);
        }
        
        for (int v : graph.get(u))  {
            if (v == p)
                continue;
            dfs2(v, u);
        }
    }
    
}
