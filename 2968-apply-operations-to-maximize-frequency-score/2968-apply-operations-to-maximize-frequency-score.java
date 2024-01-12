class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        int n = nums.length;
        int lo = 0;
        Arrays.sort(nums);
        int ans = 0;
        int p = -1;
        long presum = 0;
        long sufsum = 0;
        for (int hi = 0; hi < n; hi++)  {
            sufsum += nums[hi];
            while (true)    {
                int mid = lo + (hi - lo) / 2;
                long median = nums[mid];
                for (int i = p + 1; i <= mid; i++)  {
                    presum += nums[i];
                    sufsum -= nums[i];
                }
                p = mid;
                long need = median * (2 * mid - lo - hi + 1) - presum + sufsum;
                if (need > k) {
                    presum -= nums[lo];
                    lo++;
                    continue;
                }
                break;
            }
            ans = Math.max(ans, hi - lo + 1);
        }
        return ans;
    }
}