class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] ans = new int[n];
        int[] cnt = new int[n];
        int[] dist = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] parent = new int[n];
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
        dfs(graph, parent, cnt, dist, -1, 0);
        ans[0] = dist[0];
        dfs1(graph, parent, cnt, dist, ans, -1, 0);
        return ans;
    }
    
    int[] dfs(Map<Integer, List<Integer>> graph, int[] parent, int[] cnt, int[] dist, int p, int u)  {
        parent[u] = p;
        int[] ret = new int[] {1, 0};
        if (graph.containsKey(u))   {
            for (int v : graph.get(u))  {
                if (v == p)
                    continue;
                int[] next = dfs(graph, parent, cnt, dist, u, v);
                ret[0] += next[0];
                ret[1] += next[1] + next[0];
            }
        }
        cnt[u] = ret[0];
        dist[u] = ret[1];
        return ret;
    }
    
    void dfs1(Map<Integer, List<Integer>> graph, int[] parent, int[] cnt, int[] dist, int[] ans, int p, int u)  {
        if (u != 0)
            ans[u] = ans[parent[u]] - 2 * cnt[u] + cnt[0];
        if (graph.containsKey(u))   {
            for (int v : graph.get(u))  {
                if (v == p)
                    continue;
                dfs1(graph, parent, cnt, dist, ans, u, v);
            }
        }
    }
}


// cur + parent - cur - curCnt + parentCnt - curCnt