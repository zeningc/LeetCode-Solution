class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        Map<Integer, List<int[]>> revMap = new HashMap<>();
        for (int[] e : edges)   {
            int u = e[0];
            int v = e[1];
            int c = e[2];
            map.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[] {v, c});
            revMap.computeIfAbsent(v, x -> new ArrayList<>()).add(new int[] {u, c});
        }
        long[] dist1 = findDist(map, n, src1);
        long[] dist2 = findDist(map, n, src2);
        long[] dist3 = findDist(revMap, n, dest);
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist1[i] != -1 && dist2[i] != -1 && dist3[i] != -1)
                ans = Math.min(ans, dist1[i] + dist2[i] + dist3[i]);
        }
        return ans == Long.MAX_VALUE ? -1 : ans;
    }
    
    long[] findDist(Map<Integer, List<int[]>> map, int n, int src) {
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> a[1] == b[1] ? 0 : (a[1] > b[1] ? 1 : -1));
        long[] dist = new long[n];
        Arrays.fill(dist, -1);
        pq.offer(new long[] {src, 0});
        
        while (!pq.isEmpty())   {
            long[] cur = pq.poll();
            int u = (int)cur[0];
            long c = cur[1];
            if (dist[u] != -1)
                continue;
            dist[u] = c;
            if (!map.containsKey(u))
                continue;
            for (int[] nxt : map.get(u))    {
                int v = nxt[0];
                int nxtCost = nxt[1];
                if (dist[v] != -1)
                    continue;
                pq.offer(new long[] {v, c + nxtCost});
            }
        }
        
        return dist;
    }
}