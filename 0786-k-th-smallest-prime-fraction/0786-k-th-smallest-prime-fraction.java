class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double lo = 0;
        double hi = 1;
        while (lo <= hi)    {
            double mid = lo + (hi - lo) / 2;
            if (check(arr, mid, k))
                hi = mid - .0000001;
            else
                lo = mid + .0000001;
        }
        
        int left = 0;
        int right = 0;
        double gap = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++)    {
            int l = i + 1;
            int r = arr.length - 1;
            while (l <= r)    {
                int m = l + (r - l) / 2;
                if ((double)arr[i] / arr[m] <= lo)
                    r = m - 1;
                else
                    l = m + 1;
            }
            
            if (l != arr.length)   {
                if (Math.abs((double)arr[i] / arr[l] - lo) < gap)   {
                    left = arr[i];
                    right = arr[l];
                    gap = Math.abs((double)arr[i] / arr[l] - lo);
                }
            }
            
            if (r != -1)    {
                if (Math.abs((double)arr[i] / arr[r] - lo) < gap)   {
                    left = arr[i];
                    right = arr[r];
                    gap = Math.abs((double)arr[i] / arr[r] - lo);
                }
            }
        }
        
        return new int[] {left, right};
    }
    
    boolean check(int[] arr, double target, int k) {
        int cnt = 0;
        
        for (int i = 0; i < arr.length; i++)    {
            int lo = i + 1;
            int hi = arr.length - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if ((double)arr[i] / arr[mid] <= target)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            
            if (lo != arr.length)
                cnt += arr.length - lo;
        }
        
        return cnt >= k;
    }
}