class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int p = n;
        for (int i = n - 2; i >= 0; i--)    {
            if (nums[i] < nums[i + 1])  {
                p = i;
                break;
            }
        }
        int q = 0;
        for (int i = n - 1; i > p; i--)    {
            if (nums[i] > nums[p])    {
                q = i;
                break;
            }
        }
        int lo = 0;
        int hi = n - 1;
        if (p != n) {
            int t = nums[p];
            nums[p] = nums[q];
            nums[q] = t;
            lo = p + 1;
        }
        
        while (lo < hi) {
            int t = nums[lo];
            nums[lo] = nums[hi];
            nums[hi] = t;
            lo++;hi--;
        }
    }
}
