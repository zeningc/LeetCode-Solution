class Solution {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> bobVisited;
    int[] parent;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        graph = new HashMap<>();
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, a -> new LinkedList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new LinkedList<Integer>()).add(u);
        }
        parent = new int[amount.length];
        bobVisited = new HashMap<>();
        dfs(0, -1);
        for (int i = 0; bob != -1; i++)
        {
            bobVisited.put(bob, i);
            bob = parent[bob];
        }
        
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        int ans = Integer.MIN_VALUE;
        int t = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                int[] cur = q.poll();
                int u = cur[0];
                int amt = cur[1];
                int bobVistedTime = bobVisited.getOrDefault(u, Integer.MAX_VALUE);
                if (bobVistedTime == t)
                    amt += amount[u] / 2;
                else if (bobVistedTime > t)
                    amt += amount[u];
                if (!graph.containsKey(u))
                    continue;
                boolean hasChild = false;
                for (int v : graph.get(u))
                {
                    if (v == parent[u])
                        continue;
                    hasChild = true;
                    q.offer(new int[] {v, amt});
                }
                
                if (!hasChild)
                {
                    ans = Math.max(ans, amt);
                }
            }
            t++;
        }
        
        return ans;
    }
    
    
    void dfs(int u, int p)
    {
        parent[u] = p;
        if (!graph.containsKey(u))
            return;
        for (int v : graph.get(u))
        {
            if (v == p)
                continue;
            dfs(v, u);
        }
    }
}