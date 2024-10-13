class NumArray {
    int[] tree;
    int n;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++)    {
            tree[i] += nums[i - 1];
            int nxt = i + (i & -i);
            if (nxt <= n)
                tree[nxt] += tree[i];
        }
    }
    
    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        int p = index + 1;
        while (p <= n)  {
            tree[p] += delta;
            p += (p & -p);
        }
    }
    
    private int preSum(int i)   {
        int p = i;
        int sum = 0;
        while (p > 0)   {
            sum += tree[p];
            p -= (p & -p);
        }
        return sum;
    }
    
    public int sumRange(int left, int right) {
        return preSum(right + 1) - preSum(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */