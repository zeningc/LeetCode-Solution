class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] edge : edges)    {
            map.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[] {edge[1], edge[2]});
            map.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[] {edge[0], edge[2]});
        }
        
        Integer[] ans = new Integer[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int len = cur[1];
            
            if (ans[u] != null)
                continue;
            
            if (len >= disappear[u]) {
                ans[u] = -1;
                continue;
            }
            
            ans[u] = len;
            
            for (int[] nxt : map.getOrDefault(u, new ArrayList<>()))    {
                int v = nxt[0];
                int newLen = nxt[1];
                if (ans[v] != null)
                    continue;
                
                pq.offer(new int[] {v, len + newLen});
            }
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++)
            if (ans[i] == null)
                ret[i] = -1;
            else
                ret[i] = ans[i].intValue();
        return ret;
    }
}