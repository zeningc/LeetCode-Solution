class Solution {
    int[] nodeSum;
    int[] nodeCnt;
    int[] ans;
    int n;
    Map<Integer, List<Integer>> graph;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 1)
            return new int[] {0};
        graph = new HashMap<>();
        nodeSum = new int[n];
        nodeCnt = new int[n];
        ans = new int[n];
        this.n = n;
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, a -> new LinkedList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new LinkedList<Integer>()).add(u);
        }
        
        dfs1(0, -1);
        dfs2(0, -1);
        return ans;
    }
    
    int[] dfs1(int u, int p)
    {
        int curSum = 0;
        int curCnt = 0;
        for (int v : graph.get(u))
        {
            if (v == p)
                continue;
            int[] summary = dfs1(v, u);
            int cnt = summary[0];
            int childSum = summary[1];
            curCnt += cnt;
            curSum += childSum + cnt;
        }
        nodeSum[u] = curSum;
        nodeCnt[u] = curCnt + 1;
        return new int[] {curCnt + 1, curSum};
    }
    
    void dfs2(int u, int p)
    {
        if (p != -1)
            ans[u] = ans[p] - nodeCnt[u] + n - nodeCnt[u];
        else
            ans[u] = nodeSum[u];
        
        for (int v : graph.get(u))
        {
            if (v == p)
                continue;
            dfs2(v, u);
        }
        
    }
}