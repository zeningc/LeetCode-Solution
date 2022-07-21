class Solution {
    int[] nums;
    public int[] sortArray(int[] nums) {
        this.nums = nums;
        return mergeSort(0, nums.length - 1);
    }
    
    int[] mergeSort(int lo, int hi)    {
        if (lo == hi)   {
            return new int[] {nums[lo]};
        }
        
        if (lo == hi - 1)   {
            if (nums[lo] > nums[hi])    {
                return new int[] {nums[hi], nums[lo]};
            }
            return new int[] {nums[lo], nums[hi]};
        }
        
        int mid = lo + (hi - lo) / 2;
        int[] ans = new int[hi - lo + 1];
        int[] left = mergeSort(lo, mid - 1);
        int[] right = mergeSort(mid, hi);
        int p = 0;
        int q = 0;
        int idx = 0;
        while (p < left.length && q < right.length) {
            if (left[p] < right[q]) 
                ans[idx++] = left[p++];
            else
                ans[idx++] = right[q++];
        }
        
        while (p < left.length)
            ans[idx++] = left[p++];
        
        while (q < right.length)
            ans[idx++] = right[q++];
        
        return ans;
    }
}