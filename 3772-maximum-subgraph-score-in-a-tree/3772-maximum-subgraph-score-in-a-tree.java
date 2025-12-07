class Solution {
    int[] optimalChild;
    Map<Integer, List<Integer>> graph;
    int[] good;
    int[] ans;
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        optimalChild = new int[n];
        ans = new int[n];
        graph = new HashMap<>();
        this.good = good;
        for (int i = 0; i < n; i++)
            if (good[i] == 0)
                good[i] = -1;
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], __ -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], __ -> new ArrayList<>()).add(e[0]);
        }

        dfs1(0, -1);
        System.out.println(Arrays.toString(optimalChild));
        dfs2(0, -1, 0);

        return ans;
    }

    int dfs1(int u, int p) {
        int sum = 0;
        for (int v : graph.getOrDefault(u, new ArrayList<>())) {
            if (p == v)
                continue;
            sum += Math.max(0, dfs1(v, u));
        }
        optimalChild[u] = sum;
        return sum + good[u];
    }

    void dfs2(int u, int p, int fromP) {
        fromP += good[u];
        ans[u] = fromP + optimalChild[u];
        for (int v : graph.getOrDefault(u, new ArrayList<>())) {
            if (v == p)
                continue;
            if (optimalChild[v] + good[v] <= 0)
                continue;
            fromP += optimalChild[v] + good[v];
        }
        for (int v : graph.getOrDefault(u, new ArrayList<>())) {
            if (v == p)
                continue;
            dfs2(v, u, Math.max(0, fromP - Math.max(0, optimalChild[v] + good[v])));
        }
    }
}