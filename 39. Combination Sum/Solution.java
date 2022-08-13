class Solution {
    List<List<Integer>> ans;
    int[] candidates;
    int target;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        ans = new LinkedList<>();
        dfs(new LinkedList<>(), 0, target);
        return ans;
    }
    
    void dfs(List<Integer> path, int idx, int amount)   {
        if (amount == 0)    {
            ans.add(new LinkedList<>(path));
            return;
        }
        
        if (idx >= candidates.length)
            return;
        int size = path.size();
        for (int i = 0; amount >= i * candidates[idx]; i++) {
            dfs(path, idx + 1, amount - i * candidates[idx]);
            path.add(candidates[idx]);
        }
        
        while (path.size() > size)
            path.remove(path.size() - 1);
    }
}
