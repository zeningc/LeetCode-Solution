class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] f : flights)
            map.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[] {f[1], f[2]});
        pq.offer(new int[] {src, 0, 0});
        boolean[][] vis = new boolean[n][k + 3];
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int c = cur[1];
            int step = cur[2];
            if (vis[u][step])
                continue;
            vis[u][step] = true;
            if (step >= k + 2)
                continue;
            if (u == dst)
                return c;
            if (!map.containsKey(u))
                continue;
            for (int[] nxt : map.get(u))    {
                if (vis[nxt[0]][step + 1])
                    continue;
                pq.offer(new int[] {nxt[0], c + nxt[1], step + 1});
            }
        }
        
        return -1;
    }
}