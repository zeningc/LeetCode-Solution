class Solution {
    public int minOperations(int[] nums, int x) {
        long sum = 0;
        int n = nums.length;
        for (int num : nums)
            sum += num;
        if (sum == x)
            return n;
        long target = sum - x;
        int l = 0;
        sum = 0;
        int len = n + 1;
        for (int r = 0; r < n; r++)
        {
            sum += nums[r];
            while (l < n && sum >= target)
            {
                if (sum == target)
                {
                    len = Math.min(len, n - r + l - 1);
                }
                sum -= nums[l];
                l++;
            }
        }
        
        return len == n + 1 ? -1 : len;
    }
}