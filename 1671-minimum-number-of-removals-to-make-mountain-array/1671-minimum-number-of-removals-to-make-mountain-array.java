class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        int p = -1;
        int[] left = new int[n];
        int[] right = new int[n];
        
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            int lo = 0;
            int hi = p;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] >= cur)   {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            if (lo == p + 1)
                p++;
            arr[lo] = cur;
            left[i] = lo;
        }
        p = -1;
        for (int i = n - 1; i > -1; i--)    {
            int cur = nums[i];
            int lo = 0;
            int hi = p;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] >= cur)   {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            if (lo == p + 1)
                p++;
            arr[lo] = cur;
            right[i] = lo;
        }
        
        int ans = n;
        
        for (int i = 0; i < n; i++) {
            if (left[i] != 0 && right[i] != 0)
                ans = Math.min(ans, n - left[i] - right[i] - 1);
        }
        
        return ans;
    }
}