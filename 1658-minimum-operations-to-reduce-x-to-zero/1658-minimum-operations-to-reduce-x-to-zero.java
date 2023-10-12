class Solution {
    public int minOperations(int[] nums, int x) {
        Map<Long, Integer> presumToIdx = new HashMap<>();
        presumToIdx.put(0L, -1);
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++)
        {
            sum += nums[i];
            if (!presumToIdx.containsKey(sum))
            {
                presumToIdx.put(sum, i);
            }
        }
        int len = Integer.MAX_VALUE;
        sum = 0;
        for (int i = n; i >= 0; i--)
        {
            sum += (i == n ? 0 : nums[i]);
            long target = (long)x - sum;
            if (presumToIdx.containsKey(target) && presumToIdx.get(target) < i)
            {
                len = Math.min(len, n - i + presumToIdx.get(target) + 1);
            }
        }
            
            
        return len == Integer.MAX_VALUE ? -1 : len;
    }
}