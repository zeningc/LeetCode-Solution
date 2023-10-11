class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0L;
        for (int num : nums)
            total += num;
        int len = Integer.MAX_VALUE;
        if (total % p == 0)
            return 0;
        int n = nums.length;
        Map<Long, Integer> modToIdx = new HashMap<>();
        modToIdx.put(total % p, -1);
        long sum = 0;
        for (int i = 0; i < n; i++)
        {
            sum += nums[i];
            if (modToIdx.containsKey(sum % p))
            {
                len = Math.min(len, i - modToIdx.get(sum % p));
            }
            modToIdx.put((sum + total) % p, i);
        }
        
        return len == Integer.MAX_VALUE || len == n ? -1 : len;
    }
}
// (total - (sum - x)) % p == 0
// ((total + x) - (sum)) % p == 0

// (total + x) % p == sum % p

