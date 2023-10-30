class Solution {
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        long[] dp = new long[r + 1];
        int mod = (int)1e9 + 7;
        dp[0] = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        int cntZero = map.getOrDefault(0, 0);
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            int num = entry.getKey();
            int freq = entry.getValue();
            for (int j = r; j > Math.max(0, r - num); j--)
            {
                long sum = 0L;
                for (int k = 0; k < freq && j - k * num >= 0; k++)
                    sum = (sum + dp[j - k * num]) % mod;
                
                for (int k = j; k > 0; k -= num)
                {
                    if (k - freq * num >= 0)
                        sum = (sum + dp[k - freq * num]) % mod;
                    sum = (sum + mod - dp[k]) % mod;
                    dp[k] = (sum + dp[k]) % mod;
                }
                    
            }
        }
        
        
        long ans = 0;
        for (int i = l; i <= r; i++)
        {
            ans = (ans + dp[i]) % mod;
        }
        return (int)(ans * (cntZero + 1) % mod);
    }
}