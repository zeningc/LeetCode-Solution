class Solution {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> freq;
    int[] price;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        graph = new HashMap<>();
        freq = new HashMap<>();
        this.price = price;
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            graph.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }

        for (int[] trip : trips)
        {
            calcPath(n, trip[0], trip[1]);
        }
        
        int[] ret = dfs(0, -1);
        Map<Integer, Integer> f = freq;
        return Math.min(ret[0], ret[1]);
    }
    
    int[] dfs(int u, int p)
    {
        int[] cur = new int[2];
        cur[0] = price[u] * freq.getOrDefault(u, 0);
        cur[1] = price[u] * freq.getOrDefault(u, 0) / 2;
        if (!graph.containsKey(u))
            return cur;
        for (int v : graph.get(u))
        {
            if (p == v)
                continue;
            int[] nxt = dfs(v, u);
            cur[0] += Math.min(nxt[0], nxt[1]);
            cur[1] += nxt[0];
        }
        
        return cur;
    }
    
    int calcPath(int n, int start, int target)
    {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {start, -1});
        int[] prev = new int[n];
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int u = cur[0];
            int p = cur[1];
            prev[u] = p;
            if (u == target)
                break;
            for (int v : graph.get(u))
            {
                if (v == p)
                    continue;
                q.offer(new int[] {v, u});
            }
        }
        
        int t = target;
        int ans = 0;
        while (t != -1)
        {
            freq.put(t, freq.getOrDefault(t, 0) + 1);
            t = prev[t];
        }
        
        return ans;
    }
}