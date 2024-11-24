class Solution {
    public long maximizeSumOfWeights(int[][] edges, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] e : edges)   {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(new int[] {e[1], e[2]});
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(new int[] {e[0], e[2]});
        }
        
        return dfs(graph, k, 0, -1)[1];
    }
    
    
    long[] dfs(Map<Integer, List<int[]>> graph, int k, int u, int p) {
        long ret1 = 0;
        long ret2 = 0;
        List<Long> diff = new ArrayList<>(graph.get(u).size() - 1);
        for (int[] nxt : graph.get(u))  {
            if (nxt[0] == p)
                continue;
            int v = nxt[0];
            long w = nxt[1];
            long[] res = dfs(graph, k, v, u);
            ret1 += res[1];
            diff.add(Math.max(0, res[0] + w - res[1]));
        }
        
        Collections.sort(diff);
        for (int i = diff.size() - 1; i >= Math.max(0, diff.size() - (k - 1)); i--)
            ret1 += diff.get(i);
        ret2 = diff.size() >= k ? ret1 + diff.get(diff.size() - k) : ret1;
        
        return new long[] {ret1, ret2};
    }
}