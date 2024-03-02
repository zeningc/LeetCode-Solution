class Solution {
    public int minOperations(int[] nums, int x, int y) {
        int lo = 1;
        int hi = Integer.MAX_VALUE;
        while (lo <= hi)    {
            int m = lo + (hi - lo) / 2;
            if (check(nums, x, y, m))
                hi = m - 1;
            else
                lo = m + 1;
        }
        
        return lo;
    }
    
    
    boolean check(int[] nums, int x, int y, int m)  {
        long cnt = m;
        long gap = x - y;
        for (int i = 0; i < nums.length; i++)   {
            long left = (long)nums[i] - (long)m * y;
            if (left <= 0)
                continue;
            long need = left / gap + (left % gap == 0 ? 0 : 1);
            if (cnt < need)
                return false;
            cnt -= need;
        }
        
        return true;
    }
}