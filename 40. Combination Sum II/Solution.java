class Solution {
    int[] candidates;
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ans = new LinkedList<>();
        this.candidates = candidates;
        dfs(new LinkedList<>(), 0, target);
        return ans;
    }
    
    void dfs(List<Integer> path, int start, int amount) {
        if (amount == 0)    {
            ans.add(new LinkedList<>(path));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (amount - candidates[i] < 0)
                break;
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            path.add(candidates[i]);
            dfs(path, i + 1, amount - candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
