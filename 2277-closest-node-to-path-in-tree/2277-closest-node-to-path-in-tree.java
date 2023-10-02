class Solution {
    Map<Integer, List<Integer>> graph;
    int[] parent;
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        graph = new HashMap<>();
        parent = new int[n];
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, a -> new LinkedList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new LinkedList<Integer>()).add(u);
        }
        dfs(0, -1);
        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++)
        {
            int[] q = query[i];
            int u = q[0];
            int v = q[1];
            int node = q[2];
            Set<Integer> path = new HashSet<>();
            findPath(path, u, v);
            Deque<Integer> queue = new LinkedList<>();
            queue.offer(node);
            Set<Integer> vis = new HashSet<>();
            while (!queue.isEmpty())
            {
                int cur = queue.poll();
                if (vis.contains(cur))
                    continue;
                vis.add(cur);
                if (path.contains(cur))
                {
                    ans[i] = cur;
                    break;
                }
                if (!graph.containsKey(cur))
                    continue;
                for (int nxt : graph.get(cur))
                {
                    if (!vis.contains(nxt))
                        queue.offer(nxt);
                }
            }
        }
        
        return ans;
    }
    
    void dfs(int u, int p)
    {
        parent[u] = p;
        if (!graph.containsKey(u))
            return;
        for (int v : graph.get(u))
        {
            if (p == v)
                continue;
            dfs(v, u);
        }
    }
    
    void findPath(Set<Integer> set, int u, int v)
    {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();
        int t = u;
        while (t != -1)
        {
            path1.add(0, t);
            t = parent[t];
        }
        t = v;
        while (t != -1)
        {
            path2.add(0, t);
            t = parent[t];
        }
        int p = 0;
        while (p < path1.size() && p < path2.size() && path1.get(p).equals(path2.get(p)))
            p++;
        
        for (int i = p - 1; i < path1.size(); i++)
            if (i >= 0)
                set.add(path1.get(i));

        for (int i = p - 1; i < path2.size(); i++)
            if (i >= 0)
                set.add(path2.get(i));
        
        return;
    }
    
    
}