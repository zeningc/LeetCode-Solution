class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        Map<Integer, List<Integer>> g1 = buildGraph(edges1);
        Map<Integer, List<Integer>> g2 = buildGraph(edges2);
        int n = edges1.length + 1;
        int[] cnt1 = new int[2];
        int[] color1 = new int[n];
        dfs(color1, cnt1, g1, 0, -1, 0);
        int[] cnt2 = new int[2];
        dfs(null, cnt2, g2, 0, -1, 0);


        int[] ans = new int[edges1.length + 1];
        for (int i = 0; i < n; i++) {
            int c = color1[i];
            int target = cnt1[c] + Math.max(cnt2[0], cnt2[1]);
            ans[i] = target;
        }
        

        return ans;
    }

    void dfs(int[] color, int[] cnt, Map<Integer, List<Integer>> g, int u, int p, int d)   {
        cnt[d % 2]++;
        if (color != null)
            color[u] = d % 2;
        for (int v : g.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            dfs(color, cnt, g, v, u, d + 1);
        }
    }

    Map<Integer, List<Integer>> buildGraph(int[][] edges)  {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : edges)   {
            g.computeIfAbsent(e[0], __ -> new ArrayList<>()).add(e[1]);
            g.computeIfAbsent(e[1], __ -> new ArrayList<>()).add(e[0]);
        }
        return g;
    }
}