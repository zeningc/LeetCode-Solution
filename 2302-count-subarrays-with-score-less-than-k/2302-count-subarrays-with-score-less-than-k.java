class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0;
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + nums[i - 1];
        
        for (int i = 0; i < n; i++) {
            if (nums[i] >= k)
                continue;
            int lo = i;
            int hi = n - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if ((presum[mid + 1] - presum[i]) * (mid - i + 1) >= k)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            ans += (hi - i + 1);
        }
        
        return ans;
    }
}

