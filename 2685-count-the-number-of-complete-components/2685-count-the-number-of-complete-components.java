class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!map.containsKey(u))
                map.put(u, new LinkedList<>());
            if (!map.containsKey(v))
                map.put(v, new LinkedList<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        int ans = 0;
        boolean[] vis = new boolean[n];
        Deque<Integer> q;
        for (int i = 0; i < n; i++) {
            if (vis[i])
                continue;
            q = new ArrayDeque<>(n);
            q.offer(i);
            int visCnt = 0;
            int edgeCnt = 0;
            while (!q.isEmpty())    {
                int u = q.poll();
                if (vis[u])
                    continue;
                vis[u] = true;
                visCnt++;
                if (!map.containsKey(u))
                    continue;
                edgeCnt += map.get(u).size();
                for (int v : map.get(u))    {
                    if (!vis[v])
                        q.offer(v);
                }
            }
            edgeCnt /= 2;
            if (visCnt * (visCnt - 1) / 2 == edgeCnt)
                ans++;
        }
        return ans;
    }
}