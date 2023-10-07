class Solution {
    int[] indeg;
    int[] edges;
    int[] ans;
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        this.edges = new int[n];
        for (int i = 0; i < n; i++)
            this.edges[i] = edges.get(i);
        indeg = new int[n];
        ans = new int[n];
        for (int edge : edges)
            indeg[edge]++;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indeg[i] == 0)
                q.offer(i);
        
        while (!q.isEmpty())
        {
            int u = q.poll();
            int v = this.edges[u];
            indeg[v]--;
            if (indeg[v] == 0)
                q.offer(v);
        }
        
        for (int i = 0; i < n; i++)
        {
            if (indeg[i] == 0)
                continue;
            if (ans[i] != 0)
                continue;
            int u = this.edges[i];
            int len = 1;
            while (u != i)
            {
                len++;
                u = this.edges[u];
            }
            u = this.edges[i];
            ans[i] = len;
            while (u != i)
            {
                ans[u] = len;
                u = this.edges[u];
            }
        }
        
        for (int i = 0; i < n; i++)
            if (ans[i] == 0)
                dfs(i);
        
        return ans;
    }
    
    int dfs(int u)
    {
        if (ans[u] != 0)
            return ans[u];
        
        ans[u] = dfs(edges[u]) + 1;
        return ans[u];
    }
}