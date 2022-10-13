class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        dfs(new LinkedList<>(), 1, n, k);
        return ans;
    }
    
    void dfs(List<Integer> path, int idx, int n, int k) {
        if (idx > n)
            return;
        dfs(path, idx + 1, n, k);
        
        path.add(idx);
        if (k == 1) {
            ans.add(new LinkedList<>(path));
        }
        else    {
            dfs(path, idx + 1, n, k - 1);
        }
        path.remove(path.size() - 1);
        
    }
}