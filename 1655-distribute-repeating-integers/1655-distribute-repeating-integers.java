class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        boolean[] dp = new boolean[1 << m];
        dp[0] = true;
        for (int num : freq.keySet())   {
            boolean[] newDP = dp.clone();
            for (int state = 1; state < (1 << m); state++)  {
                if (dp[state])
                    continue;
                for (int subset = state; subset != 0; subset = (subset - 1) & state)    {
                    if (!dp[state - subset])
                        continue;
                    int cnt = freq.get(num);
                    for (int i = 0; i < m; i++)
                        if ((subset & (1 << i)) != 0)
                            cnt -= quantity[i];
                    
                    if (cnt >= 0)
                        newDP[state] = true;
                }
            }
            dp = newDP;
        }
        
        return dp[(1 << m) - 1];
        
    }
}