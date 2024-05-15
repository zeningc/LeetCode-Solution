class Solution {
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int lo = 0;
        int hi = Integer.MAX_VALUE / 2;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid, k))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] nums, int mid, int k)   {
        int cnt = 0;
        int lo = 0;
        int sum = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            sum += nums[hi];
            while (sum > mid)   {
                sum -= nums[lo];
                lo++;
            }
            cnt += hi - lo + 1;
        }
        
        return cnt >= k;
    }
}


// x x x o o o