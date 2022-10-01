class Solution {
    public int[] distanceToCycle(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] deg = new int[n];
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
            deg[u]++;
            deg[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++)
            set.add(i);
        for (int i = 0; i < n; i++)
            if (deg[i] == 1)
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            set.remove(u);
            for (int v : graph.get(u))  {
                deg[v]--;
                if (deg[v] == 1)
                    q.offer(v);
            }
        }
        
        q = new LinkedList<>();
        for (int u : set)
            q.offer(u);
        int[] ans = new int[n];
        int dist = 0;
        boolean[] visited = new boolean[n];
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (visited[u])
                    continue;
                visited[u] = true;
                ans[u] = dist;
                for (int v : graph.get(u))  {
                    if (visited[v])
                        continue;
                    q.offer(v);
                }
            }
            dist++;
        }
        return ans;            
    }
}