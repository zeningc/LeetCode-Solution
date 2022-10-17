class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] start = new int[n + 1];
        int[] indeg = new int[n + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] relation : relations)    {
            int u = relation[0];
            int v = relation[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            graph.get(u).add(v);
            indeg[v]++;
        }
        
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i <= n; i++)
            if (indeg[i] == 0){
                q.offer(new int[] {i, 0});
            }
        
        
        while (!q.isEmpty())    {
            int[] curr = q.poll();
            int u = curr[0];
            int t = curr[1];
            
            if (!graph.containsKey(u))
                continue;
            
            for (int v : graph.get(u))  {
                indeg[v]--;
                start[v] = Math.max(start[v], t + time[u - 1]);
                if (indeg[v] == 0)
                    q.offer(new int[] {v, start[v]});
            }
        }
        
        int ans = 0;
        for (int i = 1; i <= n; i++)
            ans = Math.max(ans, start[i] + time[i - 1]);
        
        return ans;
    }
}