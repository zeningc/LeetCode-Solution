class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indeg = new int[n];
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!map.containsKey(u))
                map.put(u, new LinkedList<>());
            if (!map.containsKey(v))
                map.put(v, new LinkedList<>());
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
        Set<Integer> vis = new HashSet<>();
        while (!q.isEmpty())    {
            int u = q.poll();
            vis.add(u);
            for (int v : map.get(u))    {
                if (delete.contains(v) || vis.contains(v))
                    continue;
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