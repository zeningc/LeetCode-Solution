class Solution {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int lo = 0;
        int hi = (int)1e9;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid, k))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] nums, int cap, int k)   {
        int i = 0;
        int cnt = 0;
        while (i < nums.length) {
            if (nums[i] > cap)  {
                i++;
                continue;
            }
            int j = i;
            while (j < nums.length && nums[j] <= cap)
                j++;
            int interval = j - i;
            cnt += (interval + 1) / 2;
            i = j;
        }
        return cnt >= k;
    }
}