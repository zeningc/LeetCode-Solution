class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++)
            diff[i] = Math.abs(nums1[i] - nums2[i]);
        Arrays.sort(diff);
        int lo = 0;
        int hi = diff[n - 1];
        int k = k1 + k2;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = diff[i - 1] + presum[i - 1];
        
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(presum, diff, k, mid))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        long ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (diff[i] > lo)   {
                k -= diff[i] - lo;
                diff[i] = lo;
                continue;
            }
            break;
        }
        
        for (int i =  n - 1; i >= 0; i--) {
            int curDiff = diff[i];
            if (k > 0 && curDiff > 0)  {
                curDiff--;
                k--;
            }
            ret += (long)curDiff * curDiff;
        }
        
        return ret;
    }
    
    boolean check(long[] presum, int[] diff, int k, int threshold)    {
        int n = diff.length;
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (diff[mid] >= threshold)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        long opt = presum[n] - presum[lo] - (long)(n - lo) * threshold;
        return opt <= (long)k;
    }
}

