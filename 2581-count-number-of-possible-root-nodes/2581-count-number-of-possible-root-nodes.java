class Solution {
    Map<Integer, Set<Integer>> guessMap;
    Map<Integer, List<Integer>> graph;
    int ans;
    int k;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        guessMap = new HashMap<>();
        graph = new HashMap<>();
        ans = 0;
        this.k = k;
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            graph.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }
        
        for (int[] g : guesses)
            guessMap.computeIfAbsent(g[1], x -> new HashSet<>()).add(g[0]);
        
        int cnt = dfs(0, -1);
        dfs1(0, -1, cnt);
        
        return ans;
    }
    
    int dfs(int u, int p)
    {
        int cnt = 0;
        for (int v : graph.get(u))
        {
            if (p == v)
                continue;
            cnt += dfs(v, u);
            if (guessMap.containsKey(v) && guessMap.get(v).contains(u))
                cnt++;
        }
        return cnt;
    }
    
    void dfs1(int u, int p, int cnt)
    {
        
        if (cnt >= k)
        {
            ans++;
        }
        if (!graph.containsKey(u))
            return;
        for (int v : graph.get(u))
        {
            if (p == v)
                continue;
            int temp = cnt;
            if (guessMap.containsKey(v) && guessMap.get(v).contains(u))
                temp--;
            if (guessMap.containsKey(u) && guessMap.get(u).contains(v))
                temp++;
            dfs1(v, u, temp);
        }
    }
}