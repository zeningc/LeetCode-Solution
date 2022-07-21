class Solution {
    Integer[] mem;
    int[] nums;
    public int combinationSum4(int[] nums, int target) {
        this.nums = nums;
        mem = new Integer[target + 1];
        return dfs(target);
    }
    
    int dfs(int n)   {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        if (mem[n] != null)
            return mem[n];
        int cnt = 0;
        for (int num : nums)    
            cnt += dfs(n - num);
        mem[n] = cnt;
        return cnt;
    }
}
