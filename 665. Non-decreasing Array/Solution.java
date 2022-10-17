class Solution {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int p = 0;
        while (p < n - 1 && nums[p] <= nums[p + 1])
            p++;
        if (p == n - 1)
            return true;
        int q = n - 1;
        while (q > 0 && nums[q] >= nums[q - 1])
            q--;
        
        if (q != p + 1)
            return false;
        
        if (p == 0 || q == n - 1)
            return true;
        
        if (nums[p] <= nums[q + 1] || nums[q] >= nums[p - 1])
            return true;
        
        return false;
    }
}