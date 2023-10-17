class Solution {
    public int minSizeSubarray(int[] nums, int target) {
        long total = 0;
        for (int num : nums)
            total += num;
        int n = nums.length;
        long base = (long)target / total * n;
        target %= total;
        if (target == 0)
            return (int)base;
        Map<Long, Integer> m = new HashMap<>();
        m.put(0L, -1);
        long ans = Long.MAX_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++)
        {
            sum += nums[i];
            if (m.containsKey(sum - target))
            {
                ans = Math.min(ans, base + i - m.get(sum - target));
            }
            m.put(sum, i);
        }
        
        for (int i = n; i < 2 * n; i++)
        {
            sum += nums[i - n];
            if (m.containsKey(sum - target))
            {
                ans = Math.min(ans, base + i - m.get(sum - target));
            }
        }
        
        
        return ans == Long.MAX_VALUE ? -1 : (int)ans;
    }
}