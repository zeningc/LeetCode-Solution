class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++)    {
            int[] edge = edges[i];
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[] {edge[1], i});
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[] {edge[0], i});
        }
        
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? 0 : a[1] > b[1] ? -1 : 1);
        pq.offer(new double[] {start_node, 1.0});
        boolean[] vis = new boolean[n];
        while (!pq.isEmpty())   {
            double[] cur = pq.poll();
            int u = (int)cur[0];
            double p = cur[1];
            if (vis[u])
                continue;
            vis[u] = true;
            if (u == end_node)
                return p;
            
            for (int[] nxt : graph.getOrDefault(u, new ArrayList<>()))  {
                int v = nxt[0];
                double np = succProb[nxt[1]];
                if (vis[v])
                    continue;
                pq.offer(new double[] {v, p * np});
            }
        }
        return 0;
    }
}