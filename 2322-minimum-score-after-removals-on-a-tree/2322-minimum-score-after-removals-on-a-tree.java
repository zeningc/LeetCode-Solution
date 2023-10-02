class Solution {
    int ans;
    Map<Integer, List<Integer>> graph;
    int n;
    int[] nums;
    public int minimumScore(int[] nums, int[][] edges) {
        ans = Integer.MAX_VALUE;
        graph = new HashMap<>();
        n = nums.length;
        this.nums = nums;
        for (int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, a -> new LinkedList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new LinkedList<Integer>()).add(u);
        }
        
        for (int[] edge : edges)
        {
            int a = edge[0];
            int b = edge[1];
            int xor1 = dfs(a, b);
            int xor2 = dfs(b, a);
            dfs2(a, b, xor1, xor2);
            dfs2(b, a, xor2, xor1);
        }
        
        return ans;
    }
    
    int dfs(int u, int p)
    {
        int xor = nums[u];
        for (int v : graph.get(u))
        {
            if (v == p)
                continue;
            xor ^= dfs(v, u);
        }
        return xor;
    }
    
    int dfs2(int u, int p, int curXor, int otherXor)
    {
        int xor = nums[u];
        for (int v : graph.get(u))
        {
            if (v == p)
                continue;
            int childXor = dfs2(v, u, curXor, otherXor);
            ans = Math.min(ans, calc(otherXor, curXor ^ childXor, childXor));
            xor ^= childXor;
        }
        return xor;
    }
    
    int calc(int a, int b, int c)
    {
        return Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
    }
}