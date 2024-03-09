class Solution {
    public int minimizeArrayValue(int[] nums) {
        int lo = 0;
        int hi = Integer.MAX_VALUE;
        while (lo <= hi)    {
            int m = lo + (hi - lo) / 2;
            if (check(nums, m))
                hi = m - 1;
            else
                lo = m + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] nums, int m)    {
        long idle = 0;
        for (int i = nums.length - 1; i >= 0; i--)
            if (nums[i] > m)
                idle += nums[i] - m;
            else
                idle -= Math.min(idle, m - nums[i]);
        
        return idle == 0;
    }
}