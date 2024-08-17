class Solution {
    int n;
    Map<Integer, List<Integer>> graph;
    Map<Integer, Set<Integer>> grps;
    int k;
    int ans;
    int[] subOrder;
    int[] fromRootReverse;
    int[] fromRootOrder;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        n = edges.length + 1;
        graph = new HashMap<>();
        grps = new HashMap<>();
        ans = 0;
        subOrder = new int[n];
        fromRootReverse = new int[n];
        fromRootOrder = new int[n];
        this.k = k;
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
        }
        for (int[] guess : guesses)    {
            int u = guess[0];
            int v = guess[1];
            grps.computeIfAbsent(u, x -> new HashSet<>()).add(v);
        }
        dfs(0, -1, 0, 0);
        dfs1(0, -1);
        return ans;
    }
    
    int dfs(int u, int p, int order, int rev) {
        int ret = 0;
        fromRootReverse[u] = rev;
        fromRootOrder[u] = order;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            int addOrder = 0;
            int addReverse = 0;
            if (grps.getOrDefault(u, new HashSet<>()).contains(v))  {
                ret++;
                addOrder = 1;
            }
            if (grps.getOrDefault(v, new HashSet<>()).contains(u))
                addReverse = 1;
            ret += dfs(v, u, order + addOrder, rev + addReverse);
        }
        subOrder[u] = ret;
        return ret;
    }
    
    void dfs1(int u, int p) {
        if (p == -1 && subOrder[u] >= k || p != -1 && subOrder[0] - fromRootOrder[u] + fromRootReverse[u] >= k)
            ans++;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            dfs1(v, u);
        }
    }
}