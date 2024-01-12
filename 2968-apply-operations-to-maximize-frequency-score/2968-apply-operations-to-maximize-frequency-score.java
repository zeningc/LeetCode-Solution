class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        int n = nums.length;
        int lo = 0;
        Arrays.sort(nums);
        long[] presum = new long[n + 1];
        int ans = 0;
        for (int hi = 0; hi < n; hi++)  {
            presum[hi + 1] = presum[hi] + nums[hi];
            while (true)    {
                int mid = lo + (hi - lo) / 2;
                int median = nums[mid];
                long need = (long)median * (2 * mid - lo - hi + 1) - 2 * presum[mid + 1] + presum[lo] + presum[hi + 1];
                if (need > k) {
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