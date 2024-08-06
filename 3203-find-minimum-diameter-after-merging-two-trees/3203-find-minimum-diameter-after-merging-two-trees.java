class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int a = treeDiameter(edges1);
        int b = treeDiameter(edges2);
        return Math.max((a + 1) / 2 + (b + 1) / 2 + 1, Math.max(a, b));
    }
    
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)   {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        
        int[] first = bfs(graph, n, 0);
        return bfs(graph, n, first[0])[1];
    }
    
    int[] bfs(Map<Integer, List<Integer>> graph, int n, int start) {
        Deque<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        int lastNode = -1;
        int dist = -1;
        q.offer(start);
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (vis[u])
                    continue;
                lastNode = u;
                vis[u] = true;
                for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
                    if (vis[v])
                        continue;
                    q.offer(v);
                }
            }
            dist++;
        }
        
        return new int[] {lastNode, dist};
    }
}