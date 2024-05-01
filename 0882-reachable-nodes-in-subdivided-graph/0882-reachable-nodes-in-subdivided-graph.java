class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        int nEdge = edges.length;
        boolean[] visEdge = new boolean[nEdge];
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        for (int i = 0; i < nEdge; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(new int[] {v, i, w});
            graph.get(v).add(new int[] {u, i, w});
        }
        int ans = 0;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0, -1});
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int u = curr[1];
            int currW = curr[0];
            int fromEdge = curr[2];
            if (vis[u] != -1)
                continue;
            vis[u] = currW;
            if (fromEdge != -1) {
                ans += edges[fromEdge][2] + 1;
                visEdge[fromEdge] = true;
            }
            if (!graph.containsKey(u))   
                continue;
            for (int[] dest : graph.get(u)) {
                int v = dest[0];
                int e = dest[1];
                int w = dest[2];
                if (vis[v] != -1)
                    continue;
                if (currW + w + 1 <= maxMoves)
                    pq.offer(new int[] {currW + w + 1, v, e});
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1)
                continue;
            int remainMoves = maxMoves - vis[i];
            if (!graph.containsKey(i))   
                continue;
            for (int[] dest : graph.get(i)) {
                int v = dest[0];
                int e = dest[1];
                int w = dest[2];
                if (visEdge[e])
                    continue;
                if (vis[v] == -1)   {
                    ans += remainMoves;
                    continue;
                }
                int vRemainMoves = maxMoves - vis[v];
                ans += Math.min(edges[e][2], vRemainMoves + remainMoves);
                visEdge[e] = true;
            }
        }
        
        return ans + 1;
        
    }
}