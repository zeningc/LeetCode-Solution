class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        int n = nums.length;
        Arrays.sort(nums);
        long[] presum = new long[n];
        int l = 0;
        int ans = 0;
        for (int r = 0; r < n; r++) {
            presum[r] = (r == 0 ? 0 : presum[r - 1]) + nums[r];
            if (!check(presum, nums, l, r, k))  {
                int lo = l;
                int hi = r;
                while (lo <= hi)    {
                    int mid = lo + (hi - lo) / 2;
                    if (check(presum, nums, mid, r, k))
                        hi = mid - 1;
                    else
                        lo = mid + 1;
                }
                l = lo;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
    
    boolean check(long[] presum, int[] nums, int lo, int hi, long k) {
        int mid = lo + (hi - lo) / 2;
        long pre = (long)nums[mid] * (mid - lo + 1) - (presum[mid] - (lo == 0 ? 0 : presum[lo - 1]));
        long suf = (long)(presum[hi] - presum[mid] - (long)(hi - mid) * nums[mid]);
        return pre + suf <= k;
    }
}