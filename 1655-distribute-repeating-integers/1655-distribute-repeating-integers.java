class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        boolean[] dp = new boolean[(1 << m)];
        dp[0] = true;
        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);
        int n = keys.size();
        for (int i = 0; i < n; i++) {
            boolean[] nxtDP = dp.clone();
            int cnt = freq.get(keys.get(i));
            for (int pre = 0; pre < (1 << m); pre++)  {
                if (!dp[pre])
                    continue;
                for (int nxt = 0; nxt < (1 << m); nxt++)    {
                    if ((nxt & pre) != 0)
                        continue;
                    int curCnt = cnt;
                    for (int j = 0; j < m; j++) {
                        if ((nxt & (1 << j)) != 0)
                            curCnt -= quantity[j];
                    }
                    if (curCnt < 0)
                        continue;
                    nxtDP[pre | nxt] = true;
                }
            }
            dp = nxtDP;
        }
        
        return dp[(1 << m) - 1];
    }
}