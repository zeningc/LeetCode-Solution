class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        Deque<int[]> pq = new LinkedList<>();
        pq.offer(new int[] {0, 1});
        int[] visited = new int[n + 1];
        int[] shortest = new int[n + 1];
        Arrays.fill(shortest, -1);
        
        while (!pq.isEmpty())   {
            int[] curr = pq.poll();
            int t = curr[0];
            int u = curr[1];
            
            if (visited[u] >= 2 || shortest[u] == t)
                continue;
            
            visited[u]++;
            
            if (shortest[u] == -1)
                shortest[u] = t;
            
            if (u == n && shortest[u] < t)
                return t;

            int arrive = t;
            if ((t / change) % 2 != 0)
                arrive = ((arrive / change) + 1) * change;
            for (int v : graph.get(u))  {
                if (visited[v] >= 2)
                    continue; 
                pq.offer(new int[] {arrive + time, v});
            }
        }
        
        return -1;
    }
}

