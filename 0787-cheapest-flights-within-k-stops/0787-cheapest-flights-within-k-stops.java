class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> f = new HashMap<>();
        for (int[] flight : flights)
            f.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[] {flight[1], flight[2]});
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {src, 0, 1});
        boolean[][] vis = new boolean[n][k + 3];
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int p = cur[1];
            int c = cur[2];
            if (vis[u][c])
                continue;
            vis[u][c] = true;
            if (u == dst)
                return p;
            if (c + 1 > k + 2)
                continue;
            for (int[] nxt : f.getOrDefault(u, new ArrayList<>()))  {
                int v = nxt[0];
                int np = nxt[1];
                
                if (vis[v][c + 1])
                    continue;
                pq.offer(new int[] {v, p + np, c + 1});
            }
        }
        
        return -1;
    }
}