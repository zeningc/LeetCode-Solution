class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        int m = edges.length;
        Map<Integer, List<int[]>> map = new HashMap<>();
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++)    {
            int[] edge = edges[i];
            map.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[] {edge[1], edge[2]});
            map.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[] {edge[0], edge[2]});
        }
        
        int[] d1 = getShortestDist(map, n, 0);
        int[] d2 = getShortestDist(map, n, n - 1);
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            if (d1[u] == -1 || d2[u] == -1 || d1[v] == -1 || d2[v] == -1)
                continue;
            if (d1[u] + w + d2[v] == d1[n - 1] || d1[v] + w + d2[u] == d1[n - 1])
                ans[i] = true;
        }
        
        return ans;
    }
    
    int[] getShortestDist(Map<Integer, List<int[]>> map, int n, int source)   {
        int[] d = new int[n];
        Arrays.fill(d, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {source, 0});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int w = cur[1];
            
            if (d[u] != -1)
                continue;
            d[u] = w;
            for (int[] nxt : map.getOrDefault(u, new ArrayList<>()))    {
                int v = nxt[0];
                int nw = nxt[1];
                if (d[v] != -1)
                    continue;
                pq.offer(new int[] {v, w + nw});
            }
        }
        
        return d;
    }
}