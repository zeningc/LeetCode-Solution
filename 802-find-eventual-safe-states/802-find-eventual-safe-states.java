class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] indeg = new int[n];
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0)
                continue;
            for (int v : graph[i])  {
                if (!edges.containsKey(v))
                    edges.put(v, new LinkedList<>());
                edges.get(v).add(i);
                indeg[i]++;
            }
        }
        List<Integer> ans = new LinkedList<>();
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0)
                q.offer(i);
        }
        
        while (!q.isEmpty())    {
            int u = q.poll();
            ans.add(u);
            
            if (!edges.containsKey(u))
                continue;
            
            for (int v : edges.get(u))  {
                indeg[v]--;
                if (indeg[v] == 0)
                    q.offer(v);
            }
        }
        
        Collections.sort(ans);
        
        return ans;
    }
}