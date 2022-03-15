class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times)    {
            if (!edges.containsKey(time[0]))
                edges.put(time[0], new LinkedList<>());
            edges.get(time[0]).add(new int[] {time[1], time[2]});
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {k, 0});
        dist[k] = 0;
        boolean[] vis = new boolean[n + 1];
        while (!pq.isEmpty())    {
            int[] curr = pq.poll();
            int u = curr[0];
            int currTime = curr[1];
            if (vis[u])
                continue;
            vis[u] = true;
            dist[u] = currTime;
            if (edges.containsKey(u))   {
                for (int[] dest : edges.get(u)) {
                    int v = dest[0];
                    int w = dest[1];
                    if (vis[v])
                        continue;
                    pq.offer(new int[] {v, currTime + w});
                }
            }
        }
        int ans = -1;
        for (int i = 1; i <= n; i++)    {
            if (dist[i] == -1)
                return -1;
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}