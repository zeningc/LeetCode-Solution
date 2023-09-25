class Solution {
    Map<Integer, Set<Integer>> children;
    int[] ans;
    Map<Integer, Set<Integer>> setMap;
    int[] nums;
    Map<Integer, Integer> missMap;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        this.nums = nums;
        int n = parents.length;
        setMap = new HashMap<>();
        children = new HashMap<>();
        ans = new int[n];
        missMap = new HashMap<>();
        Arrays.fill(ans, 1);
        for (int i = 0; i < n; i++) {
            if (!children.containsKey(parents[i]))   {
                children.put(parents[i], new HashSet<>());
            }
            children.get(parents[i]).add(i);
        }
        dfs(0);
        
        return ans;
    }
    
    
    private void dfs(int node)  {
        if  (!children.containsKey(node))   {
            Set<Integer> set = new HashSet<>();
            set.add(nums[node]);
            setMap.put(node, set);
            missMap.put(node, nums[node] == 1 ? 2 : 1);
            ans[node] = missMap.get(node);
            return;
        }
        
        for (int child : children.get(node))  {
            dfs(child);
        }
        int maxSetChildren = -1;
        int maxStart = 0;
        for (int child : children.get(node))  {
            if (maxSetChildren == -1 || setMap.get(maxSetChildren).size() < setMap.get(child).size())    {
                maxSetChildren = child;
            }
            maxStart = Math.max(maxStart, missMap.get(child));
        }
        
        setMap.put(node, setMap.get(maxSetChildren));
        Set<Integer> thisSet = setMap.get(node);
        
        for (int child : children.get(node))  {
            if (child == maxSetChildren)   {
                continue;
            }
            thisSet.addAll(setMap.get(child));
        }
        thisSet.add(nums[node]);
        while (thisSet.contains(maxStart))
            maxStart++;
        missMap.put(node, maxStart);
        ans[node] = maxStart;
    }
}