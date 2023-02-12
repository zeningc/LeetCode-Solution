class Solution {
    public boolean increasingTriplet(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int n = nums.length;
        int[] arr = new int[n];
        int p = -1;
        for (int i = 0; i < n; i++)   {
            int cur = nums[i];
            int lo = 0;
            int hi = p;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] >= cur)    {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            arr[lo] = cur;
            if (lo == p + 1)
                p++;
        }
        return p >= 2;
    }
}