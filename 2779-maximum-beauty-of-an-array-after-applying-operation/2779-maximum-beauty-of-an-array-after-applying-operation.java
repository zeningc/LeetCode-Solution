class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int ans = 1;
        Arrays.sort(nums);
        int min = nums[0];
        int n = nums.length;
        int max = nums[n - 1];
        for (int i = min; i <= max; i++)    {
            int lo = 0;
            int hi = n - 1;
            int target = i - k;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] >= target)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            int leftIdx = lo;
            lo = 0;
            hi = n - 1;
            target = i + k;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] > target)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            int rightIdx = hi;
            ans = Math.max(ans, rightIdx - leftIdx + 1);
        }
        return ans;
    }
}