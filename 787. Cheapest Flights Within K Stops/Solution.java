class Solution {
    public int minimumCost(int n, int[][] highways, int discounts) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        
        for (int[] highway : highways)  {
            int u = highway[0];
            int v = highway[1];
            int t = highway[2];
            
            if (!edges.containsKey(u))
                edges.put(u, new LinkedList<>());
            if (!edges.containsKey(v))
                edges.put(v, new LinkedList<>());
            
            edges.get(u).add(new int[] {v, t});
            edges.get(v).add(new int[] {u, t});
        }
        
        boolean[][] vis = new boolean[n][discounts + 1];
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] != b[0] ? a[0] - b[0] : (a[1] == b[1] ? 0 : b[1] - a[1])));
        pq.offer(new int[] {0, discounts, 0});
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int t = curr[0];
            int discnt = curr[1];
            int u = curr[2];
            
            if (vis[u][discnt])
                continue;
            vis[u][discnt] = true;
            if (u == n - 1)
                return t;
            
            if (!edges.containsKey(u))
                continue;
            
            for (int[] next : edges.get(u)) {
                int v = next[0];
                int nextT = next[1];
                if (!vis[v][discnt])    {
                    pq.offer(new int[] {t + nextT, discnt, v});
                }
                if (discnt > 0 && !vis[v][discnt - 1])    {
                    pq.offer(new int[] {t + nextT / 2, discnt - 1, v});
                }
            }
        }
        
        return -1;
    }
}