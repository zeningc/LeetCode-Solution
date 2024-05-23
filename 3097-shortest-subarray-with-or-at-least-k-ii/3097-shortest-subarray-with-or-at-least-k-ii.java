class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int lo = 1;
        int hi = nums.length;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, k, mid))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return lo == nums.length + 1 ? -1 : lo;
    }
    
    boolean check(int[] nums, int k, int mid)   {
        int[] bit = new int[32];
        int lo = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            for (int i = 0; i < 32; i++)    {
                if ((nums[hi] & (1 << i)) != 0) {
                    bit[i]++;
                }
            }
            
            if (hi - lo + 1 > mid)  {
                for (int i = 0; i < 32; i++)    {
                    if ((nums[lo] & (1 << i)) != 0) {
                        bit[i]--;
                    }
                }
                lo++;
            }
            
            int cur = 0;
            for (int i = 0; i < 32; i++)
                if (bit[i] != 0)
                    cur |= (1 << i);
            
            if (cur >= k)
                return true;
        }
        return false;
    }
}