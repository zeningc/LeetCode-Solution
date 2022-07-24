class Solution {
    Set<Integer> set;
    Map<Integer, Integer> mem;
    public int longestConsecutive(int[] nums) {
        set = new HashSet<>();
        mem = new HashMap<>();
        for (int num : nums)
            set.add(num);
        int ans = 0;
        for (int num : nums)
            ans = Math.max(dfs(num), ans);        
        
        return ans;
    }
    
    int dfs(int n)  {
        if (mem.containsKey(n))
            return mem.get(n);
        
        if (!set.contains(n + 1))   {
            mem.put(n, 1);
            return 1;
        }
        
        int len = dfs(n + 1);
        
        mem.put(n, len + 1);
        return len + 1;
    }
}
