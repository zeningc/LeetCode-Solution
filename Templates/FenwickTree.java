class FenwickTree {
    int[] tree;
    int n;
    int[] nums;
    public FenwickTree(int[] nums) {
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
