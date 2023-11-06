class Solution {
    int MOD = (int)1e9 + 7;
    int N;
    Integer[][] memo;
    int[] nums;
    public int specialPerm(int[] nums) {
        this.N = nums.length;
        this.memo = new Integer[N][1 << N];
        this.nums = nums;
        return backtrack(0, 0);
    }
    private int backtrack(int preIndex, int mask) {
        if (mask == (1 << N) - 1) return 1;
        if (memo[preIndex][mask] != null) return memo[preIndex][mask];
        int count = 0;
        for (int i = 0; i < N; i++)
            if ((mask & (1 << i)) == 0 && 
                (mask == 0 || nums[i] % nums[preIndex] == 0 || nums[preIndex] % nums[i] == 0))
                count = (count + backtrack(i, mask | (1 << i))) % MOD; 
        return memo[preIndex][mask] = count;
    }
} 