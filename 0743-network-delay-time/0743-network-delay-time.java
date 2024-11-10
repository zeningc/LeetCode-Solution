class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int cnt = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times)
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[] {time[1], time[2]});
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] vis = new boolean[n + 1];
        pq.offer(new int[] {k, 0});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int t = cur[1];
            
            if (vis[u])
                continue;
            vis[u] = true;
            cnt++;
            if (cnt == n)
                return t;
            
            for (int[] nxt : graph.getOrDefault(u, new ArrayList<>()))  {
                int v = nxt[0];
                int timeToTravel = nxt[1];
                if (vis[v])
                    continue;
                pq.offer(new int[] {v, t + timeToTravel});
            }
            
        }
        
        return -1;
    }
}