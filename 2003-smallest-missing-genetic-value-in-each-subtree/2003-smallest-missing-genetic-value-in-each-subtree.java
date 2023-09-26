class Solution {
    Map<Integer, List<Integer>> tree;
    int[] ans;
    Set<Integer> left;
    int p = 2;
    boolean[] has;
    int[] nums;
    Set<Integer> seen;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        tree = new HashMap<>();
        left = new HashSet<>();
        has = new boolean[200000];
        seen = new HashSet<>();
        this.nums = nums;
        int startNode = -1;
        int n = nums.length;
        ans = new int[n];
        for (int i = 0; i < n; i++)
            if (nums[i] == 1)
                startNode = i;
        
        for (int node = startNode; node != -1; node = parents[node])
            left.add(node);
        
        for (int i = 0; i < n; i++) {
            if (!tree.containsKey(parents[i]))
                tree.put(parents[i], new LinkedList<>());
            tree.get(parents[i]).add(i);
        }
        
        markAsOne(0);

        for (int node = startNode; node != -1; node = parents[node])   {
            if (tree.containsKey(node)) {
                for (int child : tree.get(node))    {
                    gather(child);
                }
            }
            has[nums[node]] = true;
            while (has[p])
                p++;
            ans[node] = p;
        }
        
        return ans;
    }
    
    void markAsOne(int node)    {
        if (!left.contains(node))
            ans[node] = 1;
        if (!tree.containsKey(node))
            return;
        for (int child : tree.get(node))
            markAsOne(child);
    }
    
    
    void gather(int node)   {
        if (seen.contains(node))
            return;
        seen.add(node);
        if (tree.containsKey(node)) {
            for (int child : tree.get(node))    {
                gather(child);
            }
        }
        
        has[nums[node]] = true;
        while(has[p])
            p++;
    }
}