class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            edgeMap.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            edgeMap.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }
        long[] ret = dfs(edgeMap, values, 0, -1);
        return Math.max(ret[0], ret[1]);
    }
    
    long[] dfs(Map<Integer, List<Integer>> edgeMap, int[] values, int u, int p)  {
        if (edgeMap.get(u).size() == 1 && edgeMap.get(u).get(0) == p)
            return new long[] {0, 0, values[u]};
        long[] ret = new long[3];
        ret[0] += values[u];
        for (int v : edgeMap.get(u))   {
            if (v == p)
                continue;
            long[] nxt = dfs(edgeMap, values, v, u);
            ret[0] += Math.max(nxt[0], nxt[1]);
            ret[1] += nxt[2];
        }
        ret[2] = ret[1] + values[u];
        return ret;
    }
}