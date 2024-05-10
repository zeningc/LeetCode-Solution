class Solution {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[][] editable = new boolean[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(editable[i], false);
        int[][] updatedEdge = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(updatedEdge[i], Integer.MAX_VALUE / 3);
        for (int[] e : edges)   {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if (w == -1)    {
                editable[u][v] = true;
                editable[v][u] = true;
            }
            updatedEdge[u][v] = Math.max(1, w);
            updatedEdge[v][u] = Math.max(1, w);
            map.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Integer[] dist1 = new Integer[n];
        pq.offer(new int[] {destination, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int w = cur[1];
            if (dist1[u] != null)
                continue;
            dist1[u] = w;
            
            if (!map.containsKey(u))
                continue;
            
            for (int v : map.get(u))    {
                int nw = updatedEdge[u][v];
                if (dist1[v] != null)
                    continue;
                pq.offer(new int[] {v, w + nw});
            }
        }
        pq.clear();
        Integer[] dist = new Integer[n];
        pq.offer(new int[] {source, 0});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int w = cur[1];
            if (dist[u] != null)
                continue;
            dist[u] = w;
            if (u == destination && w != target)
                return new int[0][0];
            if (!map.containsKey(u))
                continue;
            
            for (int v : map.get(u))    {
                int nw = updatedEdge[u][v];
                if (dist[v] != null)
                    continue;
                if (editable[u][v] && dist[u] + nw + dist1[v] < target)    {
                    nw = target - dist1[v] - dist[u];
                    updatedEdge[u][v] = nw;
                    updatedEdge[v][u] = nw;
                }
                pq.offer(new int[] {v, w + nw});
            }
        }
        
        for (int i = 0; i < edges.length; i++)  {
            int a = edges[i][0];
            int b = edges[i][1];
            edges[i][2] = updatedEdge[a][b];
        }
        
        return edges;
    }
}