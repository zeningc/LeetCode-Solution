class Solution {
    Map<Integer, List<Integer>> child;
    int ans;
    String s;
    public int longestPath(int[] parent, String s) {
        this.s = s;
        int n = parent.length;
        child = new HashMap<>();
        ans = 1;
        
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            if (!child.containsKey(p))
                child.put(p, new LinkedList<>());
            child.get(p).add(i);
        }
        
        dfs(0);
        return ans;
    }
    
    int dfs(int root)   {
        if (!child.containsKey(root))
            return 1;
        
        int maxLen = 0;
        int maxLen2 = 0;
        for (int c : child.get(root))   {
            int len = dfs(c);
            if (s.charAt(c) == s.charAt(root))
                continue;
            if (len > maxLen)   {
                maxLen2 = maxLen;
                maxLen = len;
            }
            else if (len > maxLen2) {
                maxLen2 = len;
            }
        }
        ans = Math.max(ans, maxLen + maxLen2 + 1);
        return maxLen + 1;
    }
}
