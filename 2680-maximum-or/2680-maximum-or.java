class Solution {
    public long maximumOr(int[] nums, int k) {
        int[] cnt = new int[64];
        for (int num : nums)
            for (int b = 0; b < 32; b++)
                if ((num & (1 << b)) != 0)
                    cnt[b]++;
        int maxReach = 0;
        for (int i = 63; i >= 0; i--)
            if (cnt[i] != 0)    {
                maxReach = i;
                break;
            }
        long ans = 0L;
        for (int num : nums)    {
            
            if ((num & (1 << maxReach)) == 0)
                continue;
            
            for (int b = 0; b < 32; b++)
                if ((num & (1 << b)) != 0)  {
                    cnt[b]--;
                    cnt[b + k]++;
                }
            
            long cur = 0L;
            
            for (int i = 63; i >= 0; i--)
                if (cnt[i] > 0)
                    cur |= (1L << i);
            
            for (int b = 0; b < 32; b++)
                if ((num & (1 << b)) != 0)  {
                    cnt[b]++;
                    cnt[b + k]--;
                }
            
            ans = Math.max(ans, cur);
        }
        
        return ans;
    }
}