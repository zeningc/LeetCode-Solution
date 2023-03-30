class Solution {
    public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }
        List<Long> ans = new LinkedList<>();
        for (int query : queries)   {
            long sum = 0;
            int leftEnd = binarySearchLeftEnd(n, nums, query);
            if (leftEnd != -1)
                sum += (long)query * (leftEnd + 1) - presum[leftEnd + 1];
            int rightEnd = binarySearchRightEnd(n, nums, query);
            if (rightEnd != n)
                sum += presum[n] - presum[rightEnd] - (long)query * (n - rightEnd);
            ans.add(sum);
        }
        
        return ans;
    }
    
    int binarySearchLeftEnd(int n, int[] nums, int target)   {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            }
            else    {
                lo = mid + 1;
            }
        }
        return hi;
    }
    
    int binarySearchRightEnd(int n, int[] nums, int target)   {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                hi = mid - 1;
            }
            else    {
                lo = mid + 1;
            }
        }
        return lo;
    }
}