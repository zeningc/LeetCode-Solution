class Solution {
    int[] dist0;
    Map<Integer, List<int[]>> edges;
    int[] mem;
    int mod = 1000000007;
    int n;
    public int countPaths(int n, int[][] roads) {
        this.n = n;
        mem = new int[n];
        Arrays.fill(mem, -1);
        dist0 = new int[n];
        Arrays.fill(dist0, -1);
        edges = new HashMap<>();
        for (int[] road : roads)    {
            int u = road[0];
            int v = road[1];
            int t = road[2];
            if (!edges.containsKey(u))
                edges.put(u, new LinkedList<int[]>());
            if (!edges.containsKey(v))
                edges.put(v, new LinkedList<>());
            edges.get(u).add(new int[] {v, t});
            edges.get(v).add(new int[] {u, t});
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int t = curr[0];
            int u = curr[1];
            if (dist0[u] != -1)
                continue;
            dist0[u] = t;
            if (!edges.containsKey(u))
                continue;
            for (int[] next : edges.get(u)) {
                int v = next[0];
                int nextT = next[1];
                if (dist0[v] != -1)
                    continue;
                pq.offer(new int[] {t + nextT, v});
            }
        }
        
        return dfs(n - 1, 0);
        
    }
    
    int dfs(int u, int dist2N) {
        int[] dist = dist0;
        if (u == 0)
            return 1;
        if (mem[u] != -1)
            return mem[u];
        int ans = 0;
        for (int[] next : edges.get(u)) {
            int v = next[0];
            int t = next[1];
            if (t + dist0[v] + dist2N == dist0[n - 1])
                ans = (ans + dfs(v, dist2N + t)) % mod;
        }
        
        mem[u] = ans;
        return mem[u];
    }
}

