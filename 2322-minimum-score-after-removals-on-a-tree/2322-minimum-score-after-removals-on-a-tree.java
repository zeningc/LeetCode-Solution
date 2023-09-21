class Solution {
    Map<Integer, List<Integer>> graph;
    int[] nums;
    int ans;
    public int minimumScore(int[] nums, int[][] edges) {
        ans = Integer.MAX_VALUE;
        graph = new HashMap<>();
        this.nums = nums;
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            if (!graph.containsKey(v))
                graph.put(v, new LinkedList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        for (int[] edge : edges)    {
            int a = getAll(edge[0], edge[1]);
            int b = getAll(edge[1], edge[0]);
            
            dfs(a, b, edge[1], edge[0]);
            dfs(b, a, edge[0], edge[1]);
        }
        
        return ans;
    }
    
    int dfs(int xor, int ttl, int u, int parent) {
        int xorAns = nums[u];
        for (int v : graph.get(u))  {
            if (parent == v)
                continue;
            int t = dfs(xor, ttl, v, u);
            int max = Math.max(xor, Math.max(ttl ^ t, t));
            int min = Math.min(xor, Math.min(ttl ^ t, t));
            ans = Math.min(max - min, ans);
            xorAns ^= t;
        }
        return xorAns;
    }
    
    int getAll(int u, int parent)    {
        int ans = nums[u];
        
        for (int v : graph.get(u))  {
            if (parent == v)
                continue;
            ans ^= getAll(v, u);
        }
        
        return ans;
    }
}