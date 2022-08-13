class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new LinkedList<>();
        dfs(new LinkedList<>(), 1, k, n);
        return ans;
    }
    
    void dfs(List<Integer> path, int start, int cnt, int remain)    {
        if(remain == 0 && cnt == 0) {
            ans.add(new LinkedList<>(path));
            return;
        }
        if (cnt == 0 || remain == 0)
            return;
        for (int i = start; i <= 9; i++)    {
            if (remain - i < 0)
                break;
            path.add(i);
            dfs(path, i + 1, cnt - 1, remain - i);
            path.remove(path.size() - 1);
        }
    }
}
