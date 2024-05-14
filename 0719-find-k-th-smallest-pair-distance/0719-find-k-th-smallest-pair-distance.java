class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int lo = 0;
        int hi = nums[n - 1];
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid, k))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] nums, int d, int k)    {
        int lo = 0;
        int cnt = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            while (nums[hi] - nums[lo] > d)
                lo++;
            cnt += hi - lo;
        }
        
        return cnt >= k;
    }
}