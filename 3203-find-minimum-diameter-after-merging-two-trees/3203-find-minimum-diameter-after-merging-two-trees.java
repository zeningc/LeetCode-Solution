class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        Map<Integer, List<Integer>> m1 = new HashMap<>();
        Map<Integer, List<Integer>> m2 = new HashMap<>();
        
        int m = edges1.length + 1;
        int n = edges2.length + 1;
        for (int[] e : edges1)  {
            m1.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            m1.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        
        for (int[] e : edges2)  {
            m2.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            m2.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        
        
        int d1 = bfs(m1);
        int d2 = bfs(m2);
        
        return Math.max((d1 + 1) / 2 + (d2 + 1) / 2 + 1, Math.max(d1, d2));
    }
    
    int bfs(Map<Integer, List<Integer>> m)  {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0, -1});
        int reach = 0;
        int len = 0;
        while (!q.isEmpty())    {
            int[] cur = q.poll();
            int u = cur[0];
            int d = cur[1];
            int p = cur[2];
            if (d > len)    {
                len = d;
                reach = u;
            }
            
            for (int v : m.getOrDefault(u, new ArrayList<>()))  {
                if (v == p)
                    continue;
                q.offer(new int[] {v, d + 1, u});
            }
        }
        
        len = 0;
        q.offer(new int[] {reach, 0, -1});
        
        while (!q.isEmpty())    {
            int[] cur = q.poll();
            int u = cur[0];
            int d = cur[1];
            int p = cur[2];
            if (d > len)    {
                len = d;
            }
            
            for (int v : m.getOrDefault(u, new ArrayList<>()))  {
                if (v == p)
                    continue;
                q.offer(new int[] {v, d + 1, u});
            }
        }
        
        return len;
    }
    
}