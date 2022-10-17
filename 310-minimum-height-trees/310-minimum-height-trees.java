class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return List.of(0);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] edgeCnt = new int[n];
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            graph.put(u, graph.getOrDefault(u, new LinkedList<>()));
            graph.put(v, graph.getOrDefault(v, new LinkedList<>()));
            graph.get(u).add(v);
            graph.get(v).add(u);
            edgeCnt[u]++;
            edgeCnt[v]++;
        }
        
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++)
            if (graph.get(i).size() == 1)
                q.offer(i);
        List<Integer> ans = new LinkedList<>();
        while (!q.isEmpty())    {
            ans = new LinkedList<>();
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (visited.contains(u))
                    continue;
                ans.add(u);
                visited.add(u);
                for (int v : graph.get(u))  {
                    if (visited.contains(v))
                        continue;
                    edgeCnt[v]--;
                    if (edgeCnt[v] == 1)    {
                        q.offer(v);
                    }
                }
            }
        }
        
        return ans;
    }
}