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
            for (int j = i + 1; j < arr.length; j++)    {
                if (Math.abs((double)arr[i] / arr[j] - lo) < gap)   {
                    left = arr[i];
                    right = arr[j];
                    gap = Math.abs((double)arr[i] / arr[j] - lo);
                }
            }
        }
        
        return new int[] {left, right};
    }
    
    boolean check(int[] arr, double target, int k) {
        int cnt = 0;
        
        for (int i = 0; i < arr.length; i++)    {
            for (int j = i + 1; j < arr.length; j++)    {
                if ((double)arr[i] / arr[j] <= target)  {
                    cnt += arr.length - j;
                    break;
                }
            }
        }
        
        return cnt >= k;
    }
}