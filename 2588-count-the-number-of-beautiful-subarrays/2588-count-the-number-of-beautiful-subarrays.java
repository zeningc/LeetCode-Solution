class Solution {
    public long beautifulSubarrays(int[] nums) {
        long ans = 0;
        Map<Integer, Long> m = new HashMap<>();
        int state = 0;
        m.put(state, 1L);
        for (int num : nums)
        {
            for (int i = 0; i < 32; i++)
            {
                if ((num & (1 << i)) != 0)
                {
                    state ^= (1 << i);
                }
            }
            ans += m.getOrDefault(state, 0L);
            m.put(state, m.getOrDefault(state, 0L) + 1);
        }
        
        return ans;
    }
}

