class Solution {
    int[] distanceToLastNode;
    Map<Integer, List<int[]>> graph;
    int n;
    long[] mem;
    final static int mod = 1000000007;
    public int countRestrictedPaths(int n, int[][] edges) {
        distanceToLastNode = new int[n + 1];
        mem = new long[n + 1];
        Arrays.fill(distanceToLastNode, -1);
        Arrays.fill(mem, (long)-1);
        this.n = n;
        graph = new HashMap<>();
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
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, n});
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int u = curr[1];
            int w = curr[0];
            if (distanceToLastNode[u] != -1)
                continue;
            distanceToLastNode[u] = w;
            if (!graph.containsKey(u))
                continue;
            for (int[] next : graph.get(u)) {
                int v = next[0];
                int nextW = next[1];
                if (distanceToLastNode[v] != -1)
                    continue;
                pq.offer(new int[] {w + nextW, v});
            }
        }
        dfs(1);
        return (int)(mem[1] % mod);
    }
    
    long dfs(int u) {
        if (u == n)
            return 1;
        
        if (mem[u] != -1)   {
            return mem[u];
        }
        
        if (!graph.containsKey(u))  {
            mem[u] = 0;
            return 0;
        }
        long cnt = 0;
        for (int[] next : graph.get(u)) {
            int v = next[0];
            if (distanceToLastNode[u] <= distanceToLastNode[v])
                continue;
            cnt = (cnt + dfs(v)) % mod;
        }
        
        mem[u] = cnt % mod;
        
        return mem[u];
    }
}