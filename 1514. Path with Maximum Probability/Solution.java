class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<double[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++)    {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            double p = succProb[i];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(new double[] {v * 1.0, p});
            graph.get(v).add(new double[] {u * 1.0, p});
        }
        boolean[] vis = new boolean[n];
        Queue<double[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? 0 : a[0] < b[0] ? 1 : -1);
        pq.offer(new double[] {1.0, start});
        while (!pq.isEmpty())   {
            double[] curr = pq.poll();
            int u = (int)curr[1];
            double p = curr[0];
            if (vis[u])
                continue;
            vis[u] = true;
            if (u == end)
                return p;
            if (!graph.containsKey(u))
                continue;
            for (double[] dest : graph.get(u))  {
                int v = (int)dest[0];
                double nextP = dest[1];
                if (vis[v])
                    continue;
                pq.offer(new double[] {p * nextP, v * 1.0});
            }
        }
        
        return (double)0;
    }
}