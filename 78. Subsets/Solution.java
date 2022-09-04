class Solution {
    List<List<Integer>> ans;
    int[] nums;
    int n;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new LinkedList<>();
        this.nums = nums;
        n = nums.length;
        dfs(new LinkedList<>(), 0);
        ans.add(new LinkedList<>());
        return ans;
    }
    
    void dfs(List<Integer> path, int lo)   {
        if (lo >= n)   {
            return;
        }
        for (int i = lo; i < n; i++)    {
            path.add(nums[i]);
            ans.add(new LinkedList<>(path));
            dfs(path, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
