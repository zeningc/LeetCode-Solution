class Solution {
    int[] flowers;
    long[] prefix;
    int target;
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        this.flowers = flowers;
        this.target = target;
        int n = flowers.length;
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + flowers[i];
        
        int start = n - 1;
        while(start > -1 && flowers[start] >= target)
            start--;
        if (start == -1)
            return (long)full * n;
        long ans = (long)full * (n - start - 1) + (long)partial * binarySearch(start, newFlowers);
        
        while (start > -1)  {
            if (newFlowers < target - flowers[start])   {
                break;
            }
            if (start == 0) {
                ans = Math.max(ans, (long)full * n);
                break;
            }
            newFlowers -= target - flowers[start];
            ans = Math.max(ans, (long)full * (n - start) + (long)partial * binarySearch(start - 1, newFlowers));
            start--;
        }
        
        return ans;
    }
    
    long binarySearch(int end, long remain) {
        int left = 0;
        int right = end;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if ((long)flowers[mid] * (mid + 1) - prefix[mid + 1] >= remain)   {
                right = mid - 1;
            }
            else    {
                left = mid + 1;
            }
        }
        remain -= (long)flowers[right] * (right + 1) - prefix[right + 1];
        long retVal = (long)flowers[right] + remain / (right + 1);
        if (retVal < target)
            return retVal;
        return (long)target - 1;
    }
}
