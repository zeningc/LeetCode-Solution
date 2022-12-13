class Solution {
    public int magnificentSets(int n, int[][] edges) {
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
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 1; i <= n; i++)    {
            Set<Integer> prev = new HashSet<>();
            Set<Integer> visited = new HashSet<>();
            Deque<Integer> q = new LinkedList<>();
            q.offer(i);
            int grpCnt = 0;
            int grpId = Integer.MAX_VALUE;
            while (!q.isEmpty())    {
                grpCnt++;
                int size = q.size();
                Set<Integer> cur = new HashSet<>();
                while (size-- > 0)  {
                    int u = q.poll();
                    grpId = Math.min(grpId, u);
                    if (visited.contains(u))
                        continue;
                    visited.add(u);
                    if (!map.containsKey(u))
                        continue;
                    
                    for (int v : map.get(u))    {
                        if (prev.contains(v))   {
                            return -1;
                        }
                        if (visited.contains(v))
                            continue;
                        q.offer(v);
                        cur.add(v);
                    }
                }
                prev = cur;
            }
            m.put(grpId, Math.max(grpCnt, m.getOrDefault(grpId, 0)));
        }
        int ans = 0;
        for (int k : m.keySet())
            ans += m.get(k);
        
        return ans;
    }
}