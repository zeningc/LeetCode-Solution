class Solution {
    int[] count;
    int n;
    int[] sorted;
    int[] nums;
    int[] temp;
    public List<Integer> countSmaller(int[] nums) {
        n = nums.length;
        count = new int[n];
        this.nums = nums;
        temp = new int[n];
        sorted = nums.clone();
        mergeSort(0, n - 1);
        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }
    
    void mergeSort(int lo, int hi)  {
        if (lo == hi)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);
        
        for (int i = lo; i <= mid; i++)  {
            int val = nums[i];
            int left = mid + 1;
            int right = hi;
            while (left <= right)   {
                int m = left + (right - left) / 2;
                if (sorted[m] < val)
                    right = m - 1;
                else
                    left = m + 1;
            }
            count[i] += hi - left + 1;
        }
        int idx = lo;
        int p = lo;
        int q = mid + 1;
        while (p <= mid && q <= hi) {
            if (sorted[p] < sorted[q])  {
                temp[idx++] = sorted[q++];
            }
            else    {
                temp[idx++] = sorted[p++];
            }
        }
        
        while (p <= mid)  {
            temp[idx++] = sorted[p++];
        }
        
        while (q <= hi)  {
            temp[idx++] = sorted[q++];
        }
        
        for (int i = lo; i <= hi; i++)  {
            sorted[i] = temp[i];
        }
    }
}