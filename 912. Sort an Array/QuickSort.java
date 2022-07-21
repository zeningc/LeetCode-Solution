class Solution {
    int[] nums;
    public int[] sortArray(int[] nums) {
        this.nums = nums;
        quickSort(0, nums.length - 1);
        return nums;
    }
    
    void quickSort(int lo, int hi)    {
        if (lo >= hi)
            return;
        int pivot = nums[lo];
        int pos = lo;
        int j = hi;
        int i = lo;
        while (i < j)   {
            while (j > i && nums[j] >= pivot)
                j--;
            if (i <= j) {
                nums[pos] = nums[j];
                pos = j;
            }
            while (i < j && nums[i] <= pivot)
                i++;
            if (i <= j) {
                nums[pos] = nums[i];
                pos = i;
            }
        }
        nums[pos] = pivot;
        quickSort(lo, pos - 1);
        quickSort(pos + 1, hi);
    }
}