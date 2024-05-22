class Solution {
    public int findKthPositive(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] >= mid + k + 1)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo + k;
    }
}

//.    x x x x o o o o o