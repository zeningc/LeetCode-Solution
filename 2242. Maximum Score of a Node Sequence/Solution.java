class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        Map<Integer, Queue<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new PriorityQueue<>((a, b) -> a[1] - b[1]));
            if (!graph.containsKey(v))
                graph.put(v, new PriorityQueue<>((a, b) -> a[1] - b[1]));
            graph.get(u).add(new int[] {v, scores[v]});
            if (graph.get(u).size() > 3)
                graph.get(u).poll();
            graph.get(v).add(new int[] {u, scores[u]});
            if (graph.get(v).size() > 3)
                graph.get(v).poll();
        }
        int ans = -1;
        List<int[]> uTop3 = new ArrayList<>(3);
        List<int[]> vTop3 = new ArrayList<>(3);
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            uTop3.clear();
            vTop3.clear();
            for (int[] x : graph.get(u))    {
                if (x[0] != v)
                    uTop3.add(x);
            }
            for (int[] y : graph.get(v))    {
                if (y[0] != u)
                    vTop3.add(y);
            }
            
            if (uTop3.size() == 0 || vTop3.size() == 0)
                continue;
            Collections.sort(uTop3, (a, b) -> b[1] - a[1]);
            Collections.sort(vTop3, (a, b) -> b[1] - a[1]);
            
            if (uTop3.get(0)[0] != vTop3.get(0)[0]) {
                ans = Math.max(ans, uTop3.get(0)[1] + vTop3.get(0)[1] + scores[u] + scores[v]);
                continue;
            }
            
            if (uTop3.size() == 1 && vTop3.size() == 1)
                continue;
            
            if (uTop3.size() == 1)  {
                ans = Math.max(ans, uTop3.get(0)[1] + vTop3.get(1)[1] + scores[u] + scores[v]);
                continue;
            }
            
            if (vTop3.size() == 1)  {
                ans = Math.max(ans, vTop3.get(0)[1] + uTop3.get(1)[1] + scores[u] + scores[v]);
                continue;
            }
            
            ans = Math.max(ans, vTop3.get(0)[1] + uTop3.get(1)[1] + scores[u] + scores[v]);
            ans = Math.max(ans, uTop3.get(0)[1] + vTop3.get(1)[1] + scores[u] + scores[v]);
            
        }
        
        return ans;
    }
}
