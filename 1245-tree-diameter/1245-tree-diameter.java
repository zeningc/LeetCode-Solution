class Solution {
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)   {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        
        Deque<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        int lastNode = -1;
        q.offer(0);
        while (!q.isEmpty())    {
            int u = q.poll();
            if (vis[u])
                continue;
            vis[u] = true;
            lastNode = u;
            for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
                if (vis[v])
                    continue;
                q.offer(v);
            }
        }
        
        q.clear();
        q.offer(lastNode);
        int dist = 0;
        vis = new boolean[n];
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (vis[u])
                    continue;
                vis[u] = true;
                for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
                    if (vis[v])
                        continue;
                    q.offer(v);
                }
            }
            dist++;
        }
        
        return dist - 1;
    }
}