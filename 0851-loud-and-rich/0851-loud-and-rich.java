class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Map<Integer, List<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> inDeg = new HashMap<>();
        for (int[] r : richer)  {
            int u = r[0];
            int v = r[1];
            inDeg.put(v, inDeg.getOrDefault(v, 0) + 1);
            if (!edges.containsKey(u))
                edges.put(u, new LinkedList<>());
            edges.get(u).add(v);
        }
        
        int[] ans = new int[n];
        Map<Integer, int[]> prev = new HashMap<>();
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg.getOrDefault(i, 0) == 0)  {
                q.offer(i);
                prev.put(i, new int[] {quiet[i], i});
            }
        }
        
        while (!q.isEmpty())    {
            int u = q.poll();
            int[] cur = prev.get(u);
            int curMinQ = cur[0];
            int curMinP = cur[1];
            if (quiet[u] < curMinQ)
                ans[u] = u;
            else
                ans[u] = curMinP;
            if (!edges.containsKey(u))
                continue;
                
            for (int v : edges.get(u))  {
                inDeg.put(v, inDeg.get(v) - 1);
                
                int[] next = prev.getOrDefault(v, new int[] {quiet[v], v});
                int nextMinQ = next[0];
                int nextMinP = next[1];
                if (nextMinQ > curMinQ) {
                    next[0] = curMinQ;
                    next[1] = curMinP;
                }
                prev.put(v, next);
                
                if (inDeg.get(v) == 0)  {
                    q.offer(v);
                }
            }
        }
        
        return ans;
    }
}

