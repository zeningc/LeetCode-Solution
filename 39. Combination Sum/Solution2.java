class Solution {
    List<List<Integer>> ans;
    int[] candidates;
    int target;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new LinkedList<>();
        this.candidates = candidates;
        this.target = target;
        
        dfs(new LinkedList<>(), 0, 0);
        
        return ans;
    }
    
    void dfs(List<Integer> path, int idx, int sum)  {
        if (sum == target)    {
            ans.add(new LinkedList<>(path));
            return;
        }
        
        
        for (int i = idx; i < candidates.length; i++)   {
            if (sum + candidates[i] > target)
                continue;
            path.add(candidates[i]);
            dfs(path, i, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
