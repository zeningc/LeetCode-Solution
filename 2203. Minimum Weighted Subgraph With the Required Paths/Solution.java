class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, List<int[]>> revGraph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (!graph.containsKey(u)) {
                graph.put(u, new LinkedList<>());
            }
            if (!revGraph.containsKey(v)) {
                revGraph.put(v, new LinkedList<>());
            }
            revGraph.get(v).add(new int[] { u, w });
            graph.get(u).add(new int[] { v, w });
        }

        Map<Integer, Long> dist1 = shortestPath(graph, src1);
        Map<Integer, Long> dist2 = shortestPath(graph, src2);
        Map<Integer, Long> dist3 = shortestPath(revGraph, dest);
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist1.containsKey(i) && dist2.containsKey(i) && dist3.containsKey(i)) {
                ans = Math.min(ans, dist1.get(i) + dist2.get(i) + dist3.get(i));
            }
        }
        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    Map<Integer, Long> shortestPath(Map<Integer, List<int[]>> graph, int src) {
        Map<Integer, Long> dist = new HashMap<>();
        Queue<long[]> pq = new PriorityQueue<long[]>((a, b) -> (a[0] - b[0] > 0 ? 1 : (a[0] == b[0] ? 0 : -1)));
        pq.offer(new long[] { (long) 0, src });
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long ttl = curr[0];
            int u = (int) curr[1];
            if (dist.containsKey(u))
                continue;
            dist.put(u, ttl);
            if (graph.containsKey(u)) {
                for (int[] dest : graph.get(u)) {
                    int v = dest[0];
                    int w = dest[1];
                    if (dist.containsKey(v))
                        continue;
                    pq.offer(new long[] { ttl + w, v });
                }
            }
        }
        return dist;
    }
}