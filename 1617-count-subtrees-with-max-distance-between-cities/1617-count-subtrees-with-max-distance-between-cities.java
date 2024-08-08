class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
        }
        
        int[] ans = new int[n - 1];
        for (int mask = 1; mask < (1 << n); mask++)    {
            boolean[] in = new boolean[n];
            int start = -1;
            for (int i = 0; i < n; i++)    {
                if ((mask & (1 << i)) != 0) {
                    start = i;
                    in[i] = true;
                }
            }
            int[] first = bfs(graph, start, in);
            int secondStart = first[0];
            if (secondStart == -1)
                continue;
            int[] second = bfs(graph, secondStart, in);
            if (second[1] == 0)
                continue;
            ans[second[1] - 1]++;
        }
        
        return ans;
    }
    
    int[] bfs(Map<Integer, List<Integer>> graph, int start, boolean[] in)  {
        Deque<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[in.length];
        int last = -1;
        q.offer(start);
        int dist = -1;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (!in[u] || vis[u])
                    continue;
                vis[u] = true;
                last = u;
                for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
                    if (vis[v] || !in[v])
                        continue;
                    q.offer(v);
                }
            }
            
            dist++;
        }
        
        for (int i = 0; i < in.length; i++)
            if (in[i] && !vis[i])
                return new int[] {-1, -1};
        
        return new int[] {last, dist};
    }
}