class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int[] indeg = new int[n];
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!map.containsKey(u))
                map.put(u, new HashSet<>());
            if (!map.containsKey(v))
                map.put(v, new HashSet<>());
            map.get(u).add(v);
            map.get(v).add(u);
            indeg[u]++;
            indeg[v]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> delete = new HashSet<>();
        for (int i = 0; i < n; i++)
            if (indeg[i] == 1 && coins[i] == 0)
                q.offer(i);

        while (!q.isEmpty())    {
            int u = q.poll();
            delete.add(u);
            for (int v : map.get(u))    {
                indeg[v]--;
                map.get(v).remove(u);
                if (indeg[v] == 1 && coins[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        q.clear();
        int[] depth = new int[n];
        for (int i = 0; i < n; i++)
            if (indeg[i] == 1 && !delete.contains(i))
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            for (int v : map.get(u))    {
                map.get(v).remove(u);
                indeg[v]--;
                depth[v] = Math.max(depth[v], depth[u] + 1);
                if (indeg[v] == 1 && !delete.contains(v))
                    q.offer(v);
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++)
            if (depth[i] >= 2)
                ans++;
        
        return ans == 0 ? 0 : (ans - 1) * 2;
    }
}