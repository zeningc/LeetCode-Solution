class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[0])
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return lo == n ? nums[0] : nums[lo];
    }
}