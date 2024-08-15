class Solution {
    int[] subCnt;
    int[] subSum;
    int[] ans;
    int n;
    Map<Integer, List<Integer>> graph;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        subCnt = new int[n];
        subSum = new int[n];
        ans = new int[n];
        this.n = n;
        graph = new HashMap<>();
        for (int[] e : edges)   {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        
        dfs(0, -1);
        dfs1(0, -1);
        return ans;
    }
    
    int[] dfs(int u, int p) {
        int[] sub = new int[2];
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            int[] nxt = dfs(v, u);
            sub[0] += nxt[0];
            sub[1] += nxt[1] + nxt[0];
        }
        sub[0]++;
        subCnt[u] = sub[0];
        subSum[u] = sub[1];
        return sub;
    }
    
    void dfs1(int u, int p) {
        if (p != -1)
            ans[u] = ans[p] - 2 * subCnt[u] + n;
        else
            ans[u] = subSum[u];
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            dfs1(v, u);
        }
    }
}